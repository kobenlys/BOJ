import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter((new OutputStreamWriter(System.out)));
        int n=Integer.valueOf(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());

        int lv=Integer.valueOf(st.nextToken());
        int rv=Integer.valueOf(st.nextToken());
        int answer= lv!=0 && lv==rv ? 1 : 0;

        for(int i=1; i<n; i++){
            st=new StringTokenizer(br.readLine());
            int l=Integer.valueOf(st.nextToken());
            int r=Integer.valueOf(st.nextToken());
            answer= l!=0 && l==lv ? answer+1 : answer;
            answer= r!=0 && r==rv ? answer+1 : answer;
            answer= l!=0 && l==r ? answer+1 : answer;
            lv= l;
            rv= r;
        }
        bw.write(answer+"");
        bw.flush();
    }
}