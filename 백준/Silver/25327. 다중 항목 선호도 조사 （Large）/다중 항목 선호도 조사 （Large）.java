import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[][] arr1 = new String[M][3];
        String[] subject = {"kor", "eng", "math"};
        String[] fruit = {"apple", "pear", "orange"};
        String[] color = {"red", "blue", "green"};


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                tmp.append(st.nextToken());
            }
            map.put(tmp.toString(), map.getOrDefault(tmp.toString(), 0) + 1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr1[i][j] = st.nextToken();
            }
        }

        for (int i = 0; i < M; i++) {
            boolean idx1 = false;
            boolean idx2 = false;
            boolean idx3 = false;
            int res = 0;

            for (int j = 0; j < 3; j++) {
                if (arr1[i][j].equals("-")) {
                    if (j == 0) idx1 = true;
                    if (j == 1) idx2 = true;
                    if (j == 2) idx3 = true;
                }
            }

            if (idx1 || idx2 || idx3) {
                HashSet<String> set = new HashSet<>();
                String tmp1 = arr1[i][0];
                String tmp2 = arr1[i][1];
                String tmp3 = arr1[i][2];

                for (int j = 0; j < 3; j++) {
                    if (idx1) {
                        tmp1 = subject[j];
                    }
                    for (int k = 0; k < 3; k++) {
                        if (idx2) {
                            tmp2 = fruit[k];
                        }
                        for (int l = 0; l < 3; l++) {
                            if (idx3) {
                                tmp3 = color[l];
                            }
                            String tmp = tmp1 + tmp2 + tmp3;

                            if (!map.containsKey(tmp) || set.contains(tmp)) continue;
                            res += map.get(tmp);
                            set.add(tmp);
                        }
                    }
                }
            } else {

                if (map.containsKey(arr1[i][0] + arr1[i][1] + arr1[i][2])){
                    res = map.get(arr1[i][0] + arr1[i][1] + arr1[i][2]);
                }
            }
            sb.append(res).append("\n");
        }

        System.out.println(sb);
    }
}