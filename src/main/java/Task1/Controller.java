package Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

public class Controller {

    public static int numberOfBracketsPerRow;

    public static List<String> readBracketsFromConsole(){

        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Enter number of brackets per row : ");
        numberOfBracketsPerRow = consoleScanner.nextInt();
        System.out.println("To stop typing to enter 0");
        String temp;

        /*
         * input number of brackets per row
         */

        List<String> brackets = new ArrayList<>();
        while (consoleScanner.hasNext()){

            temp = consoleScanner.nextLine();
            if (temp.equals("0"))break;
            if (temp.length() > 0)brackets.add(temp);
        }
        /*
         * input brackets line, while console has next line and it is not "0"
         * if the user has entered 0, we stop typing
         * if line is "\n" or there length lower than 1, we do not add to bracket list
         */
        return brackets;
    }

    public static long NumberOfProperlyClosedBrackets(List<String> brackets){

        LongAdder longAdder = new LongAdder();
        CountDownLatch countDownLatch = new CountDownLatch(brackets.size());
        brackets.forEach(s -> new CheckBrackets( s, longAdder, countDownLatch));
        /*
         * create properly closed brackets counter and finished threads counter
         * thread is started for each bracket line by streams and lambda
         */

        try {
            countDownLatch.await();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        /*
         * main thread is waiting while all thread will finish to work
         */
        return longAdder.longValue();
    }
}
