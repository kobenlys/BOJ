import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static boolean isPossible = false;
    public static String strA = "AAAA";
    public static String strB = "BB";
    public static int len;

    public static void dfs(String str) {
        if (isPossible) { // 가장 처음만 출력한다
            return;
        }
        if (len <= str.length()) { // 문자열 길이가 같다면 출력, 크다면 리턴
            if (len == str.length()) {
                sb.append(str);
                isPossible = true; // 만들 수 있다면 true
            }
            return;
        }

        dfs(str + strA); // AAAA 폴리오미노 사용하는 경우
        dfs(str + strB); // BB 폴리오미노 사용하는 경우
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int cnt = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                cnt++; // X 카운팅

            } else if (input.charAt(i) == '.') {
                len = cnt;

                if (cnt != 0) {
                    dfs(""); // 함수 실행

                    if (!isPossible) { // 조건 1. 딱 맞게 만들 수 없다면 -1 출력
                        System.out.println(-1);
                        System.exit(0);
                    }
                    isPossible = false;
                    cnt = 0;
                }
                sb.append(".");
            }
        }

        if (cnt != 0) { // 문자열 마지막이 X로 끝난 경우
            len = cnt;
            dfs("");
            if (!isPossible) { // 조건 1번 과 동일.
                System.out.println(-1);
                System.exit(0);
            }
        }

        System.out.print(sb);
    }
}
