import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[10];
        int set = 1;

        while (N != 0) {
            int temp = N % 10;
            N = N / 10;

            if (arr1[temp] < set) {
                // 현재 가지고있는 세트에 있다면
                arr1[temp]++;
            }else{
               // 숫자가 없다면 세트 하나 사기
                if (temp == 6 && arr1[9] < set) { // 6 -> 9 뒤집기
                    arr1[9]++;
                } else if (temp == 9 && arr1[6] < set) { // 9 -> 6 뒤집기
                    arr1[6]++;
                }else{
                    arr1[temp]++;
                }
                set++;
            }
        }
        Arrays.sort(arr1);
        // 배열에 가장 큰값 출력
        System.out.println(arr1[9]);
    }
}