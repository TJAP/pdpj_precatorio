package br.jus.pdpj.precatorio.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.jus.pdpj.commons.models.dtos.corporativo.OrgaoGenerico;
import br.jus.pdpj.commons.models.dtos.corporativo.UsuarioCorporativo;
import br.jus.pdpj.commons.models.dtos.corporativo.UsuarioPerfil;
import br.jus.pdpj.commons.models.dtos.tpu.Item;
import br.jus.pdpj.commons.utils.KeycloakUtil;
import br.jus.pdpj.precatorio.amqp.MensageriaService;
import br.jus.pdpj.precatorio.configurations.CacheConfig;
import br.jus.pdpj.precatorio.models.dtos.ExemploDTO;
import br.jus.pdpj.precatorio.models.dtos.InclusaoAlteracaoExemploDTO;
import br.jus.pdpj.precatorio.models.entities.Exemplo;
import br.jus.pdpj.precatorio.models.enums.EventoMensageria;
import br.jus.pdpj.precatorio.models.mappers.ExemploMapper;
import br.jus.pdpj.precatorio.repositories.ExemploRepository;

@Service
@Validated
public class ExemploService extends BaseService<Exemplo> {

	private final ExemploRepository repository;
    private final ExemploMapper mapper;
    private final CorporativoService corporativoService;
    private final TpuService tpuService;
    private final MensageriaService mensageriaService;
	
	@Autowired
	public ExemploService(ExemploRepository repository, ExemploMapper mapper,  MensageriaService mensageriaService,
			CorporativoService corporativoService, TpuService tpuService) {
		this.repository = repository;
        this.mapper = mapper;
        this.mensageriaService = mensageriaService;
        this.corporativoService = corporativoService;
        this.tpuService = tpuService;
	}
	
	@Override
	protected JpaRepository<Exemplo, Long> getRepository() {
		return repository;
	}
	
	/**
     * Retorna a lista de todas ocorrências de exemplo.
	 * @return
	 */
	public List<ExemploDTO> listar() {
        return mapper.toDTOList(repository.findAll());
    }

	/**
     * Retorna um exemplo pelo id.
	 * @return
	 */
	@Cacheable(CacheConfig.CACHE_EXEMPLO)
	public ExemploDTO findByIdDTO(Long id) {
        return mapper.toDTO(this.findById(id));
    }
	
	/**
     * Cria um novo exemplo.
	 * @return
	 */
	public ExemploDTO criar(@Valid InclusaoAlteracaoExemploDTO dto) {
		Exemplo exemplo = mapper.toEntity(dto, new Exemplo());
        exemplo = repository.save(exemplo);
        //mensageriaService.sendMessage(exemplo, "um número válido de processo", EventoMensageria.EXEMPLO_CRIADO.getTexto());
        return mapper.toDTO(exemplo);
    }
	
	/**
     * Edita um exemplo.
	 * @return
	 */
	public ExemploDTO editar(Long id, @Valid InclusaoAlteracaoExemploDTO dto) {
		Exemplo exemplo = findByIdOrThrowException(id);
		exemplo = mapper.toEntity(dto, exemplo);
        return mapper.toDTO(repository.save(exemplo));
    }
	
	/**
     * Remove um exemplo.
	 * @return
	 */
	public void remover(Long id) {
		Exemplo exemplo = findByIdOrThrowException(id);
        repository.delete(exemplo);
    }
	
	/**
	 * Implementa uma regra negocial qualquer.
	 * @return
	 */
	public ExemploDTO metodoComRegraNegocial() {
        return null;
    }
	
	/**
	 * Método com alguns exemplos de utilização dos services da TPU e CNJ Corporativo.
	 */
	public void metodoDeExemplo() {
		UsuarioCorporativo usuario = corporativoService.findUsuarioByCpf("CPF qualquer");
		OrgaoGenerico orgao = corporativoService.findOrgaoByCodigo("codigo de um orgao do CNJ Corporativo");
		Item classe = tpuService.findClasseByCodigo("7"); // Procedimento comum cível, segundo a TPU
	}
	
	public void metodoDeExemplo2() {
		//Pega os perfis do usuário logado no CNJ Corporativo, conforme retornado no token do Keycloak.
		//Só funciona se o client registrado no Keycloak estiver mapeado com o CNJ Corporativo.
		List<UsuarioPerfil> perfis = KeycloakUtil.getPerfisUsuarioLogado();
		
		//Faz algo com os perfis do usuário logado
	}
	
}
