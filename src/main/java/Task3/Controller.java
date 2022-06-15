package Task3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

public class Controller {

    public static long sumNumberOfFactorial(BigInteger number){

        LongAdder sum = new LongAdder();
        Arrays.stream(number.toString().split("")).parallel().forEach(s -> sum.add(Integer.parseInt(s)));
        /*
         *convert number to string is divided into an array of strings
         *the transforms into a parallel stream
         *turn each element of the array into a number and add to sum
         */
        return sum.longValue();

    }

    public static BigInteger factorial(int number){

        BigInteger numberBigInteger = BigInteger.valueOf(number);
        for (int i = 1; i < number; i++){

            numberBigInteger = numberBigInteger.multiply(BigInteger.valueOf(i));
        }
        return numberBigInteger;
    }
}
