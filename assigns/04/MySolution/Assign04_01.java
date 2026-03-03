/*
HX-2026-02-13: 10 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;

public class Assign04_01 {
    static boolean balencedq(String text) {
        // Wrap the input string in FnStrn
        FnStrn fnText = new FnStrn(text);
        
        // Use a 1-element array to hold the stack so it can be modified inside the lambda
        @SuppressWarnings("unchecked")
        FnList<Character>[] stack = new FnList[1];
        stack[0] = new FnList<Character>(); // Initialize with empty list

        // FnStrnSUtil.forall iterates over the string.
        // It returns false immediately if the lambda returns false.
        boolean res = FnStrnSUtil.forall(fnText, (c) -> {
            if (c == '(' || c == '[' || c == '{') {
                // Push to stack: cons(c, current_stack)
                stack[0] = new FnList<>(c, stack[0]);
                return true;
            } else if (c == ')' || c == ']' || c == '}') {
                // If stack is empty, we have a closing bracket without a match
                if (stack[0].nilq()) return false;
                
                Character top = stack[0].hd();
                boolean match = 
                    (top == '(' && c == ')') ||
                    (top == '[' && c == ']') ||
                    (top == '{' && c == '}');
                
                if (match) {
                    // Pop from stack: tail(current_stack)
                    stack[0] = stack[0].tl();
                    return true;
                } else {
                    // Mismatch found
                    return false;
                }
            }
            // Ignore characters that are not brackets (if any)
            return true;
        });

        // The string is balanced if:
        // 1. forall returned true (no mismatches found during traversal)
        // 2. The stack is empty at the end (no unclosed open brackets)
        return res && stack[0].nilq();
    }

    public static void main(String[] argv) {
        // Testing code
        System.out.println("Should be true:");
        System.out.println("(): " + balencedq("()"));
        System.out.println("([]): " + balencedq("([])"));
        System.out.println("{[]()}: " + balencedq("{[]()}"));
        System.out.println("");
        
        System.out.println("Should be false:");
        System.out.println("(: " + balencedq("("));
        System.out.println("): " + balencedq(")"));
        System.out.println("([)]: " + balencedq("([)]"));
        System.out.println("(((: " + balencedq("((("));
    }
} // end of [public class Assign04_01{...}]
