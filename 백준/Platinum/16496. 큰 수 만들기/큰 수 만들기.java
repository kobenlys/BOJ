import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] arr1 = new String[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = st.nextToken();
        }

        // 문자열 정렬
        Arrays.sort(arr1);
        
        for (int i = 0; i < N; i++) {
            String n1 = arr1[i];
            if (n1.equals("0")) continue;

            for (int j = i + 1; j < N; j++) {
                String n2 = arr1[j];

                if (n1.equals(n2)) continue;

                // 가장 앞 숫자가 같을때 ex) 1, 10
                if (n1.charAt(0) == n2.charAt(0)) {

                    // long타입 오버플로우 가능, bigInteger 사용한다.
                    // 110 , 101 비교해서 arr1배열의 요소 순서 변경하기
                    BigInteger num1 = new BigInteger(n1.concat(n2));
                    BigInteger num2 = new BigInteger(n2.concat(n1));

                    // BigInteger 비교메서드는 1, 0, -1로 반환한다.
                    if (num1.compareTo(num2) > 0) {
                        String tmp = arr1[i];
                        arr1[i] = arr1[j];
                        arr1[j] = tmp;
                        break;
                    }
                }
            }
        }

        // 출력할 가장 큰 수 만들기
        for (int i = N - 1; i >= 0; i--) {
            sb.append(arr1[i]);
        }

        if (sb.toString().charAt(0) == '0') {
            // 0으로 시작한다면 -> 0 하나만 출력
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}