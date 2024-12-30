import java.io.*;
import java.util.*;

public class Main {
    // 부분일치배열(LPS)만들기
    public static int[] makePattern(String str) {
        int[] pattern = new int[str.length()];
        int pos = 0;
        for (int i = 1; i < pattern.length; i++) {
            // 불일치시 pos 조정
            while (pos > 0 && str.charAt(i) != str.charAt(pos)) {
                pos = pattern[pos - 1];
            }
            // 일치시 pos 증가와 LPS배열에 입력
            if (str.charAt(i) == str.charAt(pos)) {
                pattern[i] = ++pos;
            }
        }
        return pattern;
    }
    
    // KMP 문자열 알고리즘 매칭
    public static int kmp(String str1, String str2, int[] pattern, StringBuilder sb) {
        int matchCnt = 0;
        int pos = 0;
        for (int i = 0; i < str1.length(); i++) {
            // 불일치시 pos 포인트 인덱스를 LPS를 이용하여 조정
            while (pos > 0 && str1.charAt(i) != str2.charAt(pos)) {
                pos = pattern[pos - 1];
            }
            
            // 일치시 
            if (str1.charAt(i) == str2.charAt(pos)) {
                if (str2.length() - 1 == pos) { // pos가 str2길이와 같다면
                    sb.append(i - pos + 1).append(" "); // 해당 문자열 처음위치
                    matchCnt++; // 같은 문자열 찾은거임
                    pos = pattern[pos];// pos값 재 조정
                } else {
                    pos++; // pos값을 증가시켜 다음 문자 탐색
                }
            }
        }
        return matchCnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[] pattern = makePattern(str2);
        int cnt = kmp(str1, str2, pattern, sb);
        System.out.println(cnt);
        System.out.println(sb);
    }
}