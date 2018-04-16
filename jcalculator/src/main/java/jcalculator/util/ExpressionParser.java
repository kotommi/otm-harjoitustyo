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
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.add(c);
                    break;
                case ')':
                    if (stack.pop() != '(') {
                        return false;
                    }   break;
                case '}':
                    if (stack.pop() != '{') {
                        return false;
                    }   break;
                case ']':
                    if (stack.pop() != '[') {
                        return false;
                    }   break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
