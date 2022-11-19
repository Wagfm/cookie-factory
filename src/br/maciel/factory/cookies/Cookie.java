package br.maciel.factory.cookies;

import br.maciel.factory.enums.BaseQueueId;
import br.maciel.factory.enums.CookieType;
import br.maciel.factory.enums.IngredientId;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Cookie {
    private static final AtomicInteger nextId = new AtomicInteger(1);
    private final int id;
    private final CookieType cookieType;
    private final List<BaseQueueId> allowedQueues;
    private final Map<IngredientId, Double> requestedIngredients;
    private final double multiplier;
    private long processStart;
    private long timeSpent;

    public Cookie(double multiplier, CookieType cookieType, List<BaseQueueId> allowedQueues) {
        this.id = this.getNextId();
        this.cookieType = cookieType;
        this.allowedQueues = new ArrayList<>(allowedQueues);
        this.requestedIngredients = new HashMap<>();
        this.multiplier = multiplier;
        this.processStart = -1;
        this.timeSpent = -1;
    }

    public Cookie(List<BaseQueueId> allowedQueues) {
        this(1.0, CookieType.PLAIN_COOKIE, allowedQueues);
    }

    public int getId() {
        return id;
    }

    public CookieType getCookieType() {
        return this.cookieType;
    }

    public List<BaseQueueId> getAllowedQueues() {
        return this.allowedQueues;
    }

    public double getRequestedQuantity(IngredientId ingredientId) {
        Double request = this.requestedIngredients.get(ingredientId);
        if (request == null) return 0;
        return request;
    }

    public void setRequestedIngredient(IngredientId ingredientId, double quantity) {
        this.requestedIngredients.put(ingredientId, quantity);
    }

    public double getRequiredTime() {
        double accumulator = 0;
        for (IngredientId id : IngredientId.values()) {
            if (this.requestedIngredients.get(id) == null) continue;
            accumulator += this.requestedIngredients.get(id);
        }
        return accumulator * this.multiplier;
    }

    public void setProcessStart() {
        this.processStart = Clock.systemDefaultZone().millis();
    }

    public long getTimeSpent() {
        return this.timeSpent;
    }

    public void setTimeSpent() {
        this.timeSpent = Clock.systemDefaultZone().millis() - this.processStart;
    }

    private int getNextId() {
        return nextId.getAndIncrement();
    }
}
