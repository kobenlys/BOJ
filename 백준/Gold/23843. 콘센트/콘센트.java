import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> devices = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> outlet = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            devices.add(Integer.parseInt(st.nextToken()));
        }

        int time = 0;

        while (!devices.isEmpty() || !outlet.isEmpty()) {
            while(!devices.isEmpty() && outlet.size() < M){
                outlet.add(devices.poll() + time);
            }

            time++;
            while(!outlet.isEmpty() && outlet.peek() <= time){
                outlet.poll();
            }
        }

        System.out.println(time);
        // 8 4
        // 4 4
        // 1 1

        // 1 1
        // 4 4
        // 8


    }
}
