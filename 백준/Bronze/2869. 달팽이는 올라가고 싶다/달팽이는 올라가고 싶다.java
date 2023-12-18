import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken()); // 낮 이동거리
        int B = Integer.parseInt(st.nextToken()); // 밤 이동거리
        int V = Integer.parseInt(st.nextToken()); // 나무 막대 길이

        // A 만큼 이동 하고 B 만큼 떨어진다
        A -= B;
        // 다음 자리부터 날짜를 센다
        V -= B;

        System.out.println(V % A > 0 ? V / A + 1 : V / A);
    }
}