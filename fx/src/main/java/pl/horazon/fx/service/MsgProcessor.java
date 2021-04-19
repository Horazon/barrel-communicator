package pl.horazon.fx.service;

import com.google.common.eventbus.Subscribe;
import org.tinylog.Logger;
import pl.horazon.barrel.common.pojo.domain.DirectChatMsg;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.fx.UserContext;
import pl.horazon.fx.events.NewDirectMsgFxEvent;
import pl.horazon.fx.events.NewMsgFxEvent;

public class MsgProcessor {
    private static MsgProcessor msgProcessor;

    static {
        msgProcessor = new MsgProcessor();
        BarrelEventBus.register(msgProcessor);
    }

    private MsgProcessor(){
        // hide public constructor
    }

    public static void init(){
        Logger.info("Starting message processor...");
        Logger.info("Starting message processor... Completed");
    }

    @Subscribe
    public void stringEvent(NewMsgFxEvent event) {

        GroupChatMsg msg = new GroupChatMsg(UserContext.login, event.getMsg());

        ClientProxy.getInstance().send(msg);
    }

    @Subscribe
    public void stringEvent(NewDirectMsgFxEvent event) {

        DirectChatMsg msg = new DirectChatMsg(UserContext.login, "null", event.getMsg());

        ClientProxy.getInstance().send(msg);
    }

}
