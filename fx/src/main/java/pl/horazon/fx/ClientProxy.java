package pl.horazon.fx;

import com.google.common.eventbus.Subscribe;
import pl.horazon.barrel.common.pojo.BarrelMsg;
import pl.horazon.barrel.common.pojo.EndConnectionBarrelMsg;
import pl.horazon.client.SocketClient;
import pl.horazon.fx.events.AppClose;
import pl.horazon.fx.events.BarrelEventBus;

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
    public void endConnection(AppClose msg) {
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
        BarrelEventBus.post(barrelMsg);
    }
}
