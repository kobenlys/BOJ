import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static int min = Integer.MAX_VALUE, len;


    public static void algorithm(){
        int sum=0, left=0, right= len-1;
        String res = "";

        while (left != right) {

            sum = arr1[left] + arr1[right];

            if(min > Math.abs(sum)){
                min = Math.abs(sum);
                res = arr1[left]+" "+arr1[right];
            }

            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            }

            if (sum == 0) {
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
            arr1[i] = Integer.parseInt(st.nextToken()); // ok
        }
        Arrays.sort(arr1);
        algorithm();

    }
}
