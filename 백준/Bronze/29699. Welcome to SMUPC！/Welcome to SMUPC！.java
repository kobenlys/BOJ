import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int num = Integer.parseInt(br.readLine()) -1;

        String str = "WelcomeToSMUPC";
        num %= str.length();

        System.out.println(str.charAt(num));

    }
}