import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());


        if(A > B) {
            System.out.println("flight");
        }else {
            System.out.println("high speed rail");
        }


    }
}
