package br.com.desafio.exception;

public class SondaException extends Exception {

    private Object object;

    public SondaException(String message) {
        super(message);
    }

    public SondaException(Object Object) {
        this.object = Object;
    }

    public Object getObject()	{
        return this.object;
    }
}
