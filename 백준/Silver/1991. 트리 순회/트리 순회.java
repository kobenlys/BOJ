import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static node[] tree;
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
        if(nd == null) return;
        System.out.print(nd.val);
        preorder(nd.left);
        preorder(nd.right);
    }

    public static void inorder(node nd) {
        if(nd == null) return;
        inorder(nd.left);
        System.out.print(nd.val);
        inorder(nd.right);
    }

    public static void postorder(node nd) {
        if(nd == null) return;
        postorder(nd.left);
        postorder(nd.right);
        System.out.print(nd.val);
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        tree = new node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (tree[parent - 'A'] == null) { // 노드 생성
                tree[parent - 'A'] = new node(parent);
            }
            if (left != '.') { // 부모노드에 left노드 연결
                tree[left - 'A'] = new node(left);
                tree[parent - 'A'].left = tree[left - 'A'];
            }
            if (right != '.') { // 부모노드에 right노드 연결
                tree[right - 'A'] = new node(right);
                tree[parent - 'A'].right = tree[right - 'A'];
            }
        }
        // 전위 순회 = root -> left -> right
        preorder(tree[0]);
        System.out.println();
        // 중위 순회 = left -> root -> right
        inorder(tree[0]);
        System.out.println();
        // 후위 순회 = left -> right -> root
        postorder(tree[0]);
    }
}