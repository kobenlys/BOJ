import java.util.*;
import java.io.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);
        
        
        for(int i=0; i<phone_book.length-1; i++){
            boolean isOk = true;
            String str1 = phone_book[i];
            String str2 = phone_book[i+1];
           
            
            int len = Math.min(str1.length(), str2.length());
            for(int j=0; j<len; j++){
                if(str1.charAt(j) != str2.charAt(j))  {
                    isOk = false;
                    break;
                } 
            }
            
            if(isOk) return false;
        }
        
        
        return true;
    }
}