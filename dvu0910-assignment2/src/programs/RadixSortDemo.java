// Assignment 2.

package programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import structures.LinkedQueue;
import structures.QueueADT;

/**
 * To radix sort an array queue read from file.
 * 
 * @author Duy Vu
 * @version 2021
 *
 */
public final class RadixSortDemo {

    /**
     * save max digit.
     */
    private static final int MY_MAX_DIGIT = 10;

    private RadixSortDemo() {

    }

    /**
     * sort to file.
     * 
     * @param theArgs .
     */

    public static void main(final String[] theArgs) {

////        LinkedQueue<Integer> queue = new LinkedQueue<>();
////        int[] arr = { 1, 12, 34, 54, 65, 777, 888, 544, 3, 222 };
//        int[] data = readFiles("in.txt");
//        System.out.println(Arrays.toString(data));
//        for (int i = 0; i < maxDigit(data); i++) {
//            radixSort(queue, i);
//        }
//        LinkedQueue<Integer> inputNumbers = new LinkedQueue<>();
//        inputNumbers.enqueue(4);
//        inputNumbers.enqueue(10);
//        inputNumbers.enqueue(14);
//        inputNumbers.enqueue(200);
//        inputNumbers.enqueue(9);
//        inputNumbers.enqueue(2);
//        inputNumbers.enqueue(100);
//
//        QueueADT<Integer> outputNumbers = radixSort(inputNumbers);
//        while (!outputNumbers.isEmpty()) {
//            System.out.print(outputNumbers.dequeue() + " ");
//        }

        writeToFile();

    }

    /**
     * read file in and write in file output.
     */

    private static void writeToFile() {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the input file name. C to cancel ");

        while (sc.hasNext()) {
            final String chooseFile = sc.nextLine();

            if ("c".equalsIgnoreCase(chooseFile)) {
                System.out.println("You quit");
                sc.close();
                System.exit(1);
                break;

            }
            final QueueADT<Integer> inputNumbers = new LinkedQueue<>();

            try {
                final Scanner scanner2 = new Scanner(new File(chooseFile));
                while (scanner2.hasNext()) {
                    try {
                        final int i = scanner2.nextInt();
                        inputNumbers.enqueue(i);
                    } catch (final InputMismatchException e) {
                        System.out.println("The file has non- Integer value.");
                        break;
                    }
                }
                scanner2.close();

                /**
                 * output to file.
                 */
                final PrintStream output = new PrintStream(new File("output.txt"));
                final QueueADT<Integer> outputQueue = radixSort(inputNumbers);
                while (!outputQueue.isEmpty()) {
                    output.println(outputQueue.dequeue());
                }
                scanner2.close();
                output.close();
            } catch (final FileNotFoundException e1) {
                // TODO Auto-generated catch block
                System.out.println("File not found");
            }
            

        }

    }

    /**
     * find max digit of an integer.
     * 
     * @param theI
     * @return nums max digit.
     */

    private static int getDigits(final int theI) {
//        int digitNums = 0;
//        if (theI < MY_MAX_DIGIT) {
//            digitNums = 1;
//        } else {
//            digitNums += getDigits(theI / MY_MAX_DIGIT);
//
//        }
//        return digitNums;
        if (theI < MY_MAX_DIGIT) {
            return 1;
        }
        return 1 + getDigits(theI / MY_MAX_DIGIT);

    }

    /**
     * find max digit of array.
     * 
     * @param theArr
     * @return maxDigigt
     */

    private static int maxDigit(final QueueADT<Integer> theArr) {
        int maxDigigt = 0;
        int digit = 0;
        int num = 0;
        final int size = theArr.size();
        for (int i = 0; i < size; i++) {
            num = theArr.dequeue().intValue();
            theArr.enqueue(num);
            digit = getDigits(num);
            if (digit > maxDigigt) {
                maxDigigt = digit;
            }

        }

        return maxDigigt;
    }

    /**
     * Method to radix sort the array queue.
     * 
     * @param theNumbers Queue read form file.
     * @return theNumbers queue.
     */

    public static QueueADT<Integer> radixSort(final QueueADT<Integer> theNumbers) {
//        final int index = maxDigit(theNumbers);
//        final List<LinkedQueue<Integer>> queue = new ArrayList<>();
//        for (int i = 0; i < MY_MAX_DIGIT; i++) {
//            queue.add(new LinkedQueue<Integer>());
//        }
//        int count = 1; 
//
//        int x = theNumbers.dequeue();
//        while (!theNumbers.isEmpty()) {
//            final int remain = x % (int) Math.pow(MY_MAX_DIGIT, count);
//            queue.get(remain).enqueue(x);
//            
//        }
//        count++;
//
//        for (int i = 0; i < MY_MAX_DIGIT; i++) {
//            while (!queue.get(i).isEmpty()) {
//                theNumbers.enqueue(queue.get(i).dequeue());
//            }
//        }
//
//        return theNumbers;

        final List<LinkedQueue<Integer>> queueDigits = new ArrayList<>();
        final int index = maxDigit(theNumbers);
        for (int i = 0; i < MY_MAX_DIGIT; i++) {
            queueDigits.add(new LinkedQueue<Integer>());
        }

        for (int i = 0; i <= index; i++) {
            while (!theNumbers.isEmpty()) {
                final Integer current = theNumbers.dequeue();
                final Integer temp = current / (int) Math.pow(MY_MAX_DIGIT, i);
                queueDigits.get(temp % MY_MAX_DIGIT).enqueue(current);

            }

            for (int j = 0; j < MY_MAX_DIGIT; j++) {
                while (!queueDigits.get(j).isEmpty()) {
                    theNumbers.enqueue(queueDigits.get(j).dequeue());
                }
            }

        }
        return theNumbers;
    }
}
