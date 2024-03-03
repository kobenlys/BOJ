import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static node[] arr1;
    public static StringBuilder sb = new StringBuilder();

    public static class node {
        char val;
        node left, right;

        public node(char val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static void preorder(node nd) {
        if (nd == null) return;
        sb.append(nd.val);
        preorder(nd.left);
        preorder(nd.right);
    }

    public static void inorder(node nd) {
        if (nd == null) return;
        inorder(nd.left);
        sb.append(nd.val);
        inorder(nd.right);
    }

    public static void postorder(node nd) {
        if (nd == null) return;
        postorder(nd.left);
        postorder(nd.right);
        sb.append(nd.val);
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (arr1[parent - 'A'] == null) {
                arr1[parent - 'A'] = new node(parent);
            }

            if (left != '.') {
                // arr1에 left 노드를 생성하고
                // arr1에 부모노드.왼쪽에 방금 생선한 left노드를 연결한다.
                arr1[left - 'A'] = new node(left);
                arr1[parent - 'A'].left = arr1[left - 'A'];
            }
            if (right != '.') {
                arr1[right - 'A'] = new node(right);
                arr1[parent - 'A'].right = arr1[right - 'A'];
            }
        }

        preorder(arr1[0]);
        sb.append("\n");
        inorder(arr1[0]);
        sb.append("\n");
        postorder(arr1[0]);

        System.out.print(sb);
    }
}