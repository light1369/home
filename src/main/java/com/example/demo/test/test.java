package com.example.demo.test;

/**
 * @author Duan
 * @date 2020/6/3 - 17:30
 */
public class test {
    public static void main(String[] args) {
//        int i = 999;
//        i--;
//        ++i;
//        System.out.println( i++ );


//        byte b = (byte)129;
//        System.out.println( b );

        for( int i = 4 ; i > 0 ; i-- ){
            int j = 0 ;

            do{
                j++;
                if( j == 2 ){
                    break;
                }
            }while(j<i);
            System.out.print( j );

        }}
}
