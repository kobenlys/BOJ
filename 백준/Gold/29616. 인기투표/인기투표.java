import java.io.*;
import java.util.*;

public class Main {

    public static int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }

    public static int gcdArray(int[] tmp) {
        int res = tmp[0];

        for (int i = 1; i < tmp.length; i++) {
            res = GCD(res, tmp[i]);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        long beforeRes = 0;
        long afterRes = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        // 배열안 모든 숫자에 대해 GCD 구하기
        int gcd1 = gcdArray(arr1);
        int gcd2 = gcdArray(arr2);

        for (int i = 0; i < N; i++) {
            arr1[i] /= gcd1;
            beforeRes += arr1[i]; // 가장 처음 인원수 최소 비율 구한다.
        }

        for (int i = 0; i < N; i++) {
            arr2[i] /= gcd2; // 현재 인원수 최소 비율 구한다
        }

        long max = 0; // 가장 크게 줄어든 비율을 구한다.
        for (int i = 0; i < N; i++) {
            int n = (int) (Math.ceil((double) arr1[i] / arr2[i]));
            max = Math.max(max, n);
        }
        
        // 가장 크게 줄어든 비율을 기준으로 곱해서 현재 인원비율을 구한다
        for (int i = 0; i < N; i++) {
            afterRes += arr2[i] * max;
        }

        System.out.println(beforeRes + " " + afterRes);
    }
}