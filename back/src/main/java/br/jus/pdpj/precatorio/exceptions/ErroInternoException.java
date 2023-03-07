package br.jus.pdpj.precatorio.exceptions;

/**
 * <p>Classe para funcionar como <code>wrapper</code> de exceções não negociais
 * geradas por outras classes.</p><p>A ideia é evitar o lançamento de exceções genéricas
 * tais como <code>Exception</code>, <code>Error</code> ou <code>RuntimeException</code>,
 * e, em vez disso, lançar a presente exceção com uma mensagem explicativa e, se for
 * o caso, acompanhada da exceção lançada originalmente.</p> 
 * @author adriano.silva
 * 
 */
public class ErroInternoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ErroInternoException(String mensagem) {
		super(mensagem);
	}
	
	public ErroInternoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
