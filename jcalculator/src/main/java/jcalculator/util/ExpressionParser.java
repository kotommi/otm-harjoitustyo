/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalculator.util;

import java.util.Stack;

/**
 *
 * @author tomko
 */
public class ExpressionParser {

    public static boolean checkParenthesis(String expression) {
        Stack<Character> stack = new Stack();
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
            } else if (c == ')') {
                if (stack.pop() != '(') {
                    return false;
                }
            } else if (c == '}') {
                if (stack.pop() != '{') {
                    return false;
                }
            } else if (c == ']') {
                if (stack.pop() != '[') {
                    return false;
                }
            }
        }
        System.out.println(stack);
        return stack.isEmpty();
    }
}