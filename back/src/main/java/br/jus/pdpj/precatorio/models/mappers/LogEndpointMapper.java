package br.jus.pdpj.precatorio.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;

import br.jus.pdpj.precatorio.auditoria.ContentTrace;
import br.jus.pdpj.precatorio.models.entities.LogEndpoint;

@Mapper(componentModel="spring")
public interface LogEndpointMapper {
    
    @Bean
	@Mapping(target = "id", ignore = true)
    public abstract LogEndpoint toEntity(ContentTrace model);

}
