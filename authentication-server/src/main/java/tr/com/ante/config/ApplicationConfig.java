package tr.com.ante.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@Configuration
public class ApplicationConfig {

//    @Bean
//    public RedisConnectionFactory connectionFactory() {
//        return new LettuceConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
//
//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return createRedisCacheConfig(Duration.ofMillis(60 * 60 * 1000L));
//    }
//
//    private RedisCacheConfiguration createRedisCacheConfig(Duration duration) {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(duration)
//                .prefixCacheNameWith("ante::")
//                .disableCachingNullValues()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }
}