/*
// HX: 0 points for Final_00
// Final_00 provides [pg2701_char$strmize] for
// constructing a stream of characters in pg2701.txt
*/

import MyLibrary.LnStrm.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Final_00 {
    /* HX: pg2701.txt lives under final/Data/. The working directory may be
	   the repo root (IDE default) or final/MySolution (command-line per
	   README); try candidates in a fixed order. */
    private static File pg2701$txt$locate() {
	String[] paths = {
	    "final/Data/pg2701.txt",
	    "./final/Data/pg2701.txt",
	    "../Data/pg2701.txt",
	    "./../Data/pg2701.txt",
	    "Data/pg2701.txt"
	};
	for (String p : paths) {
	    File f = new File(p);
	    if (f.isFile()) return f;
	}
	return new File(paths[0]);
    }

    public static
	LnStrm<Character> pg2701_char$strmize() {
	String content;
	File myFile = null;
	Scanner myScanner = null;
	myFile = pg2701$txt$locate();
	try {
	    // HX: must specify UTF-8 -- Windows default charset (Cp1252) silently
	    // truncates the read at the first multibyte UTF-8 sequence.
	    myScanner = new Scanner(myFile, "UTF-8");
	    content = myScanner.useDelimiter("\\A").next(); 
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
}
