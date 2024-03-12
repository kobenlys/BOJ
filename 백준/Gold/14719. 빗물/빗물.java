import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int ans;
    public static ArrayList<Integer> res = new ArrayList<>();

    public static int waterCheck() {

        if (res.size() <= 2) {
            // 서로 다른 높이의 빌딩이 적어도 3개 이상이여야만 빗물이 고일 수 있다.
            return 0;
        }
        Collections.sort(res);
        int sum = 0;
        // 정렬 후 두번째로 큰값 기준으로 그 블록높이 빼주기 -> 물이 고일 수 있는 조건임.
        for (int i = 0; i < res.size() - 2; i++) {
            sum += res.get(res.size() - 2) - res.get(i);
        }
        return sum;
    }

    public static void twoPointer(int len, int[] arr1) {
        int left = 0, right = 0;
        // 투 포인터 알고리즘
        while (right < len) {
            // left자리 블록보다 작거나 같은 블록인 경우 res 배열에 저장
            if (arr1[left] >= arr1[right]) {
                res.add(arr1[right++]);

            } else if (arr1[left] < arr1[right]) {
                // left자리 블록보다 큰 블록 등장 시 고일 수 있는 물량 체크
                res.add(arr1[right]);
                // left ~ right 블록까지 고일 수 있는 최대 물량 체크함.
                ans += waterCheck();
                left = right;
                res.clear();
            } else if (left == right) {
                res.add(arr1[left]);
                right++;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[W];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 투포인터 알고리즘 실행.
        twoPointer(W, arr1);

        if (res.size() > 2) {
            // ex) 4 1 1 2
            // res.size가 2 이상일때는 twoPointer함수가 모든 값을 처리하지 못했다는 증거임.
            // twoPointer 함수는  left 보다 right가 클때 물의 양을 체크하는데
            // 위 경우는 빗물이 고일 수 있음에도 처리를 하지 못함 4 > 2
            // 남아 있는 배열을 뒤집에서 arr1배열에 저장후 twoPointer 함수를 실행하면 된다
            int size = res.size();
            arr1 = new int[size];
            for (int i = 0; i < size; i++) {
                arr1[i] = res.get((size - 1) - i);
            }
            res.clear();
            twoPointer(size, arr1);
            // 마지막까지 처리가 안된 블록이 있다면
            // ex) 1 0 0 1  경우임 , watercheck로 바로 계산한다.
            ans += waterCheck();
        }
        System.out.print(ans);
    }
}