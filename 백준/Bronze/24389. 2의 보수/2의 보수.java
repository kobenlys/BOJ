import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int pos = ~N + 1;

        //XOR 연산을 통해 비트가 다를때 1을 올려준다, 즉 1의 개수만 세면 몇개의 비트가 다른 지 알 수 있음
        pos ^= N;
        System.out.println(Integer.bitCount(pos));
    }
}