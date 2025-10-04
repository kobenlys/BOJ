class Solution {
    public int solution(String s) {
        int answer = 0;
        
        String[] arr1 = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i=0; i<10; i++){
            s= s.replaceAll(arr1[i], String.valueOf(i));
        }
        
        return Integer.parseInt(s);
    }
}