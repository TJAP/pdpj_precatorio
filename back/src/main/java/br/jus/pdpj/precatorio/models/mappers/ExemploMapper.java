package br.jus.pdpj.precatorio.models.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.jus.pdpj.precatorio.models.dtos.ExemploDTO;
import br.jus.pdpj.precatorio.models.dtos.InclusaoAlteracaoExemploDTO;
import br.jus.pdpj.precatorio.models.entities.Exemplo;

@Mapper(componentModel="spring")
public interface ExemploMapper {
	
	@Mapping(target="id", ignore=true) 
	@BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, 
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    public Exemplo toEntity(InclusaoAlteracaoExemploDTO dto, @MappingTarget Exemplo entity);
	
	public ExemploDTO toDTO(Exemplo entity);

    public List<ExemploDTO> toDTOList(List<Exemplo> entity);
}
