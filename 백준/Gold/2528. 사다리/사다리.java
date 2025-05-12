import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;

    public static class Stick {

        int left, right;
        boolean direction; // false = left -> right, true = left <- right

        public Stick(int left, int right, boolean direction) {
            this.left = left;
            this.right = right;
            this.direction = direction;
        }

        public boolean isInRange(Stick topStick) {
            return !(this.right < topStick.left || this.left > topStick.right);
        }
        
        public void move() {
            if (direction) {
                if (left == 0) {
                    this.direction = false;
                    return;
                }
                this.left--;
                this.right--;
            } else {
                if (right == M) {
                    this.direction = true;
                    return;
                }
                this.left++;
                this.right++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Stick> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            if (dir == 0) {
                list.add(new Stick(0, len, false));
            } else {
                list.add(new Stick(M - len, M, true));
            }
        }

        int nowIndex = 0;
        int time = 0;

        while (nowIndex < list.size()-1) {

            Stick bottom = list.get(nowIndex);
            Stick top = list.get(nowIndex+1);

            if (bottom.isInRange(top)) {
//                System.out.println("time : " +  time);
//                System.out.println(top.left + " " + top.right);
//                System.out.println(bottom.left + " " + bottom.right);
//                System.out.println();
                nowIndex++;
                continue;
            }

            time++;
            for (Stick stick : list) {
                stick.move();
            }
        }

        System.out.println(time);
    }
}