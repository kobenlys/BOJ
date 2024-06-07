import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        
        int cnt = 0;
        for(int i = 0; i< 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< N; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 7){
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt==3?777:0);
    }
}