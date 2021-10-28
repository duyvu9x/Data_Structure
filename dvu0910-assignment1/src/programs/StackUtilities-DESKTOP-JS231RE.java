package programs;

import java.util.Scanner;
import java.util.Stack;

import structures.ArrayStack;

public class StackUtilities {
    public static void main(final String[] args) {
        boolean run = false;

        Scanner sc = new Scanner(System.in);
        String opt = null;

        do {
            System.out.print("Please enter a positive integer to convert :");
            final String number = sc.next();
            int n = -1;
            try {
                n = Integer.parseInt(number);
            } catch (Exception e) {
                System.out.println("Not an integer; try again.");
                opt = "Y";
                continue;
            }
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            if (n <= 0) {
                System.out.print("The value must be positive. Please try again.\n");
                opt = "Y";
                continue;
            }

            while (n > 0) {
                final int r = n % 2;

                stack.push(r);
                n /= 2;

            }
            System.out.print("Value = ");
            while (!stack.isEmpty()) {
                System.out.print(stack.pop());

            }

            System.out.println("\n\nDo you want to convert another number? Y/N:");
            opt = sc.next();
            if (!opt.equalsIgnoreCase("Y") && !opt.equalsIgnoreCase("N")) {
                System.out.print("I did not understand your answer.\n");
                opt = "Y";
            }

        } while (opt.equalsIgnoreCase("Y"));

    }
}
