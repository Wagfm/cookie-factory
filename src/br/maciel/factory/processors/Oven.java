package br.maciel.factory.processors;

import br.maciel.factory.cookies.Cookie;
import br.maciel.factory.queues.OutputQueue;
import br.maciel.utilities.constants.Simulation;

import java.time.Clock;

public class Oven extends CookieProcessor implements Runnable {

    public Oven() {
        super();
    }

    @Override
    public double getProgress() {
        Cookie cookie = this.getCookie();
        if (this.getProcessStart() < 0 || cookie == null) return 1;
        double requiredTime = this.getCookie().getRequiredTime() * Simulation.TIME_MS;
        double interval = Clock.systemDefaultZone().millis() - this.getProcessStart();
        return interval / requiredTime;
    }

    @Override
    public void run() {
        if (this.canPush()) return;
        if (!this.isProcessing()) {
            this.setProcessStart();
            this.setProcessing(true);
        } else {
            double requiredTime = this.getCookie().getRequiredTime() * Simulation.TIME_MS;
            if (Clock.systemDefaultZone().millis() - this.getProcessStart() <= requiredTime) return;
            this.getCookie().setTimeSpent();
            OutputQueue.getInstance().add(this.getCookie());
            System.out.println("Sent data from Id=" + this.getCookie().getId());
            this.setCookie(null);
            this.setProcessing(false);
        }
    }

}
