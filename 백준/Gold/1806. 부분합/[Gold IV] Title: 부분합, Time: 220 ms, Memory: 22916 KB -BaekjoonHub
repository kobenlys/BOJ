import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {

            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        while (true) { // 투포인터 알고리즘

            if (sum >= S) {
                min = Math.min(min, right - left);
                sum -= arr1[left++];
               
            } else if (N == right) { // 탈출
                break;
            }else{
                sum += arr1[right++];
                
            }
            


        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }
}
