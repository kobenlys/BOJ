import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static Node[] arr1;

    public static class Node {
        int x, y, water;

        public Node(int x, int y, int water) {
            this.x = x;
            this.y = y;
            this.water = water;
        }
    }

    public static void removeWater(int y, int idx) {

        arr1[idx].water = 0;

        int tmp = y;

        // 오른쪽
        for (int i = idx + 1; i < N; i++) {
            if (arr1[i].water == 0) break;

            if (tmp >= arr1[i].y) {
                arr1[i].water = 0;
                tmp = arr1[i].y;
            } else {
                arr1[i].water = Math.min(arr1[i].water, arr1[i].y - tmp);
            }
        }

        tmp = y;
        // 왼쪽
        for (int i = idx - 1; i >= 0; i--) {
            if (arr1[i].water == 0) break;

            if (tmp >= arr1[i].y) {
                arr1[i].water = 0;
                tmp = arr1[i].y;
            } else {
                arr1[i].water = Math.min(arr1[i].water, arr1[i].y - tmp);
            }
        }

    }

    public static void printArr(Node[] arr1) {

        System.out.println();
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i].x + " " + arr1[i].y + " " + arr1[i].water + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int answer = 0;
        arr1 = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr1[i] = new Node(x, y, y);
        }

        int M = Integer.parseInt(br.readLine());
        //printArr(arr1);

        while (M-- > 0) {

            st = new StringTokenizer(br.readLine());
            int sX = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int eX = Integer.parseInt(st.nextToken());
            int eY = Integer.parseInt(st.nextToken());

            int idx = 0;

            for (int i = 0; i < N; i++) {
                if (arr1[i].x == sX) {
                    idx = i;
                    break;
                }
            }
            removeWater(sY, idx);
            //printArr(arr1);
        }

        for (int i = 1; i < N - 2; i++) {
            if (arr1[i + 1].y == arr1[i].y) {
                int len = arr1[i + 1].x - arr1[i].x;
                answer += arr1[i].water * len;
            }
        }


        System.out.println(answer);
    }
}