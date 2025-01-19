import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int price, value;
        public Node(int price, int value) {
            this.price = price;
            this.value = value;
        }
        @Override
        public int compareTo(Node o){
            return price - o.price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Node[] arr1 = new Node[N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr1[i] = new Node(p, v);
        }
        Arrays.sort(arr1);
        long answer = 0;
        long temp = 0;
        int left = 0;
        int right = 0;

        while (right < N) {
            if(arr1[right].price - arr1[left].price < D){
                temp += arr1[right].value;
                answer = Math.max(answer, temp);
                right++;
            }else {
                temp -= arr1[left].value;
                left++;
            }
        }
        System.out.println(answer);
    }
}