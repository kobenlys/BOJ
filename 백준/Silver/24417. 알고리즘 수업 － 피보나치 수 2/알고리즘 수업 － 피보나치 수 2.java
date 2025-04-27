import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

         int fir = 1;
         int sec = 1;
         int result = 0;

         if(num == 1 || num ==2){
             result=1;
         }else{
             for(int i = 3; i <= num; i++){
                 result = (fir+sec) % MOD;
                 fir = sec;
                 sec = result;
             }
         }

        System.out.println(result+" " + (num-2));
    }


}