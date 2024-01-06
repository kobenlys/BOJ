import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N+1];
        // 리스트 자료형의 성질 i번째 자리에 넣으면 원래 i번 자리의 원소는 뒤로 밀려나고, 저장됨
        // 이용한다.
        ArrayList<Integer> res = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >0 ; i--) {
            res.add(arr1[i],i);
        }

        for (int e : res) {
            System.out.print(e+" ");
        }
    }
}
