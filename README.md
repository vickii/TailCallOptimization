# TailCallOptimization ![CI status](https://img.shields.io/badge/build-passing-brightgreen.svg)

Tail Call optimization support for Java8.


### Requirements
* Java-8
* Maven

### Tech stack
* Java-8
* Maven

### Build
* Clone the project
* Perform mvn clean package
* Add the jar to the classpath.


### PROBLEM ADDRESSED

The biggest hurdle to using recursion is the risk of stack overflow for problems with large inputs. The TCO(Tail Call Optimization) technique can remove that concern. A tail call is a recursive call in which the last operation performed is a call to itself. This is different from a regular recursion, where the function, in addition to making a call to itself, often performs further computations on the result of the recursive call(s). TCO lets us convert regular recursive calls into tail calls to make recursions practical for large inputs.

Java does not directly support TCO at the compiler level, but i have used the laziness of streams in java 8 and lambda expressions to implement it in a few lines of code.

### EXAMPLE

Lets go through an simple example to understand the problem at hand and how TCO solves the problem

### Simple Recursion without TCO :

```

public static int factorial(int n){

if(n==1)
    return 1;
else
    return n * factorial(n-1);
}

```

When u call this method with factorial(5) you get an output of 120 which is correct. The code is also very simple as we have used recursion.But the real problem occurs only when we want to execute with larger inputs factorial(1000) you get an StackOverflow error because each recursive call uses some space on the stack. If your recursion is too deep, then it will result in StackOverflow Exception, depending upon the maximum allowed depth in the stack.

### Recursion with TCO : (Import the jar to your class path to import the TailCall classes)

```
public static TailCall factorial(BigInteger fact, BigInteger n){

if(n.equals(new BigInteger("1")))
    return done(fact);
else
    return call(() -> factorial(fact.multiply(n),n.subtract(new BigInteger("1"))));
}

```

Now when you run for factorial of 1000 you get the correct output instead of an StackOverflow Exception but still the code is written using recursion.

```
factorial(new BigInteger("1"),new BigInteger("1000")).get();

```

We're creating a functional interface called TailCall here. The factorial method returns either a next call (packaged into a TailCall implementer) or indicates the recursion is over by returning a TailCall instance with isComplete as true. We can query the instance returned to know if we should continue or stop.

To begin with we have no clue how many tail calls exist. We turn this into a lazy infinite collection using the Streams' iterate method. We continue with the recursion, iteratively that is, until we reach the terminating call.

Tail call Optimization is provided as a compiler level feature in JVM Languages like Scala but it can implemented in java as well with less code using java8's functional style of programming.


## License
[MIT](https://choosealicense.com/licenses/mit/)
