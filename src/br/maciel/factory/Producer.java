package br.maciel.factory;

import br.maciel.factory.cookies.Cookie;
import br.maciel.factory.cookies.PlainCookie;
import br.maciel.factory.cookies.StuffedCookie;
import br.maciel.factory.enums.CookieType;

public class Producer implements Runnable {
    private final CookieType cookieType;

    public Producer(CookieType cookieType) {
        super();
        this.cookieType = cookieType;
    }

    @Override
    public void run() {
        Cookie cookie;
        switch (this.cookieType) {
            case PLAIN_COOKIE -> cookie = new PlainCookie();
            case STUFFED_COOKIE -> cookie = new StuffedCookie();
            default -> cookie = null;
        }
        if (cookie == null) return;
        Factory.getInstance().add(cookie);
    }
}
