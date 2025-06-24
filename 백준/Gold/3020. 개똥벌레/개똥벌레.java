import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;

    public static int binarySearch1(int targetPos, List<Integer> targetStone) {
        int left = 0, right = targetStone.size() - 1;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (targetPos >= targetStone.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return targetStone.size() - left;
    }

    public static int binarySearch2(int targetPos, List<Integer> targetStone) {
        int left = 0, right = targetStone.size() - 1;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (targetPos > targetStone.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return targetStone.size() - left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> upStone = new ArrayList<>();
        List<Integer> downStone = new ArrayList<>();
        int minPos = Integer.MAX_VALUE;
        int minPosCount = 1;

        for (int i = 1; i <= N; i++) {
            int len = Integer.parseInt(br.readLine());

            if (i % 2 == 0) {
                upStone.add(len);
            } else {
                downStone.add(len);
            }
        }

        Collections.sort(upStone);
        Collections.sort(downStone);

        for (int i = 0; i < M; i++) {
            int resPos = binarySearch1(i, downStone) + binarySearch2(M - i, upStone);
            if (resPos <= minPos) {
                if (resPos == minPos) {
                    minPosCount++;
                } else {
                    minPosCount = 1;
                }
                minPos = resPos;
            }
        }

        System.out.println(minPos + " " + minPosCount);
    }
}