package Algoritm_4;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {  //scanner
        Scanner scanner = new Scanner(System.in);
        String equation1 = scanner.next();
        String equation2 = scanner.next();

        if (isBalanced(equation1)) {
            System.out.println("Уравнение 1 верно.");
        } else {
            System.out.println("Уравнение 1 неверно.");
        }

        if (isBalanced(equation2)) {
            System.out.println("Уравнение 2 верно.");
        } else {
            System.out.println("Уравнение 2 неверно.");
        }
    }
    public static boolean isBalanced(String equation) {
        Stack<Character> stack = new Stack<>();
        for (char c : equation.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
