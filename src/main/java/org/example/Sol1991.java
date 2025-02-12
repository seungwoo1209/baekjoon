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

        Node[] arr = new Node[N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            arr[i] = new Node();
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

    static void preorder(Node n){
        sb.append(n.value);
        if(n.left != null) preorder(n.left);
        if(n.right != null) preorder(n.right);
    }

    private static void inorder(Node n) {
        if(n.left != null) inorder(n.left);
        sb.append(n.value);
        if(n.right != null) inorder(n.right);
    }

    private static void postorder(Node n) {
        if(n.left != null) postorder(n.left);
        if(n.right != null) postorder(n.right);
        sb.append(n.value);
    }
}

class Node {
    public char value;
    public Node left;
    public Node right;

    public Node() {}

    public Node(char value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    static Node Of(char Value, Node Left, Node Right){
        return new Node(Value, Left, Right);
    }
}