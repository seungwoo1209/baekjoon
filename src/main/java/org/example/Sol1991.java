package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol1991 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());

        Node1753[] arr = new Node1753[N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            arr[i] = new Node1753();
            arr[i].value = (char) (65 + i);
        }

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String indexStr = st.nextToken();
            String leftIndex = st.nextToken();
            if(!leftIndex.equals(".")) arr[indexStr.charAt(0) - 65].left = arr[leftIndex.charAt(0) - 65];

            String rightIndex = st.nextToken();
            if(!rightIndex.equals(".")) arr[indexStr.charAt(0) - 65].right = arr[rightIndex.charAt(0) - 65];
        }

        preorder(arr[0]);
        sb.append('\n');
        inorder(arr[0]);
        sb.append('\n');
        postorder(arr[0]);

        System.out.println(sb);
    }

    static void preorder(Node1753 n){
        sb.append(n.value);
        if(n.left != null) preorder(n.left);
        if(n.right != null) preorder(n.right);
    }

    private static void inorder(Node1753 n) {
        if(n.left != null) inorder(n.left);
        sb.append(n.value);
        if(n.right != null) inorder(n.right);
    }

    private static void postorder(Node1753 n) {
        if(n.left != null) postorder(n.left);
        if(n.right != null) postorder(n.right);
        sb.append(n.value);
    }
}
//
class Node {
    public char value;
    public Node1753 left;
    public Node1753 right;

    public Node() {}

    public Node(char value, Node1753 left, Node1753 right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    static Node1753 Of(char Value, Node1753 Left, Node1753 Right){
        return new Node1753(Value, Left, Right);
    }
}