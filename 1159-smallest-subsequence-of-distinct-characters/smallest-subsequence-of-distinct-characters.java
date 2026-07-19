class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();
        int[] lastIndex = new int[26];
        
        // Record last occurrence of each character
        for (int i = 0; i < n; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        char[] stack = new char[26]; // at most 26 distinct chars
        int top = -1;                 // stack pointer
        boolean[] inStack = new boolean[26];
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int ci = c - 'a';
            
            if (inStack[ci]) continue; // already placed
            
            // Pop larger chars that reoccur later
            while (top >= 0 && stack[top] > c && lastIndex[stack[top] - 'a'] > i) {
                inStack[stack[top] - 'a'] = false;
                top--;
            }
            
            stack[++top] = c;
            inStack[ci] = true;
        }
        
        return new String(stack, 0, top + 1);
    }
}