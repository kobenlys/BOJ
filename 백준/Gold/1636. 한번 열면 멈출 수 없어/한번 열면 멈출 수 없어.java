import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    
    // 구현
    public static int[] algorithm(int tmp) {
        int[] list = new int[N + 1];
        int answer = 0;
        list[1] = tmp;

        for (int i = 1; i < N; i++) {

            int tmpS = arr1[i][0];
            int tmpE = arr1[i][1];
            // 절댓값 측정
            int tmp1 = Math.abs(tmp - tmpS);
            int tmp2 = Math.abs(tmp - tmpE);
            
            // tmp가 범위 안이라면 조절할 필요없음.
            if (tmpS <= tmp && tmp <= tmpE) {
                list[i + 1] = tmp;
                continue;
            }
            
            // 절댓값이 가장 작은 범위로 조절한다.
            if (tmp1 > tmp2) {
                list[i + 1] = tmpE;
                tmp = tmpE;
                answer += tmp2;
            } else {
                list[i + 1] = tmpS;
                tmp = tmpS;
                answer += tmp1;
            }
        }
        // 가장 앞 예상수명을 저장후 리턴.
        list[0] = answer;
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int[] answerList = null;
        int min = Integer.MAX_VALUE;
        
        // 시작 ~ 끝 까지, 범위 숫자중 하나가 가장 최적인 시작지점이다.
        for (int i = arr1[0][0]; i <= arr1[0][1]; i++) {
            int[] res = algorithm(i);

            // 최솟값으로 배열 갱신
            if (min > res[0]) {
                min = res[0];
                answerList = res;
            }
        }

        // 출력
        for (int e : answerList) {
            sb.append(e).append("\n");
        }

        System.out.print(sb);
    }
}