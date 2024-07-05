import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException { //조건 입력

        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int a = A;
        int b = B;
        int res1=1, res2=1;
        int i = 2;

        while (true) {

            if(A % i ==0 && B % i ==0){
                A = A / i;
                B = B / i;
                res1 = res1 * i;
            }else {
                i++;
            }
            if (i > A || i > B) {
                break;
            }
        }
        i= 2;
        while (true) {

            if(a % i ==0 && b % i ==0) {
                a = a / i;
                b = b / i;
                res2 = res2 * i;
            }else{
                i++;
            }
            if (i > a || i > b) {
                res2 = res2*(a*b);
                break;
            }
        }


        System.out.println(res1);
        System.out.println(res2);
    }
}
