import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> map = new HashMap<>();
        StringTokenizer st;

        //1 = 가위, 2 = 바위, 3 = 보
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 이기는 경우만.
        map.put(1, 3);
        map.put(2, 1);
        map.put(3, 2);
        System.out.println(map.get(A) == B ? "A" : "B");
    }
}