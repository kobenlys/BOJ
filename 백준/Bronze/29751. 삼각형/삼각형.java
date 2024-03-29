import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        double a = Integer.parseInt(st.nextToken());
        double b = Integer.parseInt(st.nextToken());

        double w = a * b / 2;

        System.out.printf("%.1f",w);
    }
}