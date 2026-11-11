import java.util.concurrent.*;

public class CoffeeShopThreads {

    // ===== Part A: race condition =====
    static class Counter {
        int unsafe = 0;
        int safe = 0;
        void incrementUnsafe() { unsafe++; }                  // NOT thread-safe
        synchronized void incrementSafe() { safe++; }         // one thread at a time
    }

    // ===== Part B: the shop =====
    static class Inventory {
        private int beans;
        Inventory(int beans) { this.beans = beans; }

        // check-then-act must be one unit, so it is synchronized
        synchronized boolean takeBeans() {
            if (beans > 0) {
                pause(5);
                beans--;
                return true;
            }
            return false;
        }

        synchronized int getBeans() { return beans; }
    }

    // A job that implements Runnable, with run() written out
    static class OrderJob implements Runnable {
        private final int id;
        private final Inventory inventory;

        OrderJob(int id, Inventory inventory) {
            this.id = id;
            this.inventory = inventory;
        }

        @Override
        public void run() {
            String worker = Thread.currentThread().getName();
            if (inventory.takeBeans()) {
                pause(20);   // brewing time
                System.out.println("Order #" + id + " served by " + worker);
            } else {
                System.out.println("Order #" + id + " REJECTED: out of beans");
            }
        }
    }

    static void pause(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public static void main(String[] args) throws Exception {

        // ---------- Part A: Thread + Runnable ----------
        System.out.println("=== Part A: race condition ===");
        Counter counter = new Counter();

        Runnable job = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementUnsafe();
                counter.incrementSafe();
            }
        };

        Thread t1 = new Thread(job);
        Thread t2 = new Thread(job);
        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Unsafe (expected 200000): " + counter.unsafe);
        System.out.println("Safe   (expected 200000): " + counter.safe);

        // ---------- Part B: ExecutorService ----------
        System.out.println("\n=== Part B: coffee shop ===");
        Inventory inventory = new Inventory(15);          // beans for only 15 drinks
        ExecutorService baristas = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 20; i++) {                   // 20 orders arrive
            baristas.execute(new OrderJob(i, inventory));
        }

        baristas.shutdown();
        baristas.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\nBeans left: " + inventory.getBeans());
    }
}