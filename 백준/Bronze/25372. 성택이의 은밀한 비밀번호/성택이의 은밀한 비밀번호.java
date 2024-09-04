import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {

            String str = br.readLine();
            if(str.length() >= 6 && str.length() <= 9){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }

        }
    }
}