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
// HIGH-LEVEL STRATEGY (used by both the DFS and BFS solvers):
//
//   The "state" of an in-progress Game-of-24 game is the list of
//   Terms still on the table. Initially, this list contains four
//   TermInt(n1)..TermInt(n4). A "move" combines any two terms s, t
//   in the list using one of the binary operators +, -, *, /,
//   producing a new TermOpr that takes their place; this lowers
//   the number of terms by one. After three such moves, exactly
//   one Term remains, and the player wins iff that term evaluates
//   to 24.
//
//   We model the search space as an FnGtree<FnList<Term>>:
//     - the value() of every node is the current list of terms;
//     - the children() of a node is the list of all states
//       reachable in exactly one move (every ordered choice of
//       two operands plus every operator, skipping nothing).
//
//   The complete set of finished games is therefore precisely the
//   set of leaves (states with one term left). We enumerate the
//   tree using either FnGtreeSUtil.DFirstEnumerate (depth-first)
//   or FnGtreeSUtil.BFirstEnumerate (breadth-first), keep only
//   those nodes whose term-list has length 1 and evaluates to 24,
//   and return the surviving Terms as the answer stream.
//
import Library00.LnStrm.*;
import Library00.FnList.*;
import Library00.FnGtree.*;

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
    public abstract String show();
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
	this.tag = "TermInt"; this.val = val;
    }
    public double eval() { return val; }
    public String show() { return Integer.toString(val); }
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
    public String show() {
	return "(" + arg1.show() + opr + arg2.show() + ")";
    }
}

class GameOf24Node implements FnGtree<FnList<Term>> {
    private FnList<Term> terms;
    public GameOf24Node(FnList<Term> terms0) {
	this.terms = terms0;
    }
    public FnList<Term> value() { return terms; }
    public FnList<FnGtree<FnList<Term>>> children() {
	return Quiz02_03.makeChildren(terms);
    }
}

public class Quiz02_03 {

    static int listLen(FnList<Term> xs) {
	int n = 0;
	while (xs.consq()) { n += 1; xs = xs.tl(); }
	return n;
    }

    static Term[] toTermArray(FnList<Term> xs) {
	int n = listLen(xs);
	Term[] arr = new Term[n];
	int k = 0;
	while (xs.consq()) { arr[k] = xs.hd(); k += 1; xs = xs.tl(); }
	return arr;
    }

    static FnList<Term>
	listFromArrayExcept(Term[] arr, int skip1, int skip2) {
	FnList<Term> r = new FnList<Term>();
	for (int k = arr.length - 1; k >= 0; k -= 1) {
	    if (k != skip1 && k != skip2) r = new FnList<Term>(arr[k], r);
	}
	return r;
    }

    @SuppressWarnings("unchecked")
    static FnList<FnGtree<FnList<Term>>> makeChildren(FnList<Term> ts) {
	Term[] arr = toTermArray(ts);
	int n = arr.length;
	FnList<FnGtree<FnList<Term>>> kids =
	    new FnList<FnGtree<FnList<Term>>>();
	if (n <= 1) return kids;
	String[] ops = new String[] {"+", "-", "*", "/"};
	for (int i = 0; i < n; i += 1) {
	    for (int j = i + 1; j < n; j += 1) {
		FnList<Term> rest = listFromArrayExcept(arr, i, j);
		for (int op = 0; op < ops.length; op += 1) {
		    Term combined1 = new TermOpr(ops[op], arr[i], arr[j]);
		    FnList<Term> ns1 = new FnList<Term>(combined1, rest);
		    kids = new FnList<FnGtree<FnList<Term>>>
			(new GameOf24Node(ns1), kids);
		    if (ops[op].equals("-") || ops[op].equals("/")) {
			Term combined2 = new TermOpr(ops[op], arr[j], arr[i]);
			FnList<Term> ns2 = new FnList<Term>(combined2, rest);
			kids = new FnList<FnGtree<FnList<Term>>>
			    (new GameOf24Node(ns2), kids);
		    }
		}
	    }
	}
	return kids;
    }

    private FnGtree<FnList<Term>> initialTree(int n1, int n2, int n3, int n4) {
	FnList<Term> start =
	    new FnList<Term>(new TermInt(n1),
	      new FnList<Term>(new TermInt(n2),
		new FnList<Term>(new TermInt(n3),
		  new FnList<Term>(new TermInt(n4),
		    new FnList<Term>()))));
	return new GameOf24Node(start);
    }

    private static LnStrm<Term> only24(LnStrm<FnList<Term>> all) {
	LnStrm<FnList<Term>> finished =
	    LnStrmSUtil.filter0(all, (FnList<Term> xs) -> {
		    if (!xs.consq()) return false;
		    if (xs.tl().consq()) return false;
		    double v = xs.hd().eval();
		    if (Double.isNaN(v) || Double.isInfinite(v)) return false;
		    return Math.abs(v - 24.0) < 1e-9;
		});
	return LnStrmSUtil.map0(finished, (FnList<Term> xs) -> xs.hd());
    }
//
    public LnStrm<Term> GameOf24_bfs_solve
	(int n1, int n2, int n3, int n4) {
	FnGtree<FnList<Term>> root = initialTree(n1, n2, n3, n4);
	LnStrm<FnList<Term>> all = FnGtreeSUtil.BFirstEnumerate(root);
	return only24(all);
    }
//
    public LnStrm<Term> GameOf24_dfs_solve
	(int n1, int n2, int n3, int n4) {
	FnGtree<FnList<Term>> root = initialTree(n1, n2, n3, n4);
	LnStrm<FnList<Term>> all = FnGtreeSUtil.DFirstEnumerate(root);
	return only24(all);
    }
//
    private static int countAndShow(LnStrm<Term> sols, int max) {
	int[] cnt = new int[]{0};
	int[] shown = new int[]{0};
	LnStrmSUtil.foritm0(sols, (Term t) -> {
		cnt[0] += 1;
		if (shown[0] < max) {
		    System.out.println("  " + t.show() + " = " + t.eval());
		    shown[0] += 1;
		}
	    });
	return cnt[0];
    }

    public static void main (String[] args) {
	Quiz02_03 game = new Quiz02_03();

	System.out.println("--- DFS solutions for (4,4,10,10) ---");
	LnStrm<Term> dfs1 = game.GameOf24_dfs_solve(4, 4, 10, 10);
	int cnt1 = countAndShow(dfs1, 5);
	System.out.println("  total = " + cnt1);

	System.out.println("--- BFS solutions for (4,4,10,10) ---");
	LnStrm<Term> bfs1 = game.GameOf24_bfs_solve(4, 4, 10, 10);
	int cnt1b = countAndShow(bfs1, 5);
	System.out.println("  total = " + cnt1b);

	System.out.println("--- DFS solutions for (1,3,4,6) ---");
	LnStrm<Term> dfs2 = game.GameOf24_dfs_solve(1, 3, 4, 6);
	int cnt2 = countAndShow(dfs2, 5);
	System.out.println("  total = " + cnt2);

	System.out.println("--- BFS solutions for (1,1,1,1) (no solution) ---");
	LnStrm<Term> bfs3 = game.GameOf24_bfs_solve(1, 1, 1, 1);
	int cnt3 = countAndShow(bfs3, 5);
	System.out.println("  total = " + cnt3);
    }
//
} // end of [public class Quiz02_03{...}]
