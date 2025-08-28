import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        int uCnt = 0;
        int dCnt = 0;
        int pCnt = 0;
        int cCnt = 0;

        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            switch (tmp) {
                case 'U':
                    uCnt++;
                    break;
                case 'D':
                    dCnt++;
                    break;
                case 'P':
                    pCnt++;
                    break;
                case 'C':
                    cCnt++;
                    break;
            }
        }

        int compare = (dCnt + pCnt) / 2;
        if ((dCnt + pCnt) % 2 > 0) {
            compare++;

        }
        if (uCnt + cCnt > compare) {
            sb.append("U");
        }

        if (dCnt > 0 || pCnt > 0) {
            sb.append("DP");
        }

        System.out.println(sb);
    }
}
