package br.com.desafio.exception;

public class PlanetaException extends Exception {
	
	private static final long serialVersionUID = -5762722689460000249L;

	private Object object;

	public PlanetaException(String message) {
		super(message);
	}
	
	public PlanetaException(Object Object) {
		this.object = Object;
	}
	
	public Object getObject()	{
	    return this.object;
	}
}
