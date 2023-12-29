import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            // N = 1 인 경우는 그냥 출력
            System.out.println("1/1");
        } else {
            // 아닌 경우, 분수 찾기 ( 1/2 부터)
            int temp1 = 1, temp2 = 2, cnt = 1;

            for (int i = 1; i <= 10000000; i++) {
                int flag1 = temp2;

                while (true) { // 오른쪽위 -> 왼쪽 아래 이동
                    temp1++;
                    temp2--;
                    cnt++;
                    if (cnt == N) { // 답 출력 후 종료
                        System.out.println(--temp1 + "/" + ++temp2);
                        System.exit(0);
                    }
                    if (temp1 > flag1) { // "3/0" 일때 3/1로 변환 후 종료
                        temp2++;
                        break;
                    }
                }

                int flag2 = temp1;

                while (true) { // 왼쪽 아래 -> 오른쪽 위
                    temp1--;
                    temp2++;
                    cnt++;
                    if (cnt == N) { // 답 출력 후 종료
                        System.out.println(++temp1 + "/" + --temp2);
                        System.exit(0);
                    }
                    if (temp2 > flag2) { // "0/3" 일때 1/3로 변환 후 종료
                        temp1++;
                        break;
                    }
                }
            }
        }
    }
}