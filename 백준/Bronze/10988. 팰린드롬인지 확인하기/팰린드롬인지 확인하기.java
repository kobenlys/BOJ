import java.util.*;
import java.lang.*;

public class Main {
    public static boolean isPalindrome(char[] arr1, int len){
        for(int i=0; i < len/2; i++){
            if(arr1[i] != arr1[len-1 - i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String input  = sc.next();
        int len = input.length();


        char[] arr1 = input.toCharArray();

        if(isPalindrome(arr1, len)){
            System.out.println("1");
        }else{
            System.out.println("0");
        }
    }
}