import java.io.*;
import java.util.*;


public class Main {
    public static int N, M;
    public static List<Node> cordiX;
    public static List<Node> cordiY;

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int binarySearchX(int x) {
        int idx = 0;
        int left = 0, right = N - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (x <= cordiX.get(mid).x) {
                right = mid - 1;
            } else {
                idx = mid;
                left = mid + 1;
            }
        }
        return idx;
    }

    public static int binarySearchY(int y) {
        int idx = 0;
        int left = 0, right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (y <= cordiY.get(mid).y) {
                right = mid - 1;
            } else {
                idx = mid;
                left = mid + 1;
            }
        }

        return idx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int rbX = 0, rbY = 0;
        long answer = 0;

        cordiX = new ArrayList<>();
        cordiY = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cordiX.add(new Node(x, y));
            cordiY.add(new Node(x, y));

            answer += Math.abs(x) + Math.abs(y);
        }

        Collections.sort(cordiX, (Comparator.comparingInt(o -> o.x)));
        Collections.sort(cordiY, (Comparator.comparingInt(o -> o.y)));

        String str = br.readLine();

        for (int i = 0; i < M; i++) {
            char t = str.charAt(i);
            int idx = 0;

            switch (str.charAt(i)) {
                case ('S'):
                    rbY += 1;
                    idx = binarySearchY(rbY);
                    answer += idx + 1;
                    answer -= N - (idx + 1);

                    break;
                case ('J'):
                    idx = binarySearchY(rbY);
                    rbY -= 1;
                    answer -= idx + 1;
                    answer += N - (idx + 1);

                    break;
                case ('I'):
                    rbX += 1;
                    idx = binarySearchX(rbX);
                    answer += idx + 1;
                    answer -= N - (idx + 1);

                    break;
                case ('Z'):
                    idx = binarySearchX(rbX);
                    rbX -= 1;
                    answer -= idx + 1;
                    answer += N - (idx + 1);
                    break;
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}