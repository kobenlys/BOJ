import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1;
        StringTokenizer st2;

        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        int sum1 = 0;
        int sum2 = 0;

        for(int i=0; i<4; i++){
            sum1 += Integer.parseInt(st1.nextToken());
            sum2 += Integer.parseInt(st2.nextToken());
        }
        if (sum1 >= sum2) {
            System.out.println(sum1);
        }else{
            System.out.println(sum2);
        }
    }
}