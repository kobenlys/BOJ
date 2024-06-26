import java.io.*;
import java.util.*;

public class Main {

    public static class node implements Comparable<node> {
        int ps;
        String name;

        public node(int ps, String name) {
            this.ps = ps;
            this.name = name;
        }

        @Override
        public int compareTo(node o) {
            return o.ps - ps;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<node> arr2 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int ps = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            arr1.add(year % 100);
            arr2.add(new node(ps, name));
        }

        Collections.sort(arr1);
        Collections.sort(arr2);

        for (int i = 0; i < 3; i++) {
            sb.append(arr1.get(i));
        }
        sb.append("\n");
        for (node nd : arr2) {
            sb.append(nd.name.charAt(0));
        }
        System.out.println(sb);

    }
}