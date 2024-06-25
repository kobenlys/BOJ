import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        int answer = 0;

        for (int i = 0; i < str.length(); i++) {
            answer += Character.getNumericValue(str.charAt(i));
        }
        System.out.println(answer);
    }
}