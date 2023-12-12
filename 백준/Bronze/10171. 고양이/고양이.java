import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException { // 값 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sb.append("\\    /\\").append("\n").append(" )  ( ')").append("\n")
                .append("(  /  )").append("\n").append(" \\(__)|");


        System.out.println(sb);
    }
}


