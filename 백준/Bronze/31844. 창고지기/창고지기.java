import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();

        int boxIdx = str.indexOf('#');
        int flagIdx = str.indexOf('!');
        int robotIdx = str.indexOf('@');

        if (robotIdx < boxIdx && flagIdx < boxIdx) {
            System.out.println(-1);
            System.exit(0);
        }

        if (robotIdx > boxIdx && flagIdx > boxIdx) {
            System.out.println(-1);
            System.exit(0);
        }

        System.out.println(Math.abs(robotIdx - flagIdx)-1);
    }
}