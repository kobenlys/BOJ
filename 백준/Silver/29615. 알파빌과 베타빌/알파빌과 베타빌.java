import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M,cnt=0;
    static int[] list,friend;

    public static void main(String args[])throws IOException{
        st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        list = new int[N];
        friend = new int[M];
        st = new StringTokenizer(br.readLine()," ");

        for(int i=0;i<N;i++){
            list[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<M;i++){
            friend[i] = Integer.parseInt(st.nextToken());
        }

        for(int i =0;i<M;i++){
            for(int j=0;j<M;j++){
                if(list[j]==friend[i]){
                    cnt++;
                }
            }
        }
        System.out.println(M-cnt);
        
    }
}