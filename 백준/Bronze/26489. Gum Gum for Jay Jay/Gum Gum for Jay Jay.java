import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int cnt = 0;

        while (true) {

            String str = br.readLine();

            if(str == null){
                break;
            }
            cnt++;
        }

        System.out.println(cnt);

    }
}