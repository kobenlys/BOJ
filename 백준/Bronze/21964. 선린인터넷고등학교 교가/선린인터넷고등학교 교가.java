import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        for (int i = input.length()-5; i < input.length(); i++) {
            System.out.print(input.charAt(i));
        }
    }
}