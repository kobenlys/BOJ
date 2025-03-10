import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int yangnum = Integer.parseInt(st.nextToken());
        int huraide = Integer.parseInt(st.nextToken());

        int answer1 = B * (Math.min(yangnum, huraide) * 2);
        if(B * 2 < A && yangnum > huraide){
            answer1 += B * (yangnum - huraide) * 2;
        } else if (B * 2 < C && yangnum < huraide) {
            answer1 += B * (huraide - yangnum) * 2;
        }else{
            answer1 += yangnum > huraide ? A * (yangnum - huraide) : C * (huraide - yangnum);
        }

        int answer2 = yangnum * A + huraide * C;
        System.out.println(Math.min(answer1, answer2));
    }
}