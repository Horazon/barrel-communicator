package pl.horazon.fx.events;

import com.google.common.eventbus.EventBus;
import org.tinylog.Logger;

public class BarrelEventBus {

    private static EventBus eventBus;

    static {
        eventBus = new EventBus();
    }

    private BarrelEventBus(){
        // hide public constructor
    }

    public static void post(Object event) {
        eventBus.post(event);
    }

    public static void register(Object object) {
        eventBus.register(object);
    }

    public static void init(){
        Logger.info("Starting evetbuss ...");
        Logger.info("Starting evetbuss ... Completed");
    }
}
