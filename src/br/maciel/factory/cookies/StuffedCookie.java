package br.maciel.factory.cookies;

import br.maciel.factory.enums.BaseQueueId;
import br.maciel.factory.enums.CookieType;

import java.util.Arrays;

public class StuffedCookie extends Cookie {
    public StuffedCookie() {
        super(1.2, CookieType.STUFFED_COOKIE, Arrays.asList(BaseQueueId.QUEUE_A, BaseQueueId.QUEUE_B));
    }
}
