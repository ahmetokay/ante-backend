package tr.com.ante.security.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tr.com.ante.core.constants.Constants;
import tr.com.ante.core.converter.BaseConverter;
import tr.com.ante.core.exception.BaseException;
import tr.com.ante.core.exception.UserNotFoundException;
import tr.com.ante.core.manager.AbstractOperationalManager;
import tr.com.ante.core.manager.AbstractSearchManager;
import tr.com.ante.core.query.AbstractQueryGenerator;
import tr.com.ante.core.service.AbstractGenericServiceImpl;
import tr.com.ante.core.utils.MailUtils;
import tr.com.ante.core.utils.PasswordUtils;
import tr.com.ante.security.user.converter.UserConverter;
import tr.com.ante.security.user.entity.UserEntity;
import tr.com.ante.security.user.manager.UserOperationalManager;
import tr.com.ante.security.user.manager.UserSearchManager;
import tr.com.ante.security.user.model.UserModel;
import tr.com.ante.security.user.model.UserQueryModel;
import tr.com.ante.security.user.query.UserQueryGenerator;
import tr.com.ante.security.user.repository.UserRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.Properties;

@RequiredArgsConstructor
@Component
public class UserServiceImpl extends AbstractGenericServiceImpl<UserModel, UserEntity, UserQueryModel> implements UserService {

    private final UserConverter converter;
    private final UserSearchManager searchManager;
    private final UserOperationalManager operationalManager;
    private final UserQueryGenerator queryGenerator;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected BaseConverter<UserModel, UserEntity> getConverter() {
        return converter;
    }

    @Override
    protected AbstractSearchManager<UserEntity> getSearchManager() {
        return searchManager;
    }

    @Override
    protected AbstractOperationalManager<UserEntity> getOperationalManager() {
        return operationalManager;
    }

    @Override
    protected AbstractQueryGenerator getQueryGenerator() {
        return queryGenerator;
    }

    @Override
    public UserModel save(UserModel dto) throws BaseException {
        if (dto.getPassword() != null) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return super.save(dto);
    }

    @Override
    public UserModel update(UserModel dto) throws BaseException {
        if (StringUtils.hasText(dto.getPassword())) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            dto.setPassword(repository.getPasswordFromEmail(dto.getEmail()));
        }
        return super.update(dto);
    }

    @Transactional
    @Override
    public void resetPassword(UserModel userModel) {
        UserEntity userEntity = repository.findByEmail(userModel.getEmail()).orElseThrow(() -> new UserNotFoundException(MessageFormat.format("Sistemde {0} kayıtlı kullanıcı adı bulunamadı.", userModel.getEmail())));

        // yeni parola ayarlanıyor
        String password = PasswordUtils.generatePassword(Constants.PASSWORD_LENGTH);
        userEntity.setPassword(passwordEncoder.encode(password));

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("antegrup.ankara", "Aa123456**");
            }

        });
        try {
            // yeni parola mail atılıyor
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("antegrup.ankara@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ahmetokay14@gmail.com"));
            message.setSubject("my First Email");
            message.setContent("<h:body>You wrote first email</body>", "text/html;     charset=utf-8");
            Transport.send(message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        SimpleMailMessage simpleMailMessage = MailUtils.generateMail(userEntity.getEmail(), "Şifreniz değiştirilmiştir.", "Yeni şifre :" + password);
        //javaMailSender.send(simpleMailMessage);
        // Transport.send();

        repository.saveAndFlush(userEntity);
    }
}