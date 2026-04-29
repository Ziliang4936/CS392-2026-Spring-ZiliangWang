//
// HX-2026-04-28: 50 points
//
/*
A description on Game-of-24 and an accompanying
demo can be found by visiting the following link:
https://github.com/githwxi/XATSHOME/tree/main/contrib/githwxi/pground/proj002%40250507/misc004
Please give a high-level description in English as to
how Game-of-24 can be solved using either DFS or BFS.
Your description should be given in a README file for
this assignment.
1. Please give a DFS-based implementation according to your
   description that should directly use the DFirstEnumerate method.
2. Please give a BFS-based implementation according to your
   description that should directly use the BFirstEnumerate method.
*/
//
import Library00.LnStrm.*;

class UnsupportedOpr
    extends RuntimeException {
    String opr;
    public UnsupportedOpr(String opr) {
	this.opr = opr;
    }
}

abstract class Term {
    public String tag = "Term";
    public abstract double eval();
    // eval() returns the value of the term
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
	this.tag = "TermInt"; this.val = val;
    }
    public double eval() { return val; }
}

class TermOpr extends Term {
    public String opr;
    public Term arg1, arg2;
    public TermOpr(String opr0, Term arg1, Term arg2) {
	this.tag = "TermOpr";
	this.opr = opr0; this.arg1 = arg1; this.arg2 = arg2;
    }
    public double eval() {
	switch (opr) {
	  case "+":
	      return arg1.eval() + arg2.eval();
	  case "-":
	      return arg1.eval() - arg2.eval();
	  case "*":
	      return arg1.eval() * arg2.eval();
	  case "/":
	      return arg1.eval() / arg2.eval();
	}
	throw new UnsupportedOpr(     opr     );
    }
}

public class Quiz02_03 {
//
    public LnStrm<Term> GameOf24_bfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Each solution is represented as a Term
	// that evaluates to 24
	// Note that your solution should be based on
	// BFirstEnumerate implemented in Assign07_01
    }
//
    public LnStrm<Term> GameOf24_dfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Note that your solution should be based on
	// DFirstEnumerate implemented in Assign07_01
    }
//
    // Please add minimal testing code for GameOf24_bfs_solve
    // Please add minimal testing code for GameOf24_dfs_solve
//
} // end of [public class Quiz02_03{...}]
