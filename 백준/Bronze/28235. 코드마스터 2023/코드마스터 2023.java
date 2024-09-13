import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        String str = br.readLine();

        if(str.equals("SONGDO")){
            System.out.println("HIGHSCHOOL");
        }else if(str.equals("CODE")){
            System.out.println("MASTER");
        }else if(str.equals("2023")){
            System.out.println("0611");
        }else{
            System.out.println("CONTEST");
        }
        

    }
}
