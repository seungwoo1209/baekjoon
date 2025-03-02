package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;

class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        System.out.println(calc(input));
    }

    private static String calc(String input) {
        if(input.length() == 1){
            return  input;
        }

        char[] array = input.toCharArray();

        int index = -1;
        loop:
        while(true){
            Stack<Integer> braketStack = new Stack<>();
            for (int i = array.length - 1; i >= 0; i--) {
                if(array[i] == ')'){
                    braketStack.push(1);
                }
                if(array[i] == '('){
                    braketStack.pop();
                }
                //
                if(braketStack.empty() && (array[i] == '+' || array[i] == '-')){
                    index = i;
                    break loop;
                }
            }

            for (int i = array.length - 1; i >= 0; i--) {
                if(array[i] == ')'){
                    braketStack.push(1);
                }
                if(array[i] == '('){
                    braketStack.pop();
                }
                //
                if(braketStack.empty() && (array[i] == '*' || array[i] == '/')){
                    index = i;
                    break loop;
                }
            }

            array = Arrays.copyOfRange(array,1,array.length - 1);
        }

        return calc(new String(Arrays.copyOfRange(array,0,index)))
                + calc(new String(Arrays.copyOfRange(array,index+1,array.length)))
                + array[index];

    }

}

// A*B*C*D*E
// A*B+C*D*E
// A*(B+C)*D*E
// (A*B+C)*(D*E)