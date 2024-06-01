import java.io.*;
import java.util.*;

public class Main {
    public static int limitTop, limitBottom;
    public static boolean[] primeN;
    public static ArrayList<Integer> primeList = new ArrayList<>();
    public static HashSet<Long> sumSet = new HashSet<>();
    public static HashSet<Long> multiSet = new HashSet<>();

    public static void E_Seive() {
        primeN[1] = true;

        for (int i = 2; i <= Math.sqrt(limitTop); i++) {
            for (int j = i + i; j <= limitTop; j += i) {
                primeN[j] = true;
            }
        }

        for (int i = 2; i <= limitTop; i++) {
            if (!primeN[i]) {
                primeList.add(i);
            }
        }
    }

    public static void primeSum() {
        for (int i = 0; i < primeList.size(); i++) {
            for (int j = 0; j < primeList.size(); j++) {
                if(i == j) continue;
                long num = primeList.get(i) + primeList.get(j);
                if(num > limitTop) break;
                sumSet.add(num);
            }
        }
    }

    public static void primeMulti() {
        for (int i = 0; i < primeList.size(); i++) {
            for (int j = 0; j < primeList.size(); j++) {
                long num = (long) primeList.get(i) * primeList.get(j);
                if(num > limitTop) break;
                multiSet.add(num);
            }
        }
    }

    public static boolean validateDupliecate(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i+1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;

        for (int i = 9; i > 9 - K; i--) {
            limitTop *= 10;
            limitTop += i;
        }

        if (K == 1) {
            limitBottom = 1;
        } else {
            limitBottom = 10;
            K -= 2;
            for (int i = 2; i < K+2; i++) {
                limitBottom *= 10;
                limitBottom += i;
            }
        }
        primeN = new boolean[limitTop + 1];
        E_Seive();
        primeSum();
        primeMulti();

        for (long i = limitBottom; i <= limitTop; i++) {
            if (!validateDupliecate(String.valueOf(i))) {

                long target = i;

                while (target % M == 0) {
                    target /= M;
                }

                if (sumSet.contains(i) && multiSet.contains(target)) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}