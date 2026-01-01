class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        digits[len - 1]++;
        for (int i = len - 1; i > 0; i--) {
            if (digits[i] == 10) {
                digits[i] = 0;
                digits[i - 1]++;
            }
        }

        if (digits[0] == 10) {
            int[] tmpArr = new int[len + 1];
            tmpArr[0] = 1;
            return tmpArr;
        }

        return digits;
    }
}