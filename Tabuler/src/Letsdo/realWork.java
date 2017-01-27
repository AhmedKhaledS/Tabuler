package Letsdo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class realWork {
     int[][] remain;
     int number_bits=4; 
     LinkedList<int[][]> di = new LinkedList<int[][]> ();
     LinkedList<boolean[]> ch = new LinkedList<boolean[]>();
     public  int[] Min(String expr) {
         LinkedList<Integer> res = new LinkedList<Integer>() ;
         for(int i = 0 ;i < expr.length() ;i++) {
             StringBuilder num = new StringBuilder();
             while ((i<expr.length())&&(Character.isDigit(expr.charAt(i)))) {
                 num.append(expr.charAt(i));
                 i++;
             }
             if (num.length() >= 1) {
                 res.add(Integer.parseInt(num+""));
             }
         }
         int [] ready = new int[res.size()];
         for (int i = 0 ; i < res.size() ; i++) {
             ready[i] = (int)res.get(i);
         }
         //number_bits = no_bits(ready);
         return ready;
     }
    public  int[][] StepZero(String expr) {
        LinkedList<Integer> res = new LinkedList<Integer>() ;
        for(int i = 0 ;i < expr.length() ;i++) {
            StringBuilder num = new StringBuilder();
            while ((i<expr.length())&&(Character.isDigit(expr.charAt(i)))) {
                num.append(expr.charAt(i));
                i++;
            }
            if (num.length() >= 1) {
                res.add(Integer.parseInt(num+""));
            }
        }
        int [][] ready = new int[res.size()][2];
        for (int i = 0 ; i < res.size() ; i++) {
            ready[i][0] = (int)res.get(i);
        }
        return sort(Group(ready));
    }    
    public  int[][] StepOne(int[][] x) {
        sort2(x);
        LinkedList<Integer> ahmed = new LinkedList<Integer>();
        boolean [] check = new boolean[x.length];
        Arrays.fill(check, Boolean.FALSE);
        for(int i = 0 ;i<x.length;i++){
            for(int j = i+1;j<x.length;j++){
                if (x[i][1] != x[j][1]){
                        if(isynf3(x[i][0],x[j][0])){
                            check[i]=true;
                            check[j]=true;
                            ahmed.add(x[i][0]);
                            ahmed.add(x[j][0]);
                            ahmed.add(x[i][1]);
                        }
                }
            }
        }
        ch.add(check);
        int[][] res = new int[ahmed.size()/(2*x[0].length-1)][2*x[0].length-1];
        int k =0;
        for(int i=0;i<ahmed.size()/(2*x[0].length-1);i++){
            for(int j=0;j<2*x[0].length-1;j++){
                res[i][j]=ahmed.get(k);
                k++;
            }
        }
        boolean are =true;
        for(int i = 0; i<check.length;i++){
            if(!check[i]){
                are = false;
                break;
            }
        }
        if (are){
            di.add(res);
            return Stepn(res);
        }
        else{
            LinkedList<Integer> u = new LinkedList<Integer>();
            for(int i = 0; i<check.length;i++){
                if(!check[i]){
                    for(int p =0 ; p<x[0].length;p++){
                       u.add(x[i][p]);
                    }
                }
            }
            remain = new int [u.size()/(x[0].length)][(x[0].length)];
            int w = 0;
            for(int n = 0 ; n<u.size()/(x[0].length);n++){
                for(int i=0;i<(x[0].length);i++){
                        remain[n][i]=(int) u.get(w);
                        w++;
                }
            }
            return res;
        }
    }
    private  int[][] Stepn(int[][] x) {
        sort2(x);
        LinkedList<Integer> ahmed = new LinkedList<Integer>();
        boolean [] check = new boolean[x.length];
        Arrays.fill(check, Boolean.FALSE);
        int[][] minus = new int[x.length][2];
        for(int i = 0 ;i < x.length ; i++) {
            minus[i][0] = x[i][x[0].length-2]-x[i][x[0].length-3];
            for( int j=x[0].length-4 ; j>=0 ; j--) {
                if(j%2 == 1){
                minus[i][0] = minus[i][0]-(x[i][j]) ;
                }
                else{
                    minus[i][0] = minus[i][0]+(x[i][j]) ;
                }
            }
            minus[i][1] = x[i][x[0].length-1];
        }
        for(int i = 0 ;i<x.length;i++){
            for(int j = i+1;j<x.length;j++){
                boolean is = false;
                if ( minus[i][0] == minus[j][0] && minus[i][1] != minus[j][1]){
                    boolean flag = true;
                    int base = 0;
                    for ( int k = 0 ; k <x[0].length-1;k++){
                        if(isynf3(x[i][k],x[j][k]) && x[i][x[0].length-1] != x[j][x[0].length-1]){
                            if(flag){
                                base = x[j][k]-x[i][k];
                                flag = false;
                            }
                            int lala = x[j][k]-x[i][k];
                            if(lala == base){
                            is = true;
                            }
                            else{
                                is = false;
                                break;
                            }
                        }
                        else{
                            is = false;
                            break;
                        }
                    }
                    if(is){
                        check[i]=true;
                        check[j]=true;
                        for(int k =0 ; k<x[0].length-1;k++){
                            ahmed.add(x[i][k]);
                        }
                        for(int k =0 ; k<x[0].length-1;k++){
                            ahmed.add(x[j][k]);
                        }
                        ahmed.add(x[i][x[0].length-1]);
                    }
                }
            }
        }
        ch.add(check);
        int[][] res = new int[ahmed.size()/(2*x[0].length-1)][2*x[0].length-1];
        int k =0;
        for(int i=0;i<ahmed.size()/(2*x[0].length-1);i++){
            for(int j=0;j<2*x[0].length-1;j++){
                res[i][j]=ahmed.get(k);
                k++;
            }
        }
        boolean are =true;
        for(int i = 0; i<check.length;i++){
            if(!check[i]){
                are = false;
                break;
            }
        }
        if (are){
            sort2(res);
            int [][] ezzat = yalla (res); 
            /*for(int i = 0 ; i<ezzat.length;i++){
                for(int j=0;j<ezzat[0].length;j++){
                    di.a
                }
            }*/
            di.add(ezzat);
            return Stepn(ezzat);
        }
        else{
            LinkedList<Integer> u = new LinkedList<Integer>();
            for(int i = 0; i<check.length;i++){
                if(!check[i]){
                    for(int p =0 ; p<x[0].length;p++){
                       u.add(x[i][p]);
                    }
                }
            }
            remain = new int [u.size()/(x[0].length)][(x[0].length)];
            int w = 0;
            for(int n = 0 ; n<u.size()/(x[0].length);n++){
                for(int i=0;i<(x[0].length);i++){
                        remain[n][i]=(int) u.get(w);
                        w++;
                }
            }
            sort2(res);
            int [][] ezzat = yalla (res);
            return ezzat;
        }
    }
    private boolean isynf3(int x,int y){
        int res = y - x;
        while (((res % 2) == 0) && res > 1){
            res /= 2;
        }
        return res == 1;
    }
    public  LinkedList<ArrayList<Integer>> Tablerow (int[][] row,int[][] rem){
        LinkedList<ArrayList<Integer>> a = new LinkedList<ArrayList<Integer>>();
        for(int i=0;i<row.length;i++){
            ArrayList<Integer> x = new ArrayList<Integer>(row[0].length);
            for(int j=0; j<row[0].length-1;j++){
                x.add(row[i][j]);
            }
            a.add(x);
        }
        for(int i=0;i<rem.length;i++){
            ArrayList<Integer> x = new ArrayList<Integer>(rem[0].length);
            for(int j=0; j<rem[0].length-1;j++){
                x.add(rem[i][j]);
            }
            a.add(x);
        }
        return a;
    }
    public  String getS(int A[],int y){
        int[][] data = new int[A.length][y];
        for(int i = 0 ; i <A.length ; i++) {
            String subBinary = Integer.toBinaryString(A[i]);
            if(subBinary.length() == y){
                for(int j=0;j<y;j++){
                    data[i][j] = (int)subBinary.charAt(j)-48;
                }
            }
            else{
                while(subBinary.length() != y){
                    subBinary = "0"+subBinary;
                }
                for(int j=0;j<y;j++){
                    data[i][j] = (int) subBinary.charAt(j)-48;
                }
            }
        }
        String result = "";
        int i = 0;
        for(int j = 0 ;j<y ; j++){
            int t = data[0][j];
            for(i=0 ;i<A.length;i++){
                if(t != data[i][j]){
                    break;
                }
            }
            if(i<A.length){
                result = result +"-";
            }
            else{
                if(data[i-1][j] == 1){
                    result= result+"1";
                }
                else{
                    result= result+"0";
                }
            }
        }
        return result;
    }
    private int[][] Group(int[][] A){
        for(int i = 0 ; i<A.length;i++){
            A[i][1]=Integer.bitCount(A[i][0]);
        }
        return A;
    }
    private int[][] sort( int[][] x) {
        int temp1;
        int temp2;
        for (int i = 0; i < x.length - 1; i++) {
            for (int j = 1; j < x.length - i; j++) {
                if (x[j - 1][1] <= x[j][1]) {
                    temp1 = x[j - 1][0];
                    temp2 = x[j - 1][1];
                    x[j - 1][0] = x[j][0];
                    x[j - 1][1] = x[j][1];
                    x[j][0] = temp1;
                    x[j][1] = temp2;
                }
            }
        }
        for (int i = 0; i < x.length / 2; i++) {
            final int temp3 = x[i][0];
            final int temp4 = x[i][1];
            x[i][0] = x[x.length - i - 1][0];
            x[i][1] = x[x.length - i - 1][1];
            x[x.length - i - 1][0] = temp3;
            x[x.length - i - 1][1] = temp4;
        }
        return x;
    }
    private int[][] yalla (int[][] dom){
        if(dom.length != 0){
        LinkedList copy = new LinkedList();
        int count = 0;
        for(int i = 0 ;i<dom.length ; i++){
            int[] f = new int[dom[0].length];
            for(int j=0;j<dom[0].length;j++){
                f[j]=dom[i][j];
            }
            copy.add(f);
        }
        int[] re = new int [dom[0].length-1];
        LinkedList bo = new LinkedList();
        for(int i=0;i<dom.length;i++){
            for(int j=0;j<dom[0].length-1;j++){
                re[j]=dom[i][j];
            }
            String res= getS(re,number_bits);
            bo.add(res);
        }
        for(int i=0;i<bo.size();i++){
            for(int j=i+1 ; j<bo.size();j++){
                if(bo.get(i).equals(bo.get(j))){
                    bo.remove(j);
                    copy.remove(j);
                    j--;
                }
            }
        }
        int[][] res = new int [copy.size()][dom[0].length];
        int k = 0;
        for(int i = 0 ;i<copy.size() ; i++){
            int [] c = (int[]) copy.get(k);
            k++;
            for(int j=0;j<dom[0].length;j++){
                res[i][j] = c[j];
            }
        } 
        return res;
    }
        else{
           int[][]fe = new int[0][0]; 
            return fe;
        }
    }
    private void sort2 (int[][] x){
        int temp1;
        for(int t = 0 ; t<x.length;t++){
        for (int i = 0; i < x[0].length - 1; i++) {
            for (int j = 1; j < x[0].length - i-1; j++) {
                if (x[t][j-1] > x[t][j]) {
                    temp1 = x[t][j-1];
                    x[t][j-1] = x[t][j];
                    x[t][j] = temp1;
                }
            }
        }
        }
    }
    private int no_bits(int[] x) {
        int max = 0;
        int a = 0;
        int bits = 0;
        for(int i=0;i<x.length;i++) {
            if (x[a] > max) {
                max = x[a];
            }
            a++;
        }
        if (max == 0) {
            bits = 0;
        } else if (max == 1) {
            bits = 1;
        } else if (max > 1 && max < 4) {
            bits = 2;
        } else if (max >= 4 && max < 8) {
            bits = 3;
        } else if (max >= 8 && max < 16) {
            bits = 4;
        } else if (max >= 16 && max < 32) {
            bits = 5;
        } else if (max >= 32 && max < 64) {
            bits = 6;
        } else if (max >= 65 && max <128){
            bits = 7;
        } else if(max >=128 && max<256){
            bits=8;
        } else if(max>=256 && max<512){
            bits=9;
        }
        else if (max>=512 && max<1024){
            bits=10;
        }
        else if(max>=1024 && max<2048){
            bits=11;
        }
        else if (max>=2048 && max<4096){
            bits=12;
        }
        else if (max>2048 && max<8192){
            bits=13;
        }
        return bits;
    }
    public String res (int[] Min){
        LinkedList<String> ezz = new LinkedList<String> ();
        LinkedList<Integer> col1 = new LinkedList<Integer>();
        LinkedList<Integer> row1 = new LinkedList<Integer> ();
        LinkedList<Integer> col2 = new LinkedList<Integer>();
        LinkedList<Integer> row2 = new LinkedList<Integer> ();
        LinkedList<Integer> col3 = new LinkedList<Integer>();
        LinkedList<Integer> row3 = new LinkedList<Integer> ();
        LinkedList<Integer> col4 = new LinkedList<Integer>();
        LinkedList<Integer> row4 = new LinkedList<Integer> ();
        boolean [] check = new boolean [16];
        Arrays.fill(check, false);
        for(int i = 0;i<Min.length;i++){
            if(Min[i]>=0 && Min[i]<4 ){
                row1.add(Min[i]);
            }
            if(Min[i]>=4 && Min[i]<8 ){
                row2.add(Min[i]);
            }
            if(Min[i]>=8 && Min[i]<12 ){
                row3.add(Min[i]);
            }
            if(Min[i]>=12 && Min[i]<16 ){
                row4.add(Min[i]);
            }
            if(Min[i]==0 ||Min[i]==4||Min[i]==12||Min[i]==8){
                col1.add(Min[i]);
            }
            if(Min[i]==1 ||Min[i]==5||Min[i]==13||Min[i]==9){
                col2.add(Min[i]);
            }
            if(Min[i]==3 ||Min[i]==7||Min[i]==15||Min[i]==11){
                col3.add(Min[i]);
            }
            if(Min[i]==2 ||Min[i]==6||Min[i]==14||Min[i]==10){
                col4.add(Min[i]);
            }
        }
        for(int i=0;i<8;i++){
        if(row1.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) row1.get(k);
                check[(int) row1.get(k)]=true;
            }
            row1.clear();
            ezz.add(getS(res,4));
        }
        if(row2.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) row2.get(k);
                check[(int) row2.get(k)]=true;
            }
            row2.clear();
            ezz.add(getS(res,4));
        }
        if(row3.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) row3.get(k);
                check[(int) row3.get(k)]=true;
            }
            row3.clear();
            ezz.add(getS(res,4));
        }
        if(row4.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) row4.get(k);
                check[(int) row4.get(k)]=true;
            }
            row4.clear();
            ezz.add(getS(res,4));
        }
        if(col1.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) col1.get(k);
                check[(int) col1.get(k)]=true;
            }
            col1.clear();
            ezz.add(getS(res,4));
        }
        if(col2.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) col2.get(k);
                check[(int) col2.get(k)]=true;
            }
            col2.clear();
            ezz.add(getS(res,4));
        }
        if(col3.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) col3.get(k);
                check[(int) col3.get(k)]=true;
            }
            col3.clear();
            ezz.add(getS(res,4));
        }
        if(col4.size() ==4){
            int[] res =new int[4];
            for(int k =0 ; k<4;k++){
                res[k]=(int) col4.get(k);
                check[(int) col4.get(k)]=true;
            }
            col4.clear();
            ezz.add(getS(res,4));
        }
        }
        for(int i=0;i<8;i++){
            if(row1.size() ==2 && isynf3((int)row1.get(0),(int)row1.get(1))&& (!check[(int)row1.get(0)] || !check[(int)row1.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) row1.get(k);
                    check[(int) row1.get(k)]=true;
                }
                row1.clear();
                ezz.add(getS(res,4));
            }
            if(row2.size() ==2 && isynf3((int)row2.get(0),(int)row2.get(1))&& (!check[(int)row2.get(0)] || !check[(int)row2.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) row2.get(k);
                    check[(int) row2.get(k)]=true;
                }
                row2.clear();
                ezz.add(getS(res,4));
            }
            if(row3.size() ==2 && isynf3((int)row3.get(0),(int)row3.get(1))&& (!check[(int)row3.get(0)] || !check[(int)row3.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) row3.get(k);
                    check[(int) row3.get(k)]=true;
                }
                row3.clear();
                ezz.add(getS(res,4));
            }
            if(row4.size() ==2 && isynf3((int)row4.get(0),(int)row4.get(1))&& (!check[(int)row4.get(0)] || !check[(int)row4.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) row4.get(k);
                    check[(int) row4.get(k)]=true;
                }
                row4.clear();
                ezz.add(getS(res,4));
            }
            if(col1.size() ==2 && isynf3((int)col1.get(0),(int)col1.get(1))&& (!check[(int)col1.get(0)] || !check[(int)col1.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) col1.get(k);
                    check[(int) col1.get(k)]=true;
                }
                col1.clear();
                ezz.add(getS(res,4));
            }
            if(col2.size() ==2 && isynf3((int)col2.get(0),(int)col2.get(1))&& (!check[(int)col2.get(0)] || !check[(int)col2.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) col2.get(k);
                    check[(int) col2.get(k)]=true;
                }
                col2.clear();
                ezz.add(getS(res,4));
            }
            if(col3.size() ==2 && isynf3((int)col3.get(0),(int)col3.get(1))&& (!check[(int)col3.get(0)] || !check[(int)col3.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) col3.get(k);
                    check[(int) col3.get(k)]=true;
                }
                col3.clear();
                ezz.add(getS(res,4));
            }
            if(col4.size() ==2 && isynf3((int)col4.get(0),(int)col4.get(1))&& (!check[(int)col4.get(0)] || !check[(int)col4.get(1)])){
                int[] res =new int[2];
                for(int k =0 ; k<2;k++){
                    res[k]=(int) col4.get(k);
                    check[(int) col4.get(k)]=true;
                }
                col4.clear();
                ezz.add(getS(res,4));
            }
            }
        for(int i=0;i<8;i++){
            if(row1.size() ==3){
                if(isynf3((int)row1.get(0),(int)row1.get(1)) && (!check[(int)row1.get(0)] || !check[(int)row1.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) row1.get(0);
                        check[(int) row1.get(0)]=true;
                        res[1]=(int) row1.get(1);
                        check[(int) row1.get(1)]=true;
                    row1.clear();
                    ezz.add(getS(res,4));   
                }
                else if (isynf3((int) row1.get(0), (int) row1.get(2)) && (!check[(int)row1.get(0)] || !check[(int)row1.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row1.get(0);
                    check[(int) row1.get(0)]=true;
                    res[1]=(int) row1.get(2);
                    check[(int) row1.get(2)]=true;
                row1.clear();
                ezz.add(getS(res,4));   
            }
                else if (isynf3((int) row1.get(1), (int) row1.get(2)) && (!check[(int)row1.get(1)]|| !check[(int)row1.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row1.get(1);
                    check[(int) row1.get(1)]=true;
                    res[1]=(int) row1.get(2);
                    check[(int) row1.get(2)]=true;
                row1.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(row2.size() ==3){
                if(isynf3((int)row2.get(0),(int)row2.get(1)) && (!check[(int)row2.get(0)] || !check[(int)row2.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) row2.get(0);
                        check[(int) row2.get(0)]=true;
                        res[1]=(int) row2.get(1);
                        check[(int) row2.get(1)]=true;
                    row2.clear();
                    ezz.add(getS(res,4));   
                }
                else if (isynf3((int) row2.get(0), (int) row2.get(2)) && (!check[(int)row2.get(0)] || !check[(int)row2.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row2.get(0);
                    check[(int) row2.get(0)]=true;
                    res[1]=(int) row2.get(2);
                    check[(int) row2.get(2)]=true;
                row2.clear();
                ezz.add(getS(res,4));   
            }
                else if (isynf3((int) row2.get(1), (int) row2.get(2)) && (!check[(int)row2.get(1)]|| !check[(int)row2.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row2.get(1);
                    check[(int) row2.get(1)]=true;
                    res[1]=(int) row2.get(2);
                    check[(int) row2.get(2)]=true;
                row2.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(row2.size() ==3){
                if(isynf3((int)row2.get(0),(int)row2.get(1)) && (!check[(int)row2.get(0)] || !check[(int)row2.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) row2.get(0);
                        check[(int) row2.get(0)]=true;
                        res[1]=(int) row2.get(1);
                        check[(int) row2.get(1)]=true;
                    row2.clear();
                    ezz.add(getS(res,4));   
                }
                else  if (isynf3((int) row2.get(0), (int) row2.get(2)) && (!check[(int)row2.get(0)] || !check[(int)row2.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row2.get(0);
                    check[(int) row2.get(0)]=true;
                    res[1]=(int) row2.get(2);
                    check[(int) row2.get(2)]=true;
                row2.clear();
                ezz.add(getS(res,4));   
            }
                else  if (isynf3((int) row2.get(1), (int) row2.get(2)) && (!check[(int)row2.get(1)]|| !check[(int)row2.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row2.get(1);
                    check[(int) row2.get(1)]=true;
                    res[1]=(int) row2.get(2);
                    check[(int) row2.get(2)]=true;
                row2.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(row3.size() ==3){
                if(isynf3((int)row3.get(0),(int)row3.get(1)) && (!check[(int)row3.get(0)] || !check[(int)row3.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) row3.get(0);
                        check[(int) row3.get(0)]=true;
                        res[1]=(int) row3.get(1);
                        check[(int) row3.get(1)]=true;
                    row3.clear();
                    ezz.add(getS(res,4));   
                }
                else  if (isynf3((int) row3.get(0), (int) row3.get(2)) && (!check[(int)row3.get(0)] || !check[(int)row3.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row3.get(0);
                    check[(int) row3.get(0)]=true;
                    res[1]=(int) row3.get(2);
                    check[(int) row3.get(2)]=true;
                row3.clear();
                ezz.add(getS(res,4));   
            }
                else if (isynf3((int) row3.get(1), (int) row3.get(2)) && (!check[(int)row3.get(1)]|| !check[(int)row3.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row3.get(1);
                    check[(int) row3.get(1)]=true;
                    res[1]=(int) row3.get(2);
                    check[(int) row3.get(2)]=true;
                row3.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(row4.size() ==3){
                if(isynf3((int)row4.get(0),(int)row4.get(1)) && (!check[(int)row4.get(0)] || !check[(int)row4.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) row4.get(0);
                        check[(int) row4.get(0)]=true;
                        res[1]=(int) row4.get(1);
                        check[(int) row4.get(1)]=true;
                    row4.clear();
                    ezz.add(getS(res,4));   
                }
                else   if (isynf3((int) row4.get(0), (int) row4.get(2)) && (!check[(int)row4.get(0)] || !check[(int)row4.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row4.get(0);
                    check[(int) row4.get(0)]=true;
                    res[1]=(int) row4.get(2);
                    check[(int) row4.get(2)]=true;
                row4.clear();
                ezz.add(getS(res,4));   
            }
                else  if (isynf3((int) row4.get(1), (int) row4.get(2)) && (!check[(int)row4.get(1)]|| !check[(int)row4.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) row4.get(1);
                    check[(int) row4.get(1)]=true;
                    res[1]=(int) row4.get(2);
                    check[(int) row4.get(2)]=true;
                row4.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(col1.size() ==3){
                if(isynf3((int)col1.get(0),(int)col1.get(1)) && (!check[(int)col1.get(0)] || !check[(int)col1.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) col1.get(0);
                        check[(int) col1.get(0)]=true;
                        res[1]=(int) col1.get(1);
                        check[(int) col1.get(1)]=true;
                    col1.clear();
                    ezz.add(getS(res,4));   
                }
                else if (isynf3((int) col1.get(0), (int) col1.get(2)) && (!check[(int)col1.get(0)] || !check[(int)col1.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col1.get(0);
                    check[(int) col1.get(0)]=true;
                    res[1]=(int) col1.get(2);
                    check[(int) col1.get(2)]=true;
                col1.clear();
                ezz.add(getS(res,4));   
            }
                else if (isynf3((int) col1.get(1), (int) col1.get(2)) && (!check[(int)col1.get(1)]|| !check[(int)col1.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col1.get(1);
                    check[(int) col1.get(1)]=true;
                    res[1]=(int) col1.get(2);
                    check[(int) col1.get(2)]=true;
                col1.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(col2.size() ==3){
                if(isynf3((int)col2.get(0),(int)col2.get(1)) && (!check[(int)col2.get(0)] || !check[(int)col2.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) col2.get(0);
                        check[(int) col2.get(0)]=true;
                        res[1]=(int) col2.get(1);
                        check[(int) col2.get(1)]=true;
                    col2.clear();
                    ezz.add(getS(res,4));   
                }
                else if (isynf3((int) col2.get(0), (int) col2.get(2)) && (!check[(int)col2.get(0)] || !check[(int)col2.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col2.get(0);
                    check[(int) col2.get(0)]=true;
                    res[1]=(int) col2.get(2);
                    check[(int) col2.get(2)]=true;
                col2.clear();
                ezz.add(getS(res,4));   
            }
                else if (isynf3((int) col2.get(1), (int) col2.get(2)) && (!check[(int)col2.get(1)]|| !check[(int)col2.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col2.get(1);
                    check[(int) col2.get(1)]=true;
                    res[1]=(int) col2.get(2);
                    check[(int) col2.get(2)]=true;
                col2.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(col3.size() ==3){
                if(isynf3((int)col3.get(0),(int)col3.get(1)) && (!check[(int)col3.get(0)] || !check[(int)col3.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) col3.get(0);
                        check[(int) col3.get(0)]=true;
                        res[1]=(int) col3.get(1);
                        check[(int) col3.get(1)]=true;
                    col3.clear();
                    ezz.add(getS(res,4));   
                }
                else  if (isynf3((int) col3.get(0), (int) col3.get(2)) && (!check[(int)col3.get(0)] || !check[(int)col3.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col3.get(0);
                    check[(int) col3.get(0)]=true;
                    res[1]=(int) col3.get(2);
                    check[(int) col3.get(2)]=true;
                col3.clear();
                ezz.add(getS(res,4));   
            }
                else    if (isynf3((int) col3.get(1), (int) col3.get(2)) && (!check[(int)col3.get(1)]|| !check[(int)col3.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col3.get(1);
                    check[(int) col3.get(1)]=true;
                    res[1]=(int) col3.get(2);
                    check[(int) col3.get(2)]=true;
                col3.clear();
                ezz.add(getS(res,4));   
            }
                }
            if(col4.size() ==3){
                if(isynf3((int)col4.get(0),(int)col4.get(1)) && (!check[(int)col4.get(0)] || !check[(int)col4.get(1)])){
                    int[] res =new int[2];
                        res[0]=(int) col4.get(0);
                        check[(int) col4.get(0)]=true;
                        res[1]=(int) col4.get(1);
                        check[(int) col4.get(1)]=true;
                    col4.clear();
                    ezz.add(getS(res,4));   
                }
                else if (isynf3((int) col4.get(0), (int) col4.get(2)) && (!check[(int)col4.get(0)] || !check[(int)col4.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col4.get(0);
                    check[(int) col4.get(0)]=true;
                    res[1]=(int) col4.get(2);
                    check[(int) col4.get(2)]=true;
                col4.clear();
                ezz.add(getS(res,4));   
            }
                else     if (isynf3((int) col4.get(1), (int) col4.get(2)) && (!check[(int)col4.get(1)]|| !check[(int)col4.get(2)])) {
                    int[] res =new int[2];
                    res[0]=(int) col4.get(1);
                    check[(int) col4.get(1)]=true;
                    res[1]=(int) col4.get(2);
                    check[(int) col4.get(2)]=true;
                col4.clear();
                ezz.add(getS(res,4));   
            }
                }
            }
        for(int i=0;i<8;i++){
            if(row1.size() ==1 && (!check[(int)row1.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) row1.get(0);
                    check[(int) row1.get(0)]=true;
                row1.clear();
                ezz.add(getS(res,4));
            }
            if(row2.size() ==1 && (!check[(int)row2.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) row2.get(0);
                    check[(int) row2.get(0)]=true;
                row2.clear();
                ezz.add(getS(res,4));
            }
            if(row3.size() ==1 && (!check[(int)row3.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) row3.get(0);
                    check[(int) row3.get(0)]=true;
                row3.clear();
                ezz.add(getS(res,4));
            }
            if(row4.size() ==1 && (!check[(int)row4.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) row4.get(0);
                    check[(int) row4.get(0)]=true;
                row4.clear();
                ezz.add(getS(res,4));
            }
            if(col1.size() ==1 && (!check[(int)col1.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) col1.get(0);
                    check[(int) col1.get(0)]=true;
                col1.clear();
                ezz.add(getS(res,4));
            }
            if(col2.size() ==1 && (!check[(int)col2.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) col2.get(0);
                    check[(int) col2.get(0)]=true;
                col2.clear();
                ezz.add(getS(res,4));
            }
            if(col3.size() ==1 && (!check[(int)col3.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) col3.get(0);
                    check[(int) col3.get(0)]=true;
                col3.clear();
                ezz.add(getS(res,4));
            }
            if(col4.size() ==1 && (!check[(int)col4.get(0)])){
                int[] res =new int[1];
                    res[0]=(int) col4.get(0);
                    check[(int) col4.get(0)]=true;
                col4.clear();
                ezz.add(getS(res,4));
            }
            }
        String res ="";
        for(int i = 0 ;i<ezz.size();i++){
            String con = ezz.get(i);
            for(int j = 0;j<4;j++){
                if(con.charAt(j) == '0'){
                 if(j==0){
                     res += "A'";
                 }
                 else if(j==1){
                     res += "B'";
                 }
                 else if(j ==2){
                     res += "C'";
                 }
                 else if(j == 3){
                     res += "D'";
                 }
                }
                else if(con.charAt(j) =='1'){
                    if(j==0){
                        res += "A";
                    }
                    else if(j==1){
                        res += "B";
                    }
                    else if(j ==2){
                        res += "C";
                    }
                    else if(j == 3){
                        res += "D";
                    }   
                }
            }
            if(i != ezz.size()-1)
            res +="+";
        }
        return res;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[][] x = new String[4][4];
        x[0][0] = "1"; x[0][1] ="1"; x[0][2] ="";x[0][3] ="";
        x[1][0] ="1"; x[1][1] ="1"; x[1][2] ="1";x[0][3] ="1";
        x[2][0] ="1"; x[2][1] =""; x[2][2] ="1";x[0][3] ="";
        x[3][0] ="1"; x[3][1] =""; x[3][2] ="1";x[0][3] ="1";
        System.out.println(makeEquation(x));
    }

    
    public static String makeEquation(String table[][])
    {
        int col = table[0].length;
        String[][] A = new String[table.length+1][col-1];
        int x = 1;
        for(int i = 0 ;i < col-1; i++)
        {
            A[0][i] = ""+(x+1+i);//important equation
        }
        for(int i = 0 ; i < table.length; i++)
        {
            for(int j = 1 ; j < col ; j++)
            {
                A[i+1][j-1] = table[i][j];
            }
        }
        //array to collect the terms 
        String[][] collect = new String[A.length][100];
        int k = 0;
        for(int i = 1;i < A.length;i++)
        {
            for(int j = 0;j < col-1 ;j++)
            {
                if(A[i][j] == "1")
                {
                    collect[i-1][k] = "" + A[0][j];
                    k++;
                }
            }
            k = 0;
        }
        //now we should multiply the rows of collect array
        String[] myltiply = new String[100];
        String[][] myltiply2 = new String[100][200];
        int s = 0;
        for(int i = 0 ;i < collect.length;i++)
        {
            for(int j = 0 ;j< col-1; j++)
            {
                    for(int y = 0 ;y < col-1; y++)
                    {
                        if(i+1 < collect.length)
                        {
                            if(collect[i][j] != null && collect[i+1][y] != null)
                            {
                                    if(!collect[i][j].contains(collect[i+1][y]))
                                    {
                                        myltiply[s] = collect[i][j] + collect[i+1][y];
                                        s++;
                                    }
                                    else
                                    {
                                        myltiply[s] = collect[i][j];
                                        s++;
                                    }
                            
                             
                            }
                    }
                }
        }
            for(int n = 0 ;n < 100; n++)
            {
                if(i+1 < collect.length)
                {
                collect[i+1][n] = myltiply[n];
                myltiply[n] = "";
                }
            }
            s=0;
    }
        //get the minimum
        int value = collect[collect.length-2][0].length();
        for(int k5 = 0 ; k5 < 100 ; k5++)
        {
            if(collect[collect.length-2][k5].length() < value && collect[collect.length-2][k5].length() != 0 )
            {
                value = collect[collect.length-2][k5].length();
            }
        }
        //value have the min length
        String result = new String();
        for(int k5 = 0 ; k5 < 100 ; k5++)
        {
            if(collect[collect.length-2][k5].length() == value)
            {
                result = result +" " + collect[collect.length-2][k5];
            }
        }
    return result.trim();   
}
}
