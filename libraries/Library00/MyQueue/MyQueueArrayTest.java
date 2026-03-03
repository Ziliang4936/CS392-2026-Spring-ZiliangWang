import Library00.MyQueue.*;

public class MyQueueArrayTest {
    public static void main(String[] args) {
	MyQueueArray<Integer> itms = new MyQueueArray(10);
	itms.enque$exn(1);
	itms.enque$exn(2);
	itms.enque$exn(3);
	itms.deque$exn(); itms.deque$exn();
	itms.enque$exn(4);
	itms.enque$exn(5);
	itms.System$out$print(); System.out.println();
    	System.out.print("MyQueueRev(");
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
