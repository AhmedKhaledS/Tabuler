package Letsdo;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class next2 extends JFrame {

    private JFrame frame;
    realWork ahmed = new realWork();
    public static int [] minterms;
    public static int[][] step0 ;
    public static int [][] step1;
    public static int [][] remain;
    public static LinkedList<int[][]> steps;
    public static LinkedList<boolean[]> check;
    boolean [][] cop;
    LinkedList zzz;
    String [] firstRow;
    private JTable table_2;
    private JTextField textField_2;
    String finalres;
    /**
     * @wbp.parser.constructor
     */
    public next2(int [] x , int[][] y  , int [][] z , int [][] t , LinkedList<int[][]> h , LinkedList<boolean[]> m,String qw){
        minterms = x;
        step0 = y;
        step1 = z;
        remain = t;
        steps = h;
        check = m;
        finalres = qw;
        initialize(minterms , step0 , step1 , remain , steps , check, finalres);
    }
    /**
     * Launch the application.
     */
   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    next window = new next(minterms , step0  , step1 , remain , steps);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the application.
     */
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize(int [] x , int[][] y  , int [][] z , int [][] t , LinkedList<int[][]> h , LinkedList<boolean[]>m,String qw) {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(100, 100, 684, 631);
        frame.setTitle("Steps");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        firstRow = new String[step1.length+remain.length+1];
        JLabel lblStep = new JLabel("Coverage Table");
        lblStep.setForeground(SystemColor.activeCaption);
        lblStep.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblStep.setBounds(245, 27, 223, 23);
        frame.getContentPane().add(lblStep);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBackground(Color.CYAN);
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 60, 654, 361);
        frame.getContentPane().add(scrollPane);
        JButton btnNext = new JButton("Finish");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "Thanks for using \n Created By :\n Ahmed Khaled Saad Hassan (7) \n Ahmed Ezzat Elmaghawry (13)\nAhmed Moustafa Youssef ElNaggar(19)\nArsanuous Eissa Atia (21)\nMarwan Morsy Mohamed Morsy (75)");
                frame.setVisible(false);
            }
        });
        btnNext.setForeground(new Color(255, 0, 51));
        btnNext.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
        btnNext.setBounds(553, 564, 111, 30);
        frame.getContentPane().add(btnNext);
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setFont(UIManager.getFont("CheckBoxMenuItem.font"));
        textField_2.setBounds(10, 461, 641, 84);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);
        if(z.length != 0 ){
        int dor = step1[0].length;
        firstRow[0] = "  ";
        for(int i=0;i<step1.length;i++){
            int[] u = new int[dor-1];
            for(int r=0;r<dor-1;r++){
                u[r] = step1[i][r];
            }
            firstRow[i+1]=ahmed.getS(u,ahmed.number_bits);
    }
        int dor2 = remain[0].length;
        for(int i=0;i<remain.length;i++){
            int[] u = new int[dor2-1];
            for(int r=0;r<dor2-1;r++){
                u[r] = remain[i][r];
            }
            firstRow[step1.length+i+1]=ahmed.getS(u,ahmed.number_bits);
    }
        String[][] data = new String [minterms.length][firstRow.length];
        cop = new boolean [minterms.length-1][firstRow.length-1];
        for(int i = 0 ;i<minterms.length;i++){
            String pop = minterms[i] +"";
            data[i][0] = (pop);
        }
        for (int n = 0 ; n<minterms.length;n++){
            for(int me=0;me<step1.length;me++){
                for(int ne =0;ne<step1[0].length;ne++){
                if(step1[me][ne]==(minterms[n])){
                    if(n<minterms.length-1 && me <firstRow.length-1 ){
                        cop[n][me] = true ;
                        }
                    data[n][me+1] = "1" ;
                }
            }
            }
            for(int me=0;me<remain.length;me++){
                for(int ne =0;ne<remain[0].length-1;ne++){
                if(remain[me][ne]==(minterms[n])){
                    if(n<minterms.length-1 && me+step1.length <firstRow.length-1 ){
                    cop[n][me+step1.length] = true ;
                    }
                    data[n][me+1+step1.length] = "1" ;
                }
            }
            }
        }
        table_2 = new JTable(data,firstRow);
        table_2.setBackground(Color.CYAN);
        table_2.setBorder(null);
        table_2.setEnabled(false);
        scrollPane.setViewportView(table_2);
        /*LinkedList re = finaly(cop);
        for(int o =0;o<re.size();o++){
            textField_2.setText(re.get(o)+",");
        }*/
        textField_2.setText(finalres);
        }
        else{
            String [] firstRow = new String[remain.length+1];
            firstRow[0] = "  ";
            int dor2 = remain[0].length;
            for(int i=0;i<remain.length;i++){
                int[] u = new int[dor2-1];
                for(int r=0;r<dor2-1;r++){
                    u[r] = remain[i][r];
                }
                firstRow[i+1]=ahmed.getS(u,ahmed.number_bits);
        }
            String[][] data = new String [minterms.length][firstRow.length];
            boolean [][] cop = new boolean [minterms.length-1][firstRow.length-1];
            for(int i = 0 ;i<minterms.length;i++){
                String pop = minterms[i] +"";
                data[i][0] = (pop);
            }
            for (int n = 0 ; n<minterms.length;n++){
                for(int me=0;me<remain.length;me++){
                    for(int ne =0;ne<remain[0].length-1;ne++){
                    if(remain[me][ne]==(minterms[n])){
                        if(n<minterms.length-1 && me <firstRow.length-1 ){
                        cop[n][me] = true ;
                        }
                        data[n][me+1] = "1" ;
                    }
                }
                }
            }
            table_2 = new JTable(data,firstRow);
            table_2.setBackground(Color.CYAN);
            table_2.setBorder(null);
            table_2.setEnabled(false);
            scrollPane.setViewportView(table_2);
            textField_2.setText(finalres);
        }
    }
    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        if(b == true)
            frame.setVisible(b);
        else{
            frame.setVisible(b);
            Tabular x = new Tabular();
            x.setVisible(true);
        }
    }
   /* public String fun (boolean[][] x){
        Object [][] zz = new Object[3][minterms.length];
        for(int i=0;i<minterms.length;i++){
            zz[0][i] = minterms[i];
        }
        for(int i = 0;i<minterms.length;i++){
            int count = 0 ;
            LinkedList q = new LinkedList();
            for(int j = 0 ;j<x[0].length;j++){
            if(x[i][j] == true){
                count++;
                if(j<step1.length){
                q.add(step1[j]);
                }
                else{
                    q.add(remain[j]);
                }
            }
            }
            zz[1][i]=count;
            zz[2][i]=q;
        }
        LinkedList res = help(zz);
        String ro="";
        for(int i=0;i<res.size();i++){
            LinkedList s=(LinkedList) res.get(i);
            int [] a = (int[]) s.get(i);
            ro+=ahmed.getS(a,4);
        }
        return ro;
    }
    LinkedList bta3 = new LinkedList();
    public LinkedList help (Object[][] x){
        boolean flag = true;
        for(int i = 0 ;i<minterms.length;i++){
            if(x[1][i].equals(1)){
                flag = false;
                bta3.add(x[2][i]);
                LinkedList g = (LinkedList) x[2][i];
                for(int t = 0 ;t<minterms.length;t++){
                    if(g.contains(minterms[t])){
                        x[1][t] = 0;
                    }
                }
            }
        }
        if(flag){
            int[] test = new int[minterms.length];
            Arrays.fill(test, 0);
            if(x[1].equals(test)){
                return bta3;
            }
            else{
                for(int i = 0 ;i<minterms.length;i++){
                LinkedList g = (LinkedList) x[2][i];
                for(int t = 0 ;t<minterms.length;t++){
                    if(g.contains(minterms[t])){
                        x[1][t] = 0;
                    }
                }
            }
               return help(x);
            }
        }
        else{
            int[] test = new int[minterms.length];
            Arrays.fill(test, 0);
            if(x[1].equals(test)){
                return bta3;
            }
            else{
                return help(x);
            }
        }
    }*/
    /*LinkedList z = new LinkedList();
    public LinkedList finaly(boolean[][] x){
        int[] y =new int[x[0].length];
        for(int i=0;i<x[0].length;i++){
            for(int j=0;j<x.length;j++){
                if(x[j][i]){
                    y[i]= y[i]+1;
                }
            }
        }
        int max = -999;
        int pos = 0;
        for(int i =0;i<y.length;i++){
            if(y[i]>max){
                max = y[i];
                pos = i;
            }
        }
        z.add(firstRow[pos+1]);
        for(int u = 0;u<x.length;u++){
            if(x[u][pos]){
                for(int i = 0;i<x[0].length;i++){
                    x[u][i]=false;
                }
            }
        }
        boolean[][] test = new boolean[x.length][x[0].length];
        boolean flag1 = true;
        for(int i=0;i<test.length;i++){
            for(int j=0;j<test[0].length;j++){
                if(test[i][j]==x[i][j]){
                }
                else{
                    flag1 = false;
                }
            }
        }
        if(flag1){
            return z;
        }
        else{
            return finaly(x);
        }
    }*/
}
