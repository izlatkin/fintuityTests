import com.fintuity.RegisterPage;

import java.util.Stack;

public class MainClass {
    public static void main(String[] args) {
        MainClass p = new MainClass();
        System.out.println(p.isBalanced("{(a,b)}"));
        System.out.println(p.isBalanced("{(a},b)"));
        System.out.println(p.isBalanced("{)(a,b}"));

        System.out.println(p.isBalanced("()"));
        System.out.println(p.isBalanced("[({})]"));
        System.out.println(p.isBalanced("[]()"));
        System.out.println(p.isBalanced("[ab]c{(d5) (11)}"));


        System.out.println(p.isBalanced("("));
        System.out.println(p.isBalanced(")"));
        System.out.println(p.isBalanced("[])"));
        System.out.println(p.isBalanced("a[b]c)d"));
    }

    public static boolean isBalanced(String s) {
        Stack<Character> stack  = new Stack<Character>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '[' || c == '(' || c == '{' ) {
                stack.push(c);
            } else if(c == ']') {
                if(stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else if(c == ')') {
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if(c == '}') {
                if(stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }

        }
        return stack.isEmpty();

    }
}
