package pl.horazon.barrel.common.thread;

import pl.horazon.barrel.common.pojo.BarrelMsg;

import java.io.ObjectOutputStream;
import java.util.concurrent.SynchronousQueue;

public class CommOutThread implements Runnable {

    private final ObjectOutputStream out;
    public SynchronousQueue<BarrelMsg> queue = new SynchronousQueue<BarrelMsg>();

    public CommOutThread(ObjectOutputStream out) {
        this.out = out;
    }

    public SynchronousQueue<BarrelMsg> getQueue() {
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
