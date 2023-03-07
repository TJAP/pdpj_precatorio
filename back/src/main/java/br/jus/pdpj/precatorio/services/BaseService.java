package br.jus.pdpj.precatorio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService <E> {
	
	private final Class<E> genericType;
	
	@Autowired
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	@SuppressWarnings("unchecked")
	protected BaseService() {
		genericType = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseService.class);
	}
	
	protected abstract JpaRepository<E, Long> getRepository();
	
	public List<E> findAll() {
		return getRepository().findAll();
	}

	
	/**
	 * Busca a entidade pelo id.
	 * @param id
	 * @return A instância da entidade, ou <i>null</i>, se não for encontrada.
	 * @see #findByIdOrThrowException(Long)
	 */
	public E findById(Long id) {
		if (id!=null) {
			Optional<E> entidade = getRepository().findById(id); 
			if (entidade.isPresent()) {
				return entidade.get();
			}
		}
		return null;
	}
	
	/**
	 * Busca a entidade pelo id ou dispara uma exceção EntityNotFoundException.
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	public E findByIdOrThrowException(Long id) {
		E entidade = findById(id);
		if (entidade == null) {
			throw new EntityNotFoundException("Entidade '"+ genericType.getName() +"' com o id '"+id+"' não encontrada");
		}
		return entidade;
	}
}
