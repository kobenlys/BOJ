import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args)throws IOException {
        Scanner sc = new Scanner(System.in);

       int[] arr1 = new int[26]; // 알파벳 수 만큼 지정

       String str = sc.next().toUpperCase(); // 출력조건이 대문자이니 대문자로 저장

       for(int i=0; i< str.length(); i++){
           arr1[str.charAt(i)-'A']++; // ascii코드 이용함
       }
       /*
       * 가령  M을 입력 받았다면 ascii로 변환시 M = 77이다 A = 65이다
       * 77 - 65 = 12를 arr1[] 저장한다. 이뜻은 알파벳 12번째 자리는
       * M 이라는 말이다
       * 아래 a 를 구할때 (i+65)를 i 가 '12'라면  12+65 는 77
       * ascii 코드상 'M'을 가르킨다 그 후 저장된 값은 중복수만큼 저장되어있다.
       *
       * */

       int max =0;
       char a = '?';

       for(int i=0; i<26; i++){
           if(arr1[i]>max){
               max = arr1[i];
               a = (char) (i+65);
           }else if(arr1[i]==max){
               a = '?';
           }
       }

        System.out.println(a);
    }
}