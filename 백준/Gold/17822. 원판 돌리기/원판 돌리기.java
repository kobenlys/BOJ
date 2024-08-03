import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, sum, cnt;
    public static ArrayList<ArrayList<Integer>> arr1 = new ArrayList<>();

    public static void spinCircleBorad(int num, int dir, int freq) {

        for (int i = num; i <= N; i += num) {

            if (dir == 0) { // 시계방향 돌리기
                for (int j = 0; j < freq; j++) {
                    arr1.get(i).add(0, arr1.get(i).get(M - 1));
                    arr1.get(i).remove(M);
                }

            } else { // 반시계방향 돌리기
                for (int j = 0; j < freq; j++) {
                    arr1.get(i).add(arr1.get(i).get(0));
                    arr1.get(i).remove(0);
                }
            }
        }
    }

    public static boolean updateColummNum(int target, int boardNum, int startIdx) {
        boolean isUpdate = false;

        for (int i = boardNum + 1; i <= N; i++) {

            if (target == arr1.get(i).get(startIdx)) {
                sum -= target;
                cnt--;
                arr1.get(i).set(startIdx, -1);
                updateRowNum(target, i, startIdx);
                isUpdate = true;
            } else {
                break;
            }
        }

        for (int i = boardNum - 1; i >= 1; i--) {

            if (target == arr1.get(i).get(startIdx)) {
                sum -= target;
                cnt--;
                arr1.get(i).set(startIdx, -1);
                updateRowNum(target, i, startIdx);
                isUpdate = true;
            } else {
                break;
            }
        }

        return isUpdate;
    }

    public static boolean updateRowNum(int target, int boardNum, int startIdx) {
        boolean isUpdate = false;

        for (int i = startIdx + 1; i < startIdx + M; i++) {

            if (target == arr1.get(boardNum).get(i % M)) {
                sum -= target;
                cnt--;
                arr1.get(boardNum).set(i % M, -1);
                updateColummNum(target, boardNum, i % M);
                isUpdate = true;
            } else {
                break;
            }

        }

        int idx = startIdx - 1;
        for (int i = 0; i < M; i++) {

            if (idx < 0) {
                idx = M - 1;
            }

            if (target == arr1.get(boardNum).get(idx)) {
                sum -= target;
                cnt--;
                arr1.get(boardNum).set(idx, -1);
                updateColummNum(target, boardNum, idx);
                isUpdate = true;
            } else {
                break;
            }
            idx--;
        }
        return isUpdate;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                sum += n;
                cnt++;
                arr1.get(i).add(n);
            }
        }


        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int pan = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int freq = Integer.parseInt(st.nextToken());

            boolean isUpdate = false;

            spinCircleBorad(pan, dir, freq % M);



            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {

                    if (arr1.get(i).get(j) > 0) {
                        int target = arr1.get(i).get(j);

                        if (updateRowNum(target, i, j) | updateColummNum(target, i, j)) {
                            if (arr1.get(i).get(j) > 0) {
                                sum -= arr1.get(i).get(j);
                                cnt--;
                                arr1.get(i).set(j, -1);
                            }
                            isUpdate = true;
                        }
                    }
                }
            }

            if (sum == 0) break;

            if (!isUpdate) {
                double avg = (double) sum / cnt;
                for (int i = 1; i <= N; i++) {
                    for (int j = 0; j < M; j++) {
                        int num = arr1.get(i).get(j);
                        if (num == -1) continue;

                        if (num > avg) {
                            num--;
                            sum--;
                            arr1.get(i).set(j, num);

                        } else if (num < avg) {
                            num++;
                            sum++;
                            arr1.get(i).set(j, num);
                        }
                    }
                }
            }

        }

        

        System.out.println(sum);
    }
}