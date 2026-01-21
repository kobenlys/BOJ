class Solution {

    public boolean validatePalindrome(String str) {

        int size = str.length() / 2;

        for (int i = 0; i < size; i++) {
            if (str.charAt(i) != str.charAt((str.length() - 1) - i)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome(String s) {

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            char lowC = Character.toLowerCase(c);
            int asciiNum = lowC - 'a';

            if ((0 <= asciiNum && 25 >= asciiNum) || Character.isDigit(lowC)) {
                sb.append(lowC);
            }
        }

        return validatePalindrome(sb.toString());
    }
}