import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr1 = new int[N][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String str = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            map.put(i, str);
            arr1[i][0] = i;
            arr1[i][1] = day;
            arr1[i][2] = month;
            arr1[i][3] = year;
        }

        Arrays.sort(arr1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[3] == o2[3] && o1[2] != o2[2]) {
                    return o1[2] - o2[2];
                } else if (o1[3] == o2[3]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[3] - o2[3];
                }
            }
        });

        System.out.print(map.get(arr1[N-1][0])+"\n"+map.get(arr1[0][0]));
    }
}
