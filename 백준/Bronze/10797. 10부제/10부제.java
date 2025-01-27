import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int num = Integer.parseInt(br.readLine());
        int cnt = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 5; i++) {
            int car = Integer.parseInt(st.nextToken());
            if(num == car) cnt++;
        }
        System.out.println(cnt);



    }
}