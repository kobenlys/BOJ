import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char A = br.readLine().charAt(0);

        if(A == 'M') System.out.println("MatKor");
        if(A == 'W') System.out.println("WiCys");
        if(A == 'C') System.out.println("CyKor");
        if(A == 'A') System.out.println("AlKor");
        if(A == '$') System.out.println("$clear");

    }
}