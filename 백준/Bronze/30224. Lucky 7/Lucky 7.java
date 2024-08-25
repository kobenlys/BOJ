import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String num = br.readLine();

        if (num.contains("7")) {
            int tmp = Integer.parseInt(num);

            if(tmp % 7 == 0){
                System.out.println(3);
            }else{
                System.out.println(2);
            }

        }else{
            int tmp = Integer.parseInt(num);

            if(tmp % 7 == 0){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }
    }
}