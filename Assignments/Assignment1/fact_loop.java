import java.util.*;

class fact_loop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        System.out.println(fact);
    }
}

// In the factorial program the main method goes in the stack as it is the only
// method to be
// called so it create a stack frame and it runs until the loop is completed and
// than it is poped out of the stack

// Memory stack { Main()}
// {int n, int i}
// {int fact*=1}
//