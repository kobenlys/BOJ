import java.io.*;
import java.util.*;

public class Main {
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {

            int num = Integer.parseInt(br.readLine());

            if(num % 2 == 0){
                System.out.println(num+" is even");
            }else{
                System.out.println(num+" is odd");
            }

        }

    }
}