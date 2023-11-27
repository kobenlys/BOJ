import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class Main {
    public static int N, cnt;
    public static int[][] arr1;

    public static void algorithm(){

        int start = arr1[0][1];

        for (int i = 1; i < N; i++) {
            if (start <= arr1[i][0]) {
                start = arr1[i][1];
                cnt++;
            }
        }


    }

    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr1 = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if( o1[1] == o2[1]){
                    return o1[0] - o2[1];
                }
                return o1[1] - o2[1];
            }
        });

        cnt = 1;
        algorithm();
        System.out.println(cnt);

    }
}
