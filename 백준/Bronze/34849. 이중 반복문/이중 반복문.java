import java.io.*;
import java.util.*;

public class Main {

 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long N = Integer.parseInt(br.readLine());

        long time = N * N;

        System.out.println(time > 100_000_000 ? "Time limit exceeded" : "Accepted");

    }
}