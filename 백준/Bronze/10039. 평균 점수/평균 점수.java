import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int answer = 0;
        for (int i = 0; i < 5; i++) {
            answer += Math.max(Integer.parseInt(br.readLine()), 40);
        }
        System.out.println(answer / 5);
    }
}
