/**
 * Assignment 1.
 */

package programs;

import java.util.ArrayList;
import java.util.Scanner;

import structures.ArrayStack;

/**
 * 
 * @author Duy Vu
 * @version 2021
 *
 * application to run.
 */
public final class StackUtilities {
    /**
     * to stop application.
     */
    private static boolean myAns;

    /**
     * method check option is correct.
     * 
     * @param theAl  get the aswer.
     * @param theOpt the opt input.
     * @return myAns true or false.
     */
    private static boolean yesOrNo(final ArrayList<String> theAl, final String theOpt) {
        for (final String s : theAl) {
            if (theOpt.equalsIgnoreCase(s)) {
                myAns = true;
            }
        }
        return myAns;
    }

    /**
     * main method to run everything.
     * 
     * @param theArgs the args.
     */
    public static void main(final String[] theArgs) {
        final ArrayList<String> ans = new ArrayList<String>();
        ans.add("yes");
        ans.add("y");
        ans.add("no");
        ans.add("n");
        final Scanner scanner = new Scanner(System.in);
        String opt = null;
        do {
            System.out.print("Please enter a positive integer to convert :");
            final String number = scanner.next();
            int n = -1;
            try {
                n = Integer.parseInt(number);
            } catch (final NumberFormatException e) {
                System.out.println("Not an integer; try again.");
                continue;
            }

            if (n <= 0) {
                System.out.print("The value must be positive. Please try again.\n");
                opt = "y";
                continue;
            }

            System.out.print("Value = ");
            System.out.println(decimalToBinary(n));

            System.out.println("\n\nDo you want to convert another number? Y/N:");
            opt = scanner.next();
            if (!yesOrNo(ans, opt)) {
                System.out.print("I did not understand your answer.\n");
                opt = "Y";
            }
            if ("no".equalsIgnoreCase(opt) || "n".equalsIgnoreCase(opt)) {
                scanner.close();
            }

        } while ("y".equalsIgnoreCase(opt) || "yes".equalsIgnoreCase(opt));
        System.out.println("Thanks for trying this program.\nHave a great day!");

    }
    
    /**
     * solution to change decimal to binary.
     * 
     * @param theN value input.
     * @return string of binary.
     */
    public static String decimalToBinary(int theN) {
        if (theN <= 0) {
            throw new IllegalArgumentException();
        }
        final ArrayStack<Integer> stack = new ArrayStack<Integer>();
        final StringBuilder sb = new StringBuilder();

        while (theN > 0) {
            final int r = theN % 2;

            stack.push(r);
            theN /= 2;

        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());

        }
        return sb.toString();
    }
}
