import java.io.*;
import java.util.*;

public class Main {

    public static int getNomalDist(List<Integer> list, int capacity){
        int res = 0;
        for (int i = 0; i < list.size(); i+= capacity) {
            int num = list.get(i);
            res += num * 2;
        }
        return res;
    }

    public static int getOptimalDist(List<Integer> list, int capacity){
        int res = 0;
        for (int i = capacity; i < list.size(); i+= capacity) {
            int num = list.get(i);
            res += num * 2;
        }
        if(list.isEmpty()) return 0;
        return res + list.get(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer1;
        int answer2;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int book = Integer.parseInt(st.nextToken());
            if (book > 0) {
                list1.add(book);
            }else{
                list2.add(book * -1);
            }
        }
        Collections.sort(list1, Comparator.reverseOrder());
        Collections.sort(list2, Comparator.reverseOrder());

        answer1 = getNomalDist(list1, M) + getOptimalDist(list2, M);
        answer2 = getNomalDist(list2, M) + getOptimalDist(list1, M);


        System.out.println(Math.min(answer1, answer2));
    }
}