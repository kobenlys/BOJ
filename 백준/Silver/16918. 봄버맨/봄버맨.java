import java.io.*;
import java.util.*;

public class Main {

    public static int C, R;
    public static int[][] arr1;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static void printArr(StringBuilder sb){
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                if(arr1[i][j] == 0){
                    sb.append(".");
                }else{
                    sb.append("O");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public static void fillArr(){
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                arr1[i][j]++;
            }
        }
    }
    
    public static void removeArr(){
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                if(arr1[i][j] >= 2){
                    list.add(new int[]{j, i});
                }
            }
        }
        for (int[] a : list) {
            arr1[a[1]][a[0]] = 0;
            for (int k = 0; k < 4; k++) {
                int nx = a[0] + dx[k];
                int ny = a[1] + dy[k];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(arr1[ny][nx] >= 2) continue;
                arr1[ny][nx] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        arr1 = new int[C][R];


        for (int i = 0; i < C; i++) {
            String str = br.readLine();
            for (int j = 0; j < R; j++) {
                arr1[i][j] = str.charAt(j) == 'O' ? 1 : 0;
            }
        }

        if (N == 1) {
            printArr(sb);
            return;
        }

        for (int i = 2; i <= N; i++) {
            if(i % 2 == 0){
                fillArr();
            }else{
                removeArr();
            }
        }
        
        printArr(sb);
    }
}