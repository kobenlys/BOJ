import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr1 = new int[N]; // 배열 숫자저장.
        int[] number = new int[1_000_001]; // 숫자가 몇번 적혀있는지 저장
        int[] answer = new int[N]; // 답 저장

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            number[arr1[i]]++; // 숫자개수 카운트
        }

        for (int i = 0; i < N; i++) {
            // 배열안에 들어있는 인덱스의 숫자의 갯수가 'i'번째 인덱스의 숫자 갯수보다 작다면
            while (!stk.isEmpty() && number[arr1[stk.peek()]] < number[arr1[i]]) {
                // answer 배열의 해당 인덱스 자리에 'i'번째 숫자 저장.
                answer[stk.pop()] = arr1[i];
            }
            // 인덱스를 저장
            stk.push(i);
        }

        // 출력
        for (int i = 0; i < N; i++) {
            sb.append(answer[i] == 0 ? -1 : answer[i]).append(" ");
        }
        System.out.print(sb);
    }
}