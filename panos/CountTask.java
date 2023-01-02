package panos;

import java.util.concurrent.ExecutorService;

public class CountTask implements Runnable {
    private ExecutorService executor;

    public CountTask(ExecutorService executor) {
        this.executor = executor;
    }
    @Override
    public void run() {
        int i = 1;
        while (i <= 100) {
            System.out.println(i);
            i++;
        }
        executor.shutdown();
    }
}