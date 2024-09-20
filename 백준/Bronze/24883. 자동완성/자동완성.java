import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();

        if (str.equals("N") || str.equals("n")) {
            System.out.println("Naver D2");
        }else{
            System.out.println("Naver Whale");
        }
        
    }
}