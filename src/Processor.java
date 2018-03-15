/**
 * Created by Mitet0 on 3/15/2018
 */
public class Processor extends Thread {
    private boolean running = true;

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Running " + getName());
        }
    }

    public void terminate() {
        running = false;
    }
}
