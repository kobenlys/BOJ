import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        System.out.print((int) (N * 0.78) +" ");

        System.out.println((int) (N * 0.8) + (int) ((N - (int) (N * 0.8)) * 0.78));

    }
}