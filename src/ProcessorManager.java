import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mitet0 on 3/16/2018
 */
public class ProcessorManager {
    private List<Processor> threads = new ArrayList<>();

    public ProcessorManager() {
        startApplicationCounter();
    }

    public void executeCommand(String command) {
        if (command == null) {
            return;
        }
        if (isShutdown(command)) {
            System.out.println("Shutting down...");
            System.exit(0);
        }
        String appName = getApplicationName(command);
        if (isStart(command)) {
            start(appName);
        } else if (isStop(command)) {
            stop(appName);
        }
    }

    private void startApplicationCounter() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threads.size() + " programs are running.");
            }
        });
        t.start();
    }

    private void start(String appName) {
        Processor t = new Processor();
        t.setName(appName);
        t.start();
        threads.add(t);
    }

    private void stop(String appName) {
        final Optional<Processor> thread = threads.stream().filter(t -> t.getName().equals(appName)).findFirst();
        if (thread.isPresent()) {
            thread.get().terminate();
            thread.ifPresent(t -> threads.remove(t));
        }
    }

    private boolean isShutdown(String command) {
        return Commands.SHUTDOWN.toString().equalsIgnoreCase(command);
    }

    private boolean isStart(String command) {
        return command.toUpperCase().startsWith(Commands.START.toString());
    }

    private boolean isStop(String command) {
        return command.toUpperCase().startsWith(Commands.STOP.toString());
    }

    private String getApplicationName(String command) {
        return command.split("\\s+")[1];
    }
}
