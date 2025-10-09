import java.util.concurrent.CountDownLatch;

public class CountDownLatch_example {
    public static void main(String args[]) {
        int n = 3;
        CountDownLatch latch = new CountDownLatch(n);

        Runnable loading = () -> {
            try {
                System.out.println("Loading Service: -- Service Started");
                Thread.sleep(1000);
                System.out.println("Loading Serive: -- Service Finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread " + Thread.currentThread().getName() + " Finished");
                latch.countDown();
            }
        };

        Runnable configuration = () -> {
            try {
                System.out.println("configuration Service: -- configuration Started");
                Thread.sleep(1000);
                System.out.println("configuration Serive: -- configuration Finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread " + Thread.currentThread().getName() + " Finished");
                latch.countDown();
            }
        };

        Runnable database = () -> {
            try {
                System.out.println("database connection started: -- connection established");
                Thread.sleep(1000);
                System.out.println("database connection finished -- database connection ended");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread " + Thread.currentThread().getName() + " Finished");
                latch.countDown();
            }
        };

        new Thread(loading).start();
        new Thread(configuration).start();
        new Thread(database).start();

        System.out.println("Main thread on hold...");

        try {
            latch.await();
            System.out.println("Thread " + Thread.currentThread().getName() + " Started");
            System.out.println("Thread " + Thread.currentThread().getName() + " Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
