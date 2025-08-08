import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st1;
        StringTokenizer st2;

        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());

        int aCnt = 0;
        int bCnt = 0;
        int whoLastWin = 0; // a team

        for (int i = 0; i < 10; i++) {
            int aScore = Integer.parseInt(st1.nextToken());
            int bScore = Integer.parseInt(st2.nextToken());
            if(aScore > bScore){
                aCnt += 3;
                whoLastWin = -1;
            } else if (aScore < bScore) {
                bCnt += 3;
                whoLastWin = 1;
            }else{
                aCnt++;
                bCnt++;
            }
        }

        System.out.println(aCnt + " " + bCnt);
        if (aCnt > bCnt) {
            System.out.println("A");
        } else if (aCnt < bCnt) {
            System.out.println("B");
        } else {
            if (whoLastWin == -1) {
                System.out.println("A");
            } else if (whoLastWin == 1) {
                System.out.println("B");
            }else{
                System.out.println("D");
            }
        }


    }
}
