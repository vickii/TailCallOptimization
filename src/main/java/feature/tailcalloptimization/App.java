package feature.tailcalloptimization;

import java.math.BigInteger;

import static feature.tailcalloptimization.TailCalls.call;
import static feature.tailcalloptimization.TailCalls.done;


public class App 
{

    public static TailCall factorial(BigInteger fact, BigInteger n){

        if(n.equals(new BigInteger("1")))
            return done(fact);
        else
            return call(() -> factorial(fact.multiply(n),n.subtract(new BigInteger("1"))));
    }

    public static int factorial(int n){

        if(n==1)
            return 1;
        else
            return n * factorial(n-1);
    }

    public static void main( String[] args )
    {
        //System.out.println(factorial(new BigInteger("1"),new BigInteger("1000")).get());
        System.out.println(factorial(5));
    }
}
