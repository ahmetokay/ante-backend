package tr.com.ante.core.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import tr.com.ante.core.model.PageableModel;

public class PageUtils {

    public static PageRequest createPageable(PageableModel pageableModel) {
        if (!StringUtils.hasText(pageableModel.getSortField())) {
            return PageRequest.of(pageableModel.getPage(), pageableModel.getSize(), Sort.by("createDate").descending());
        }
        switch (pageableModel.getSortOrder()) {
            case ASC:
                return PageRequest.of(pageableModel.getPage(), pageableModel.getSize(), Sort.by(pageableModel.getSortField()).ascending());
            default:
                return PageRequest.of(pageableModel.getPage(), pageableModel.getSize(), Sort.by(pageableModel.getSortField()).descending());
        }
    }
}