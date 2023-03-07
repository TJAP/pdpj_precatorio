package br.jus.pdpj.precatorio.models.enums;

public enum EventoMensageria {
    
    EXEMPLO_CRIADO("ExemploCriado"),
    EXEMPLO_EDITADO("ExemploEditado");
    //etc...

    private String texto;

	private EventoMensageria(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}

}
