package br.maciel.factory.exceptions;

import br.maciel.factory.enums.CookieProcessorId;

public class CookieProcessorNotFound extends RuntimeException {
    public CookieProcessorNotFound(CookieProcessorId cookieProcessorId) {
        super("Cookie processor Id=" + cookieProcessorId.name() + " not found!");
    }
}
