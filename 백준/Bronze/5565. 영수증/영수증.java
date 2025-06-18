import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int A = Integer.parseInt(br.readLine());

        for (int i = 0; i < 9; i++) {
            A -= Integer.parseInt(br.readLine());
        }
        System.out.println(A);
    }
}