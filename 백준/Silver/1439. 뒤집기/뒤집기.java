import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        int zeroCnt = 0;
        int oneCnt = 0;

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '0') {
                zeroCnt++;
            }else{
                oneCnt++;
            }

            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) != str.charAt(j)) {
                    i = j - 1;
                    break;
                }
            }
        }

        System.out.println(Math.min(zeroCnt, oneCnt));
    }
}