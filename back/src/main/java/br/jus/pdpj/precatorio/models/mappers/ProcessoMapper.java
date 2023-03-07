package br.jus.pdpj.precatorio.models.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.jus.pdpj.precatorio.models.dtos.ProcessoDTO;
import br.jus.pdpj.precatorio.models.entities.Processo;

@Mapper(componentModel="spring")
public interface ProcessoMapper {
    
    @Mapping(target="id", ignore=true) 
	@BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, 
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    public Processo toEntity(ProcessoDTO dto, @MappingTarget Processo entity);
	
	public ProcessoDTO toDTO(Processo entity);

    public List<ProcessoDTO> toDTOList(List<Processo> entity);
}
