package br.jus.pdpj.precatorio.configurations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.jus.pdpj.precatorio.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo())
                .globalResponseMessage(RequestMethod.GET, new ArrayList<>())
                .globalResponseMessage(RequestMethod.POST, new ArrayList<>())
                .securitySchemes(Arrays.asList(jwt()))
                .securityContexts(securityContexts());
    }
    
    private ApiKey jwt() { 
        return new ApiKey("JWT", "Authorization", "header"); 
    }
    
    private List<SecurityContext> securityContexts() { 
    	List<SecurityContext> contexts = new ArrayList<>();

        //Lista dos endpoints autenticados (para exibir o "cadeado" no swagger-ui.html)
        //É possível agrupar um conjunto de endpoints que compartilham um path comum usando
        //a sintaxe 'pathcomum.*'.
        contexts.add(SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/api/v1/exemplos.*")).build());
        contexts.add(SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/api/v1/pathcomum.*")).build());

        return contexts;
    } 

    private List<SecurityReference> defaultAuth() { 
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
        authorizationScopes[0] = authorizationScope; 
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo("PDPJ - Gerenciado de precatório", 
        		"Descrição do meu módulo negocial", 
        		"1", 
        		"TERMS OF SERVICE URL",
                new Contact("TJAP", "URL", "pedro.machado@tjap.jus.br"), 
                "LICENSE", 
                "LICENSE URL", 
                Collections.emptyList());
    }
    

}
