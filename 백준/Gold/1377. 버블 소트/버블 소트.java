import java.io.*;
import java.util.*;

public class Main {
    
    // 번호와, 원래 위치 인덱스 저장
    public static class node implements Comparable<node> {
        int n, idx;

        public node(int n, int idx) {
            this.n = n;
            this.idx = idx;
        }
        @Override
        public int compareTo(node o) {
            return n - o.n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        node[] arr1 = new node[N];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr1[i] = new node(num, i);
        }
        // 오름차순 정렬 
        Arrays.sort(arr1);
        
        for (int i = 0; i < N; i++) {
            node nd = arr1[i];
            // 원래 있던 자리 - 정렬 후 자리 인덱스의 차가 가장 큰 것을 저장
            answer = Math.max(answer, nd.idx - i);
        }
        // 버블 정렬 특징과 관련있음
        System.out.print(answer + 1);
    }
}