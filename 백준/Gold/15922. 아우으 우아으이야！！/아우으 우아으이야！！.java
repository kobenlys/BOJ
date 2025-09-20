import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int answer = 0;

        st = new StringTokenizer(br.readLine());
        int posX = Integer.parseInt(st.nextToken());
        int posY = Integer.parseInt(st.nextToken());
        answer = posY - posX;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (posY < x) {
                answer += y - x;
                posX = x;
                posY = y;
            }else if(posY < y){
                answer += y - posY;
                posY = y;
            }

        }

        System.out.println(answer);

    }
}