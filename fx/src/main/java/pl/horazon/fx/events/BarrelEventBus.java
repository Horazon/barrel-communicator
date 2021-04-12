package pl.horazon.fx.events;

import com.google.common.eventbus.EventBus;

public class BarrelEventBus {

    private static EventBus eventBus;

    static {
        eventBus = new EventBus();
    }

    public static void post(Object event) {
        eventBus.post(event);
    }

    public static void register(Object object) {
        eventBus.register(object);
    }
}
