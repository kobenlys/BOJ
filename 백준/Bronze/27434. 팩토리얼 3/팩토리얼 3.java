import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static BigInteger dfs(int start, int end) {
        if(start == end) return BigInteger.valueOf(end);
        return dfs(start, (start + end) / 2).multiply(dfs((start + end) / 2 + 1, end));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());

        BigInteger answer = N == 0 ? BigInteger.valueOf(1) : dfs(1, N);
        System.out.println(answer);
    }
}