package br.maciel.factory.cookies;

import br.maciel.factory.enums.BaseQueueId;

import java.util.Arrays;

public class PlainCookie extends Cookie {
    public PlainCookie() {
        super(Arrays.asList(BaseQueueId.QUEUE_A, BaseQueueId.QUEUE_B, BaseQueueId.QUEUE_C));
    }
}
