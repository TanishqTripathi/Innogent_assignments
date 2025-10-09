import java.util.concurrent.locks.ReentrantReadWriteLock;

class config {
    private String configData = "Initial configuration";
    private final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    private volatile boolean running = true;

    boolean isrunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

    public void read_config(String thread) {
        rwlock.readLock().lock();
        try {
            System.out.println(thread + " is reading: " + configData);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }
    }

    public void write_config(String thread, String new_task) {
        rwlock.writeLock().lock();
        try {
            System.out.println(thread + " Writing task initiated");
            Thread.sleep(1000);
            configData = new_task;
            System.out.println(thread + " Writing task completed: " + configData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }
    }
}

public class read_write {
    public static void main(String args[]) {
        config config = new config();

        for (int i = 1; i <= 3; i++) {
            final String reader_name = "Reader: " + i;
            new Thread(() -> {
                while (config.isrunning()) {
                    config.read_config(reader_name);
                }
            }).start();
        }

        new Thread(() -> {
            int version = 1;
            while (config.isrunning()) {
                config.write_config("Writer", "Writing task: " + version);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                version++;
                if (version == 3) {
                    config.stop();
                    System.out.println("Execution completed !!!");
                    break;
                }
            }
        }).start();
    }
}
