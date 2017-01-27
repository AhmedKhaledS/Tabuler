package Letsdo;

import java.util.LinkedList;

public class Digital {
    public LinkedList input (String expr) {
        LinkedList test = new LinkedList();
        int[] group0 = {0};
        int[] group1 = {1,2,4,8,16,32};
        int[] group2 = {3,5,6,9,10,12,17,18,20,24,33,34,36,40,48};
        int[] group3 = {7,11,13,14,19,21,22,25,26,28,35,37,38,41,42,44,49,50,52,56};
        int[] group4 = {15,23,27,29,30,39,43,45,46,51,53,54,57,58,60};
        int[] group5 = {31,47,55,59,61,62};
        int[] group6 = {63};
        int[] exp = firsttoArray(expr);
        sortArray(exp);
        printToConsole(exp);
        for(int i =0;i<exp.length;i++){
            for(int j=i+1;j<exp.length;j++){
                if(isynf3(exp[i],exp[j])){
                    //test.add(addToGroup(exp[i],exp[j]));
                   // check(exp.length,i,j);
                }
            }
        }
        return test;
    }
    public static int[] firsttoArray(String x){
        LinkedList s = new LinkedList();
        for(int i=0;i<x.length();i++){
            int num;
            String a = new String();
            while(i < x.length() && x.charAt(i) != ',' ){
                a+=x.charAt(i);
                i++;
            }
            num = Integer.parseInt(a);
            s.add(num);
        }
        int[] tra = new int[s.size()];
        for(int i=0 ;i<s.size();i++){
            tra[i]=(int) s.get(i);
        }
        return tra;
    }
    public static void sortArray (int[] x){
        int temp1;
        for (int i = 0; i < x.length - 1; i++) {
            for (int j = 1; j < x.length - i; j++) {
                if (x[j - 1] > x[j]) {
                    temp1 = x[j - 1];
                    x[j - 1] = x[j];
                    x[j] = temp1;
                }
            }
        }
    }
    public void printToConsole (int[] x){
        for(int i=0;i<x.length;i++){
            System.out.println(x[i]);
        }   
    }
    public static boolean isynf3(int x,int y){
        int res = y - x;
        while (((res % 2) == 0) && res > 1){
            res /= 2;
        }
        return res == 1;
    }
    public static void main(String[] args) {
        String x = "1,20,3,45,2";
        int [] y = firsttoArray(x);
        sortArray(y);
        for(int i=0;i<y.length;i++){
            System.out.println(y[i]);
        }
        System.out.println(isynf3(0,4));
    }
    /*
    Integer.toBinaryString(number)
    ------------------------------
    int count = 0; 
    int no = 4 ;
    while(no != 0){
    int d = no %2 ;
    if (d == 1 ){
    count ++ ;
    }
    no /=2;
    str += d;
    }
    */
   /*public static void main(String[] args) {
       String x = "0 1 5 9 10 7 13 14 15 29 30 31 55 63";
       int [][] y = StepZero(x);
       int [][] z = StepOne(y);
      System.out.println("------------------------------------------------------------------------");
      System.out.println("one");
       for(int i = 0; i<y.length ; i++){
           System.out.println(y[i][0] + ","+ y[i][1]);
       }
       System.out.println("------------------------------------------------------------------------");
       System.out.println("two");
       for(int i = 0; i<z.length ; i++){
           for(int j = 0; j<z[0].length;j++)
           System.out.println(z[i][j]);
        System.out.println("-------------------------------------------------------------------------");
       }
   }*/
    /*public static String[][] coveringTable(int[] col, int[][] row) {
    String[][] result = new String[col.length + 1][row.length + 1];
    for (int x = 0; x < result.length; x++)
        Arrays.fill(result[x], "");
    for (int i = 1; i < result[0].length; i++) {
        for (int j = 0; j < row[0].length; j++) {
            result[0][i] += row[i - 1][j];
            result[0][i] += ", ";
        }
    }
    result[0][0] = " ";
    for (int i = 1; i < result.length; i++) {
        result[i][0] = "";
        result[i][0] += col[i - 1];
    }
    for (int i=0 ; i < result.length ; i++){
        for (int j=0 ; j <result[i].length ;j++){
            if (i!=0 && j!=0 )
            if ((result[0][j].contains(result[i][0]+",")) || (result[0][j].contains(result[i][0]+" "))){
                result[i][j]="     1     ";
            }
            else {
                result[i][j]="      0     ";
            }
        }
    }
    return result;
}*/
    /* public static void main(String[] args) {
    String x = "1 4 9 11 14 28 23";
    realWork ahmed = new realWork();
    int [][] y = ahmed.StepZero(x);
    int[] c = new int [y.length];
    for(int i=0;i<y.length;i++){
        c[i]=y[i][0];
    }
    int [] a = {1};
    int [][] z = ahmed.StepOne(y);
    String q = getS(a , 4);
    System.out.println(q);
   /*System.out.println("------------------------------------------------------------------------");
   System.out.println("one");
    for(int i = 0; i<y.length ; i++){
        System.out.println(y[i][0] + ","+ y[i][1]);
    }
    System.out.println("------------------------------------------------------------------------");
    System.out.println("two");
    for(int i = 0; i<z.length ; i++){
        for(int j = 0; j<z[0].length;j++)
        System.out.println(z[i][j]);
     System.out.println("-------------------------------------------------------------------------");
    }
    System.out.println("------------------------------------------------------------------------");
    System.out.println("remain");
    for(int i = 0; i<ahmed.remain.length ; i++){
        for(int j = 0; j<ahmed.remain[0].length;j++)
        System.out.println(ahmed.remain[i][j]);
     System.out.println("-------------------------------------------------------------------------");
    }*/
    /*private  int[][] divide_Groups(int[][] x) {
    for(int i = 0 ;i < x.length ; i++) {
        if (x[i][0] == 0){
            x[i][1] = 0;
        }
        else if ( x[i][0] == 1 || x[i][0] == 2 || x[i][0] == 4 || x[i][0] == 8 || x[i][0] == 16 || x[i][0] == 32 || x[i][0] == 64 ){
            x[i][1] = 1;
        }
        else if ( x[i][0] == 3 || x[i][0] == 5 || x[i][0] == 6 || x[i][0] == 9 || x[i][0] == 10 || x[i][0] == 12 || x[i][0] == 17 || x[i][0] == 18 || x[i][0] == 20 || x[i][0] == 24 || x[i][0] == 33 || x[i][0] == 34 || x[i][0] == 36 || x[i][0] == 40 || x[i][0] == 48 ) {
            x[i][1] = 2;
        }
        else if ( x[i][0] == 7 || x[i][0] == 11 || x[i][0] == 13 || x[i][0] == 14 || x[i][0] == 19 || x[i][0] == 21 || x[i][0] == 22 || x[i][0] == 25 || x[i][0] == 26 || x[i][0] == 28 || x[i][0] == 35 || x[i][0] == 37 || x[i][0] == 38 || x[i][0] == 41 || x[i][0] == 42 || x[i][0] == 44 || x[i][0] == 49 || x[i][0] == 50 || x[i][0] == 52 || x[i][0] == 56 ) {
            x[i][1] = 3;
        }
        else if ( x[i][0] == 15 || x[i][0] == 23 || x[i][0] == 27 || x[i][0] == 29 || x[i][0] == 30 || x[i][0] == 39 || x[i][0] == 43 || x[i][0] == 45 || x[i][0] == 46 || x[i][0] == 51 || x[i][0] == 53 || x[i][0] == 54 || x[i][0] == 57 || x[i][0] == 58 || x[i][0] == 60 ) {
            x[i][1] = 4;
        }
        else if ( x[i][0] == 31 || x[i][0] == 47 || x[i][0] == 55 || x[i][0] == 59 || x[i][0] == 61 || x[i][0] == 62) {
            x[i][1] = 5;
        }
        else if ( x[i][0] == 63) {
            x[i][1] = 6;
        }
    }
    return x;
}*/
    }
