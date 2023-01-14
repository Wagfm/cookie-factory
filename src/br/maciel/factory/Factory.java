package br.maciel.factory;

import br.maciel.factory.cookies.Cookie;
import br.maciel.factory.cookies.PlainCookie;
import br.maciel.factory.cookies.StuffedCookie;
import br.maciel.factory.daos.BaseQueueDao;
import br.maciel.factory.daos.CookieProcessorDao;
import br.maciel.factory.enums.BaseQueueId;
import br.maciel.factory.enums.CookieProcessorId;
import br.maciel.factory.enums.CookieType;
import br.maciel.factory.enums.IngredientId;
import br.maciel.factory.exceptions.CookieProcessorNotFound;
import br.maciel.factory.exceptions.ProductionQueueNotFound;
import br.maciel.factory.processors.CookieProcessor;
import br.maciel.factory.processors.IngredientMixer;
import br.maciel.factory.processors.Oven;
import br.maciel.factory.queues.InputQueue;
import br.maciel.factory.queues.OutputQueue;
import br.maciel.utilities.ThreadPoolHandler;
import br.maciel.utilities.constants.Simulation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public class Factory implements Runnable {
    private final Map<BaseQueueId, InputQueue> queueMap;
    private final Map<CookieProcessorId, CookieProcessor> processorMap;
    private final List<ScheduledFuture<?>> futures;
    private boolean isRunning;
    private static Factory factory;

    public static Factory getInstance() {
        if (factory == null) factory = new Factory();
        return factory;
    }

    public void add(Map<IngredientId, Double> ingredientsMap, CookieType type) {
        Cookie cookie;
        switch (type) {
            case PLAIN_COOKIE -> cookie = new PlainCookie();
            case STUFFED_COOKIE -> cookie = new StuffedCookie();
            default -> cookie = null;
        }
        if (cookie == null) return;
        for (IngredientId id : ingredientsMap.keySet()) {
            cookie.setRequestedIngredient(id, ingredientsMap.get(id));
        }
        BaseQueueId smallerQueueId = BaseQueueId.QUEUE_A;
        for (BaseQueueId id : cookie.getAllowedQueues())
            if (this.queueMap.get(id).size() <= this.queueMap.get(smallerQueueId).size()) smallerQueueId = id;
        this.queueMap.get(smallerQueueId).add(cookie);
    }

    public BaseQueueDao getInfo(BaseQueueId queueId) {
        InputQueue productionQueue = this.queueMap.get(queueId);
        if (productionQueue == null) throw new ProductionQueueNotFound(queueId);
        return new BaseQueueDao(productionQueue);
    }

    public CookieProcessorDao getInfo(CookieProcessorId cookieProcessorId) {
        CookieProcessor cookieProcessor = this.processorMap.get(cookieProcessorId);
        if (cookieProcessor == null) throw new CookieProcessorNotFound(cookieProcessorId);
        return new CookieProcessorDao(cookieProcessor);
    }

    public double getProgress(CookieProcessorId id) {
        return this.processorMap.get(id).getProgress();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void stop() {
        if (!this.isRunning) return;
        for (ScheduledFuture<?> future : this.futures)
            future.cancel(false);
        this.isRunning = false;
    }

    @Override
    public void run() {
        if (this.isRunning) return;
        for (CookieProcessor processor : this.processorMap.values())
            futures.add(ThreadPoolHandler.getInstance().schedule(processor, Simulation.UPS));
        for (Runnable runnable : this.queueMap.values())
            futures.add(ThreadPoolHandler.getInstance().schedule(runnable, Simulation.UPS));
        ThreadPoolHandler.getInstance().schedule(OutputQueue.getInstance(), Simulation.WPS);
        this.isRunning = true;
    }

    private Factory() {
        this.queueMap = new HashMap<>();
        this.processorMap = new HashMap<>();
        this.futures = new ArrayList<>();
        this.isRunning = false;
        setupOvenMap();
        setupMixerMap();
        setupQueueMap();
    }

    private void setupQueueMap() {
        this.queueMap.put(BaseQueueId.QUEUE_A, new InputQueue(this.processorMap.get(CookieProcessorId.MIXER_A1)));
        this.queueMap.put(BaseQueueId.QUEUE_B, new InputQueue(this.processorMap.get(CookieProcessorId.MIXER_B1)));
        this.queueMap.put(BaseQueueId.QUEUE_C, new InputQueue(this.processorMap.get(CookieProcessorId.MIXER_C1)));
    }

    private void setupOvenMap() {
        this.processorMap.put(CookieProcessorId.OVEN_1, new Oven());
        this.processorMap.put(CookieProcessorId.OVEN_2, new Oven());
    }

    private void setupMixerMap() {
        this.processorMap.put(CookieProcessorId.MIXER_A3, new IngredientMixer(IngredientId.SUGAR, this.processorMap.get(CookieProcessorId.OVEN_1)));
        this.processorMap.put(CookieProcessorId.MIXER_A2, new IngredientMixer(IngredientId.MILK, this.processorMap.get(CookieProcessorId.MIXER_A3)));
        this.processorMap.put(CookieProcessorId.MIXER_A1, new IngredientMixer(IngredientId.FLOUR, this.processorMap.get(CookieProcessorId.MIXER_A2)));

        List<CookieProcessor> b3Ovens = new ArrayList<>();
        b3Ovens.add(this.processorMap.get(CookieProcessorId.OVEN_1));
        b3Ovens.add(this.processorMap.get(CookieProcessorId.OVEN_2));
        this.processorMap.put(CookieProcessorId.MIXER_B3, new IngredientMixer(IngredientId.SUGAR, b3Ovens));

        this.processorMap.put(CookieProcessorId.MIXER_B2, new IngredientMixer(IngredientId.MILK, this.processorMap.get(CookieProcessorId.MIXER_B3)));
        this.processorMap.put(CookieProcessorId.MIXER_B1, new IngredientMixer(IngredientId.FLOUR, this.processorMap.get(CookieProcessorId.MIXER_B2)));
        this.processorMap.put(CookieProcessorId.MIXER_C3, new IngredientMixer(IngredientId.SUGAR, this.processorMap.get(CookieProcessorId.OVEN_2)));
        this.processorMap.put(CookieProcessorId.MIXER_C2, new IngredientMixer(IngredientId.MILK, this.processorMap.get(CookieProcessorId.MIXER_C3)));
        this.processorMap.put(CookieProcessorId.MIXER_C1, new IngredientMixer(IngredientId.FLOUR, this.processorMap.get(CookieProcessorId.MIXER_C2)));
    }
}
