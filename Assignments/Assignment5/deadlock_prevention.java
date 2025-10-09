
// class Resource {
//     String name;

//     Resource(String name) {
//         this.name = name;
//     }
// }

// public class deadlock_prevention {

//     private static final Resource r1 = new Resource("Resource: R1");
//     private static final Resource r2 = new Resource("Resource: R2");

//     public static void resource1_method() {
//         synchronized (r1) {
//             System.out.println(Thread.currentThread().getName() + " Using resource r1");
//             try {
//                 Thread.sleep(1000);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//             synchronized (r2) {
//                 System.out.println(Thread.currentThread().getName() + " Using resource r2");
//             }
//         }
//     }

//     public static void resource2_method() {
//         synchronized (r2) {
//             System.out.println(Thread.currentThread().getName() + " Using resource r2");
//             try {
//                 Thread.sleep(1000);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }
//             synchronized (r1) {
//                 System.out.println(Thread.currentThread().getName() + " Using resource r1");
//             }
//         }
//     }

//     public static void main(String[] args) {
//         Thread t1 = new Thread(() -> resource1_method(), "Thread 1");
//         Thread t2 = new Thread(() -> resource2_method(), "Thread 2");
//         t1.start();
//         t2.start();
//     }
// }

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class deadlock_prevention {

    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void resource1_method() {
        try {
            if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " Using resource r1");
                Thread.sleep(1000);

                if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " Using resource r2");
                    Thread.sleep(1000);
                    lock2.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + " Fails to get resource r2");
                }
                lock1.unlock();
            } else {
                System.out.println("Failed to get resource r1");
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " Failed to run thread!!!");
        }
    }

    public static void resource2_method() {
        try {
            if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " Using resource r2");
                Thread.sleep(1000);

                if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " Using resource r1");
                    Thread.sleep(1000);
                    lock1.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + " Fails to get resource r2");
                }
                lock2.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + " Failed to get resource r1");
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " Failed to run thread!!!");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> resource1_method(), "Thread 1");
        Thread t2 = new Thread(() -> resource2_method(), "Thread 2");
        t1.start();
        t2.start();
    }
}
