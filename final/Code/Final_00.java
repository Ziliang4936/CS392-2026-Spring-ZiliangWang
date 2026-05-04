/*
// HX: 0 points for Final_00
// Final_00 provides [pg2701_char$strmize] for
// constructing a FnList of characters in pg2701.txt
*/

import Library00.FnList.*;
import Library00.LnStrm.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Final_00 {
    public static
	LnStrm<Character> pg2701_char$strmize() {
	String content;
	File myFile = null;
	Scanner myScanner = null;
	myFile = new File("./../Data/pg2701.txt");
	try {
	    myScanner = new Scanner(myFile);
	    content = myScanner.useDelimiter("\\Z").next(); 
	} catch (IOException e) {
	    content = "***FileNotFoundException***";
	} finally {
	    if (myScanner != null) myScanner.close(); 
	}
	/*
	System.out.println("content.length() = " + content.length());
	*/
	return pg2701$helper_char$strmize(content, content.length(), 0);
    }
    private static
	LnStrm<Character>
	pg2701$helper_char$strmize(String cs, int n, int i) {
	return new LnStrm<Character> (
          () -> {
	      if (i >= n) {
		  return new LnStcn<Character>();
	      } else {
		  return new LnStcn<Character>
		      (cs.charAt(i), pg2701$helper_char$strmize(cs, n, i+1));
	      }
	  }
        );
    }
/*
    // HX-2025-12-16: minimal testing
    public static void main(String[] args) {
	Character ch;
	LnStcn<Character> cxs;
	LnStrm<Character> fxs = pg2701_char$strmize();
	int i = 0;
	while (i < 1000) {
	    i += 1;
	    cxs = fxs.eval0(); ch = cxs.hd(); fxs = cxs.tl(); System.out.print(ch);
	}
	return;
    }
*/
}
