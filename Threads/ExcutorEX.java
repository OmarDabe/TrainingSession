import java.util.concurrent.*;

public class ExcutorEX {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);  // 3 workers

        for (int i = 1; i <= 6; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Task " + taskNumber + " on " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();   // no new tasks; finish the ones already submitted
    }
}