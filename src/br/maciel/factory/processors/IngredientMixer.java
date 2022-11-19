package br.maciel.factory.processors;

import br.maciel.factory.enums.IngredientId;
import br.maciel.utilities.constants.Simulation;

import java.time.Clock;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IngredientMixer extends CookieProcessor implements Runnable {
    private final IngredientId ingredientId;
    private final Queue<CookieProcessor> nextProcessors;

    public IngredientMixer(IngredientId ingredientId, List<CookieProcessor> nextProcessors) {
        super();
        this.ingredientId = ingredientId;
        this.nextProcessors = new LinkedList<>(nextProcessors);
    }

    public IngredientMixer(IngredientId ingredientId, CookieProcessor nextProcessors) {
        this(ingredientId, Collections.singletonList(nextProcessors));
    }

    @Override
    public void run() {
        if (this.canPush()) return;
        if (!this.isProcessing()) {
            this.getCookie().setRequestedIngredient(this.ingredientId, this.getCookie().getRequestedQuantity(this.ingredientId));
            this.setProcessStart();
            this.setProcessing(true);
        } else {
            double requiredTime = this.getCookie().getRequestedQuantity(this.ingredientId) * Simulation.TIME_MS;
            if (Clock.systemDefaultZone().millis() - this.getProcessStart() < requiredTime) return;
            this.tryToPush();
        }
    }

    @Override
    public double getProgress() {
        if (this.getCookie() == null) return 1;
        double requiredTime = this.getCookie().getRequestedQuantity(this.ingredientId) * Simulation.TIME_MS;
        double interval = Clock.systemDefaultZone().millis() - this.getProcessStart();
        return interval / requiredTime;
    }

    private void tryToPush() {
        CookieProcessor processor = this.nextProcessors.poll();
        if (processor == null) return;
        if (processor.canPush()) {
            processor.push(this.getCookie());
            this.setCookie(null);
            this.setProcessing(false);
        }
        this.nextProcessors.add(processor);
    }
}
