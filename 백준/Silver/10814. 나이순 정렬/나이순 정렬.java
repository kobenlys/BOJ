import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // member의 가입순, 나이, 이름을 담기 위한 객체
    public static class member implements Comparable<member>{
        int seq;
        int age;
        String name;

        public member(int seq, int age, String name) {
            this.seq = seq;
            this.age = age;
            this.name = name;
        }
        // 문제 조건 : 나이순 정렬, 나이가 같다면 가입순 정렬
        @Override
        public int compareTo(member o) {
            if (age == o.age) {
                // 나이가 같다면
                return seq - o.seq;
            }else{
                // 나이가 같지 않다면
                return age - o.age;
            }
        }
    }

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        // 우선순위 큐 사용
        PriorityQueue<member> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            // i = 가입순, a = 나이, n = 이름
            pq.offer(new member(i, a, n));
        }

        for (int i = 0; i < N; i++) {
            member m = pq.poll();
            sb.append(m.age).append(" ").append(m.name).append("\n");
        }
        System.out.print(sb);
    }
}