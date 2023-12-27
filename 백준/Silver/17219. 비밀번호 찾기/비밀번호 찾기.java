import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        // HashMap 에 저장하기
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            map.put(str[0], str[1]);
        }
        // HashMap 에 검색하기
        for (int i = 0; i < M; i++) {
            sb.append(map.get(br.readLine())).append("\n");
        }
        System.out.print(sb);
    }
}