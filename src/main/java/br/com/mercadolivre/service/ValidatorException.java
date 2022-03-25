package br.com.mercadolivre.service;

public class ValidatorException extends RuntimeException {

    private static final long serialVersionUID = -8154745631348511081L;

    public ValidatorException(String mensagem) {
        super(mensagem);
    }
}
