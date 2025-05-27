import java.io.*;
import java.util.*;

public class Main {

    public static class Node {

        int height, index;

        public Node(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }

    public static class Answer {

        int count, range, number;

        public Answer(int count, int range, int number) {
            this.count = count;
            this.range = range;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Node[] arr1 = new Node[N];
        Answer[] res = new Answer[N];
        Stack<Node> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = new Node(Integer.parseInt(st.nextToken()), i);
            res[i] = new Answer(0, Integer.MAX_VALUE, -1);
        }

        for (int i = 0; i < N; i++) {

            if (stack.isEmpty()) {
                stack.push(arr1[i]);
                continue;
            }

            while (!stack.isEmpty() && stack.peek().height <= arr1[i].height) {
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek().height > arr1[i].height) {
                res[i].count += stack.size();
                res[i].number = stack.peek().index;
                res[i].range = Math.abs(stack.peek().index - arr1[i].index);
                stack.push(arr1[i]);
                continue;
            }
            stack.push(arr1[i]);
        }

        stack.clear();

        for (int i = N - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(arr1[i]);
                continue;
            }

            while (!stack.isEmpty() && stack.peek().height <= arr1[i].height) {
                stack.pop();
            }

            if (!stack.isEmpty() &&  stack.peek().height > arr1[i].height) {
                res[i].count += stack.size();
                if (res[i].range > Math.abs(stack.peek().index - arr1[i].index)) {
                    res[i].number = stack.peek().index;
                }
                stack.push(arr1[i]);
                continue;
            }
            stack.push(arr1[i]);
        }

        for (int i = 0; i < N; i++) {
            sb.append(res[i].count).append(" ").append(res[i].number == -1 ? "" : res[i].number + 1)
                .append("\n");
        }

        System.out.println(sb);
    }
}