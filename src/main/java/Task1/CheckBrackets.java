package Task1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

public class CheckBrackets implements  Runnable{

    private final String line;
    private final LongAdder counter;
    private final CountDownLatch countDownLatch;

    public CheckBrackets(String line, LongAdder counter, CountDownLatch countDownLatch) {
        this.line = line;
        this.counter = counter;
        this.countDownLatch = countDownLatch;
        new Thread(this).start();
    }

    @Override
    public void run() {

        long counterRegularBrackets = Controller.numberOfBracketsPerRow;
        long counterInverseBrackets = Controller.numberOfBracketsPerRow;
        boolean option = true;

        String[] brackets = this.line.split("");
        for (String str : brackets){
            if (counterInverseBrackets < counterRegularBrackets){
                option = false;
                break;
            }
            if (str.equals("(")){
                counterRegularBrackets--;
            }else{
                counterInverseBrackets--;
            }
        }
        /*
         * bracket string is divided into bracket array
         * count the number of open and closed brackets
         * if the parenthesis is wider than open, the cycle is closed
         */

        if (option && counterInverseBrackets == counterRegularBrackets){
            this.counter.add(1);
        }
        /*
         * if the number of matched and closed brackets falls and all brackets are closed after opening,
         *  then add 1 to counter
         */
        this.countDownLatch.countDown();
    }
}
