import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if(N < 2){
            System.out.println("Before");
        } else if (N == 2) {
            if (M < 18) {
                System.out.println("Before");
            } else if (M > 18) {
                System.out.println("After");
            }else{
                System.out.println("Special");
            }
        }else{
            System.out.println("After");
        }
    }
}