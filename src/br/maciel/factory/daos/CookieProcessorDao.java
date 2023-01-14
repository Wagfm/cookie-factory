package br.maciel.factory.daos;

import br.maciel.factory.cookies.Cookie;
import br.maciel.factory.processors.CookieProcessor;

public class CookieProcessorDao {
    private final int cookieId;

    public CookieProcessorDao(CookieProcessor cookieProcessor) {
        Cookie cookie = cookieProcessor.getCookie();
        if (cookie == null) this.cookieId = -1;
        else this.cookieId = cookie.getId();
    }

    @Override
    public String toString() {
        if (this.cookieId == -1) return "...";
        return "Id=" + this.cookieId;
    }
}
