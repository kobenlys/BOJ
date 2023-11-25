import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
   
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(br.readLine());

        int flag = m + c;

        if (m + c >= 60) {
            h += flag / 60;
            m = flag % 60;
            if (h >= 24) {
                h = h - 24;
            }
            System.out.println(h+" "+m);
        }else{
            System.out.println(h+" "+flag);
        }

    }
}
