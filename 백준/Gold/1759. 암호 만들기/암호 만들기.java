import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int L, C;
    public static char[] arr1;
    public static boolean[] vi;
    public static char[] isVowel = {'a', 'e', 'i', 'o', 'u'};
    public static StringBuilder sb = new StringBuilder();

    public static void algorithm(int start, int j, String res) { // 백트래킹 알고리즘

        if (start == L) {
            int vowel = 0;
            // 모음, 자음 카운팅
            for (int i = 0; i < 5; i++) {
                String key = String.valueOf(isVowel[i]);
                if (res.contains(key)) {
                    vowel++;
                }
            }
            // 최종 암호길이 - 모음 카운팅 = 자음 개수
            if (L - vowel >= 2 && vowel >= 1) {
                sb.append(res).append("\n");
            }
            return;
        }

        // 사전순으로 만들 수 있는 모든 경우 문자열 생성.
        for (int i = j; i < C; i++) {
            String tmp = res;
            res += arr1[i];
            algorithm(start + 1, i + 1, res);
            res = tmp;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr1 = new char[C];
        vi = new boolean[C];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            arr1[i] = st.nextToken().charAt(0);
        }
        // 사전순 출력 위해.
        Arrays.sort(arr1);
        algorithm(0, 0, "");
        System.out.print(sb);
    }
}
