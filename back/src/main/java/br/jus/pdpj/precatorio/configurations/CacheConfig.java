package br.jus.pdpj.precatorio.configurations;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class CacheConfig {

	public static final String CACHE_EXEMPLO = "exemplo";
    public static final String CACHE_EXEMPLO2 = "exemplo2";

	private RedisCacheConfiguration redisConfigWithTtl() {
		//Personalize como entender melhor
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(7));
	}
	
	private RedisCacheConfiguration redisConfig() {
		return RedisCacheConfiguration.defaultCacheConfig();
	}
	
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		
		//Nunca expiram
		cacheConfigurations.put(CACHE_EXEMPLO, redisConfig());
		
		//Expiram em uma semana
		cacheConfigurations.put(CACHE_EXEMPLO2, redisConfigWithTtl());
		
		return RedisCacheManager.builder(factory).withInitialCacheConfigurations(cacheConfigurations).build();
	}
}
