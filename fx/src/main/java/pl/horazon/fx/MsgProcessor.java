package pl.horazon.fx;

import com.google.common.eventbus.Subscribe;
import org.tinylog.Logger;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.fx.events.BarrelEventBus;
import pl.horazon.fx.events.NewTxtMsg;

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
    public void stringEvent(NewTxtMsg event) {

        GroupChatMsg msg = new GroupChatMsg(UserContext.login, event.getMsg());

        ClientProxy.getInstance().send(msg);
    }

}
