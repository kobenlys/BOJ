import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 이진수로 변환
        String str = Integer.toBinaryString(Integer.parseInt(br.readLine()));
        
        // 이진수에서 가장 오른쪽 숫자가 "1"이라면 홀수이다.
        if (str.charAt(str.length()-1) == '0') {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}