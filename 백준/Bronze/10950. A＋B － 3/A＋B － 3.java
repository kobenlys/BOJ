import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int A, B;
        String[] arrays;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++){
            arrays = br.readLine().split(" ");
            A = Integer.parseInt(arrays[0]);
            B = Integer.parseInt(arrays[1]);
            System.out.println(A + B);
        }

    }
}