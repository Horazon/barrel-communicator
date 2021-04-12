package pl.horazon.barrel.common.thread;

import org.tinylog.Logger;
import pl.horazon.barrel.common.pojo.*;

import java.io.ObjectInputStream;
import java.util.Objects;
import java.util.function.Consumer;

public class CommInThread<T> extends Thread{
    private final ObjectInputStream in;
    private final Consumer<T> consumer;

    private Class endSignal;

    public void setEndSignal(Class endSignal) {
        this.endSignal = endSignal;
    }

    boolean toClose = false;

    public CommInThread(ObjectInputStream in, Consumer<T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {

            Object recivedObject;

            while (!toClose && (recivedObject = in.readObject()) != null) {

                Logger.info("New MSG");

                if (recivedObject.getClass() == endSignal){
                    toClose = true;
                }

                consumer.accept((T) recivedObject);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
