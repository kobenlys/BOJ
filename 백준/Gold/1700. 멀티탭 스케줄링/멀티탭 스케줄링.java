import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int ans = 0;

        int[] arr1 = new int[K];
        boolean[] plug = new boolean[101];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < K; i++) {

            int tmp = arr1[i];

            if (!plug[tmp]) {
                if (cnt < N) {
                    plug[tmp] = true;
                    cnt++;
                } else {

                    List<Integer> list = new ArrayList<>();
                    for (int j = i + 1; j < K; j++) {
                        if (plug[arr1[j]] && !list.contains(arr1[j])) {
                            list.add(arr1[j]);
                        }
                    }

                    if (list.size() != N) {
                        for (int j = 0; j < plug.length; j++) {
                            if (plug[j] && !list.contains(j)) {
                                plug[j] = false;
                                break;
                            }
                        }
                    } else {
                        int remove = list.get(list.size() - 1);
                        plug[remove] = false;
                    }
                    plug[tmp] = true;
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}