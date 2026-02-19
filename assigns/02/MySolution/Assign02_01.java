public class Assign02_01 {
    /*
      HX-2025-02-13: 10 points
      The 'int' type in Java is for integers of some fixed precision.
      More precisely, each integer of the type int is represented as a sequence of bits
      of some fixed length. Please write a program that finds this length. Your program
      is expected return the correct answer instantly. Note that you can only use arithmetic
      operations here. In particular, NO BIT-WISE OPERATIONS ARE ALLOWED.
     */

    // fixed the bug: add static keyword to the main method so that 
    // we can run the code and return the correct answer.
    // if we rerutn something we mush add static keyword to the main method.
    public static void main(String[] argv) {
	// Please give your implementation here
    int count = 1;
    int power = 1;

    while (power > 0){
        power = power * 2;
        count++;
    }
    System.out.println("The length of the int type is " + count);
    }
}
