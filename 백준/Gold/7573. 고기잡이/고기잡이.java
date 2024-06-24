import java.io.*;
import java.util.*;

public class Main {
    public static int N, I, M;
    public static ArrayList<node> arr1;
    public static ArrayList<node> netList;

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int cntFish(int x, int y, int netX, int netY) {
        int cnt = 0;

        for (node pos : arr1) {
            if (x <= pos.x && x + netX >= pos.x
                    && y <= pos.y && y + netY >= pos.y) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = 0;

        arr1 = new ArrayList<>();
        netList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            arr1.add(new node(x, y));
        }

        for (int i = 1; i < I / 2; i++) {
            netList.add(new node(i, I / 2 - i));
            netList.add(new node(I / 2 - i, i));
        }



        for (node p1 : arr1) {
            for (node p2 : arr1) {

                for (node net : netList) {
                    int x = p1.x;
                    int y = p2.y;

                    if (x + net.x > N) {
                        x = N - net.x;
                        if (x < 1) continue;
                    }

                    if (y + net.y > N) {
                        y = N - net.y;
                        if (y < 1) continue;
                    }
                    answer = Math.max(answer, cntFish(x, y, net.x, net.y));
                }
            }
        }
        System.out.println(answer);

    }
}