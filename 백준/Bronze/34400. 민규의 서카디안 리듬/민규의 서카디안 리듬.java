import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {

            int time = Integer.parseInt(br.readLine());

            time %= 25;

            if (time < 17) {
                System.out.println("ONLINE");
            } else {
                System.out.println("OFFLINE");
            }
        }
    }
}