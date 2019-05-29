/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch10;

/**
 *
 * @author NeelS
 */
public class ArrayBackwardRecursion {
    //print array backwards using recursion
    public static void stringReverse(String a){
        char[] b = a.toCharArray();
        int length = b.length;
        while(length>0){
            System.out.print(b[b.length-1]);
            a=a.substring(0, a.length()-1);
            stringReverse(a);
            length--;
    }
}
    public static void main(String[] args) {
        stringReverse("Cars");
    }
}