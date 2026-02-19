/*
HX-2026-02-05: 10 points
*/
import Library00.FnList.FnList;
import Library00.FnList.FnListSUtil;

public class Assign03_02 {
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of FnList (as a stack)!
	//
	return balencedq_helper(text, 0, FnListSUtil.<Character>nil());
    }

    // Tail-recursive helper: iterate through text using index i,
    // use FnList<Character> as a stack to track open brackets.
    static boolean balencedq_helper(String text, int i, FnList<Character> stk) {
	if (i == text.length()) {
	    // balanced only if the stack is empty at the end
	    return stk.nilq();
	}
	char c = text.charAt(i);
	if (c == '(' || c == '[' || c == '{') {
	    // push opening bracket onto stack
	    return balencedq_helper(text, i + 1, FnListSUtil.cons(c, stk));
	} else {
	    // c must be ')', ']', or '}'
	    if (stk.nilq()) {
		return false; // no matching opener
	    }
	    char top = stk.hd();
	    if ((c == ')' && top == '(') ||
		(c == ']' && top == '[') ||
		(c == '}' && top == '{')) {
		// matched: pop and continue
		return balencedq_helper(text, i + 1, stk.tl());
	    } else {
		return false; // mismatch
	    }
	}
    }

    public static void main(String[] argv) {
		
	// Testing balencedq
	System.out.println("\"()\"       => " + balencedq("()"));         // true
	System.out.println("\"([])\"     => " + balencedq("([])"));       // true
	System.out.println("\"{[()]}\"   => " + balencedq("{[()]}"));     // true
	System.out.println("\"([{}])\"   => " + balencedq("([{}])"));     // true
	System.out.println("\"\"         => " + balencedq(""));           // true
	System.out.println("\"(]\"       => " + balencedq("(]"));         // false
	System.out.println("\"([)]\"     => " + balencedq("([)]"));       // false
	System.out.println("\"{\"        => " + balencedq("{"));           // false
	System.out.println("\")\"        => " + balencedq(")"));           // false
	System.out.println("\"(()())\"   => " + balencedq("(()())"));     // true
	System.out.println("\"[({})]()\" => " + balencedq("[({})]()"));   // true
    }
}
