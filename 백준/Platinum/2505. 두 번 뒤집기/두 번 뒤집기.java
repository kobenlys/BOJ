import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1;
    public static int[] arr2;
    public static List<node> list = new ArrayList<>();

    public static class node {
        int left, right;

        public node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void front() {
        for (int i = 1; i <= N; i++) {

            if (i != arr1[i]) {
                for (int j = i + 1; j <= N; j++) {
                    if (i == arr1[j]) {
                        list.add(new node(i, j));
                        swapArray(i, j);
                    }
                }
            }
        }
    }

    public static void back() {
        for (int i = N; i >= 1; i--) {

            if (i != arr1[i]) {
                for (int j = i - 1; j >= 1; j--) {
                    if (i == arr1[j]) {
                        list.add(new node(j, i));
                        swapArray(j, i);
                    }
                }
            }
        }
    }

    public static void swapArray(int left, int right) {

        while (left < right) {
            int tmp = arr1[left];
            arr1[left] = arr1[right];
            arr1[right] = tmp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N + 1];
        arr2 = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr1[i] = num;
            arr2[i] = num;
        }

        front();
        if (list.size() == 2) {
            for (node nd : list) {
                sb.append(nd.left + " " + nd.right).append("\n");
            }
        } else if (list.size() == 1) {
            for (node nd : list) {
                sb.append(nd.left + " " + nd.right).append("\n");
            }
            sb.append("1 1");
        } else if (list.isEmpty()) {
            sb.append("1 1").append("\n");
            sb.append("1 1");
        }

        if (list.size() > 2) {
            arr1 = arr2.clone();
            list.clear();
            back();

            if (list.size() == 2) {
                for (node nd : list) {
                    sb.append(nd.left + " " + nd.right).append("\n");
                }
            } else if (list.size() == 1) {
                for (node nd : list) {
                    sb.append(nd.left + " " + nd.right).append("\n");
                }
                sb.append("1 1");
            } else if (list.isEmpty()) {
                sb.append("1 1").append("\n");
                sb.append("1 1");
            }
        }
        System.out.println(sb);
    }
}
