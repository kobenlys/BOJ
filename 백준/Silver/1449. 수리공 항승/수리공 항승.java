import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 1;

        int[] arr1 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);


        int left =0, right = 0;

        while (right < N) {

            if (arr1[right] - arr1[left] >= M) {
                left = right;
                answer++;
            }else{
                right++;
            }
        }
        //if(right != left) answer++;
        System.out.println(answer);
    }
}