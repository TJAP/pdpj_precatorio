package br.jus.pdpj.precatorio.configurations;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationEntryPoint;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.QueryParamPresenceRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@Profile("sso-security")
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
    
    @Bean
    @Override
    protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter() throws Exception {
        RequestMatcher requestMatcher =
                new OrRequestMatcher(
                        new AntPathRequestMatcher(KeycloakAuthenticationEntryPoint.DEFAULT_LOGIN_URI),
                        new QueryParamPresenceRequestMatcher(OAuth2Constants.ACCESS_TOKEN),
                        new IgnoreKeycloakProcessingFilterRequestMatcher()
                );
        return new KeycloakAuthenticationProcessingFilter(authenticationManagerBean(), requestMatcher);
    }

    private class IgnoreKeycloakProcessingFilterRequestMatcher implements RequestMatcher {
        IgnoreKeycloakProcessingFilterRequestMatcher() {
        }

        public boolean matches(HttpServletRequest request) {
            String authorizationHeaderValue = request.getHeader("Authorization");
            return authorizationHeaderValue != null;
        }
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable()
        	.cors()
        	.and().headers().frameOptions().sameOrigin()
        	.and().authorizeRequests()
        	
        		 //Swagger
		        .antMatchers("/**").permitAll()
		        .antMatchers("/webjars/**").permitAll()
		        .antMatchers("/v2/api-docs").permitAll()
		        .antMatchers("/swagger-resources/**").permitAll()
		        .antMatchers("/swagger-ui.html").permitAll()
		        
		        .antMatchers("/actuator/**").permitAll()
		        .antMatchers("/api/v1/**").permitAll()
		        .antMatchers("/pdpj/info").permitAll();
		        
		        //.anyRequest().authenticated();
    }
    
    @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		//TODO: substituir * pelos valores corretos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        configuration.setMaxAge(3600L);
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));

		//configuration.setAllowedOrigins(Arrays.asList("*"));
		//configuration.setAllowedMethods(Arrays.asList("*"));
		//configuration.setAllowedHeaders(Arrays.asList("*"));
		
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
    
	
}
