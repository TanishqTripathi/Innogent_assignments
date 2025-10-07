import java.util.*;

class Calculator {
    static int counter = 0;
    int limit;

    Calculator(int limit) {
        this.limit = limit;
    }

    public synchronized void odd_calculation() throws InterruptedException {
        while (counter <= limit) {
            if (counter % 2 == 1) {
                System.out.println("Given number " + counter + " is: " + Thread.currentThread().getName());
                counter++;
                notify();
            } else {
                wait();
            }
        }
    }

    public synchronized void even_calculation() throws InterruptedException {
        while (counter <= limit) {
            if (counter % 2 == 0) {
                System.out.println("Given number " + counter + " is: " + Thread.currentThread().getName());
                counter++;
                notify();
            } else {
                wait();
            }
        }
    }
}

public class Odd_even {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            try {
                System.out.println("Enter a number: ");
                n = sc.nextInt();
                if (n <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter a number greater then ZERO!!!");
            }
            sc.nextLine();
        }
        Calculator cal1 = new Calculator(n);

        Thread t1 = new Thread(() -> {
            try {
                cal1.even_calculation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.setName("Even: ");

        Thread t2 = new Thread(() -> {
            try {
                cal1.odd_calculation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.setName("Odd: ");

        t1.start();
        t2.start();
    }
}
