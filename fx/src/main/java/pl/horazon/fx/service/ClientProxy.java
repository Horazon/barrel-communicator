package pl.horazon.fx.service;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import pl.horazon.barrel.common.pojo.BarrelMsg;
import pl.horazon.barrel.common.pojo.system.EndConnectionBarrelMsg;
import pl.horazon.barrel.common.pojo.system.Init;
import pl.horazon.client.SocketClient;
import pl.horazon.fx.UserContext;
import pl.horazon.fx.events.AppCloseFxEvent;

import java.io.IOException;

public class ClientProxy {

    private static SocketClient client = new SocketClient();

    private static ClientProxy instance;

    public static ClientProxy getInstance() {
        if (instance == null) {
            instance = new ClientProxy();
        }
        return instance;
    }

    public void start() {
        BarrelEventBus.register(instance);

        try {
            client.startConnection("127.0.0.1", 6666);
            client.setMsgConsumer(instance::post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void endConnection(AppCloseFxEvent msg) {
        client.send(new EndConnectionBarrelMsg());
    }

    @Subscribe
    public void endConnection(EndConnectionBarrelMsg msg) {
        try {
            client.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(BarrelMsg barrelMsg){
        client.send(barrelMsg);
    }

    private void post(BarrelMsg barrelMsg) {
        Platform.runLater(() -> BarrelEventBus.post(barrelMsg));
    }

    public void init(String login){
        client.send(new Init(UserContext.login));
    }
}
