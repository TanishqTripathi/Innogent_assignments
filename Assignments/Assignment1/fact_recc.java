import java.util.*;

public class fact_recc {
    static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(factorial(n));
    }
}

// In the factorial program the main method goes in the stack
// after the new stack frame is created for method factorial(5)
// after again a new stack frame is created for factorial(4) and this goes on
// until
// factorial(0) is reached
// after the out put the stack memory is poped out in the reverse order
// Memory stack {factorial(0)}

// {factorial(0)}
// {factorial(1)}
// {factorial(2)}
// {factorial(3)}
// {factorial(4)}
// {factorial(5)}
// { Main()}
// {int n, int i}

// after this the stack memory is poped out after the method execution is completed