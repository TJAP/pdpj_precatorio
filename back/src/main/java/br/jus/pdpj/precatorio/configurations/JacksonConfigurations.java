package br.jus.pdpj.precatorio.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Configuration
public class JacksonConfigurations {
    
	@Value("${spring.jackson.time-zone}")
	private String timeZone;
	
	@Value("${spring.jackson.locale}")
	private String locale;
	
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.failOnEmptyBeans(false);
        builder.failOnUnknownProperties(false);
        builder.mixIn(Object.class, IgnoreHibernatePropertiesInJackson.class);
        builder.timeZone(timeZone);
        builder.locale(locale);
        return builder;
    }
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private abstract class IgnoreHibernatePropertiesInJackson{ }
}
