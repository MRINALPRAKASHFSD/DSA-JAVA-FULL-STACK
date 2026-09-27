import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Step 1: Pair up the matching parentheses
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        
        // Step 2: Traverse using the wormhole technique
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int dir = 1;
        
        while (i < n) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];
                dir = -dir;
            } else {
                sb.append(s.charAt(i));
            }
            i += dir;
        }
        
        return sb.toString();
    }
}
