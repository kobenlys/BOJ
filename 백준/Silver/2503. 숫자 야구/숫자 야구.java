import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Integer> arr1 = new ArrayList<>();

        for (int i = 123; i <= 987; i++) {
            String num = String.valueOf(i);
            boolean isDuple = false;
            // 중복된 숫자 또는 '0' 없다면 arr1에 저장한다.
            st:
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == k) continue;
                    if (num.charAt(j) == '0') {
                        isDuple = true;
                        break st;
                    }
                    if (num.charAt(j) == num.charAt(k)) {
                        isDuple = true;
                        break st;
                    }
                }
            }
            if (!isDuple) {
                arr1.add(i);
            }
        }

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");

            String num = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            for (int k = 0; k < arr1.size(); k++) {
                String input = String.valueOf(arr1.get(k));
                int strikeCnt = 0;
                int ballCnt = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        boolean isSame = num.charAt(i) == input.charAt(j);
                        if (i == j) { // 스트라이크 카운팅
                            if (isSame) {
                                strikeCnt++;
                            }
                            continue;
                        }
                        if (isSame) { // 볼 카운팅
                            ballCnt++;
                        }
                    }
                }
                if (strike != strikeCnt || ball != ballCnt) {
                    // 입력받은 스트라이크, 볼 과 조건이 맞지 않다면 arr1에서 해당 숫자 제거.
                    arr1.remove(k);
                    k--;
                }
            }
        }
        System.out.print(arr1.size());
    }
}
