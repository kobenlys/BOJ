import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static int min = Integer.MAX_VALUE, len;


    public static void algorithm(){ // 투포인터 알고리즘 구현
        int sum=0, left=0, right= len-1;
        String res = "";

        while (left != right) {

            sum = arr1[left] + arr1[right];

            if(min > Math.abs(sum)){ // min 과 절댓값 비교 = -1, 1은 0과 거리는 같음
                min = Math.abs(sum);
                res = arr1[left]+" "+arr1[right];
            }

            if (sum > 0) { //sum이 크다면 오름차순이기때문에 right--;
                right--;
            } else if (sum < 0) { // sum이 작다면 오름차순이기때문에 left++;
                left++;
            }

            if (sum == 0) { // 0과 같다면 res 저장 후 while문 break
                res = arr1[left]+" "+arr1[right];
                break;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        len = Integer.parseInt(br.readLine());

        arr1 = new int[len];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < len; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1); // 오름차순 정렬
        algorithm();

    }
}
