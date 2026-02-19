import Library.MyStack.*;

public class MyStackArrayTest {
    public static void main(String[] args) {
	MyStackArray<Integer> itms =
	    new MyStackArray<Integer>(10);
	itms.push$exn(1);
	itms.push$exn(2);
	itms.push$exn(3);
	itms.pop$exn(); itms.pop$exn();
	itms.push$exn(4);
	itms.push$exn(5);
	itms.System$out$print(); System.out.println();
    	System.out.print("MyStackRev(");
	itms.irforitm
	(
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	); System.out.print(")"); System.out.println();
    }
}
