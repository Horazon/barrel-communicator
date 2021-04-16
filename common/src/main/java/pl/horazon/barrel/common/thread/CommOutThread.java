package pl.horazon.barrel.common.thread;

import pl.horazon.barrel.common.pojo.BarrelMsg;

import java.io.ObjectOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class CommOutThread implements Runnable {

    private final ObjectOutputStream out;
    public ArrayBlockingQueue<BarrelMsg> queue = new ArrayBlockingQueue<BarrelMsg>(10);

    public CommOutThread(ObjectOutputStream out) {
        this.out = out;
    }

    public ArrayBlockingQueue<BarrelMsg> getQueue() {
        return queue;
    }

    @Override
    public void run() {

        while (true) {
            try {
                BarrelMsg barrelMsg = queue.take();

                out.writeObject(barrelMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
