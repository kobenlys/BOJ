import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();
        set.add(N);
        set.add(M);
        set.add(K);

        if (N + M + K == 180) {
            if (set.size() == 1) {
                System.out.println("Equilateral");
            }else if (set.size() == 2) {
                System.out.println("Isosceles");
            }else{
                System.out.println("Scalene");
            }
        }else{
            System.out.println("Error");
        }



    }
}