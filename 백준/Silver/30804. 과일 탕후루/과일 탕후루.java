import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> box = new ArrayList<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        int[] arr1 = new int[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int cnt = 0, max = 0;
        int temp = 0;

        while (right < N) {
            if (box.size() < 2) {
                if (box.size() == 1) {
                    if (box.get(0) != arr1[right]) {
                        box.add(arr1[right]);
                        temp = arr1[right];
                        left = right;
                    }
                } else {
                    box.add(arr1[right]);
                }
                cnt++;
                right++;
                max = Math.max(max, cnt);

            } else if (box.size() == 2) {

                if (box.get(0) == arr1[right]) {
                    if (box.get(0) != temp) {
                        temp = box.get(0);
                        left = right;
                    }
                    cnt++;
                    right++;
                } else if (box.get(1) == arr1[right]) {
                    if (box.get(1) != temp) {
                        temp = box.get(1);
                        left = right;
                    }
                    cnt++;
                    right++;
                } else {
                    right = left;
                    box.clear();
                    cnt = 0;
                }
                max = Math.max(max, cnt);
            }


        }

        System.out.println(max);


    }
}