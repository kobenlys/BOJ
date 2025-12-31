class Solution {
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stk.push(c);
                continue;
            }

            if(stk.isEmpty()) {
                return false;
            }

            if (stk.peek() != map.get(c)) {
                return false;
            }
            stk.pop();
        }

        return stk.isEmpty();
    }
}