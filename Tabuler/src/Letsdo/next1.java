package Letsdo;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
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

public class next1  {

    private JFrame frame;
    realWork ahmed = new realWork();
    public static int [] minterms;
    public static int[][] step0 ;
    public static int [][] step1;
    public static int [][] remain;
    public static LinkedList<int[][]> steps;
    public static LinkedList<boolean[]> check;
    private JTable table_2;
    private JTable table;
    String finalres ;
    /**
     * @wbp.parser.entryPoint
     */
    public next1(int [] x , int[][] y  , int [][] z , int [][] t , LinkedList<int[][]> h , LinkedList<boolean[]> m,String qw){
        minterms = x;
        step0 = y;
        step1 = z;
        remain = t;
        steps = h;
        check = m;
        finalres = qw;
        initialize(minterms , step0 , step1 , remain , steps , check,finalres);
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
     * @wbp.parser.entryPoint
     */
    private void initialize(int [] x , int[][] y  , int [][] z , int [][] t , LinkedList<int[][]> h , LinkedList<boolean[]>m,String qw) {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(100, 100, 684, 631);
        frame.setTitle("Steps");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JLabel lblStep = new JLabel("Second Comparasion");
        lblStep.setForeground(SystemColor.activeCaption);
        lblStep.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblStep.setBounds(25, 26, 223, 23);
        frame.getContentPane().add(lblStep);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBackground(Color.CYAN);
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 60, 654, 257);
        frame.getContentPane().add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setEnabled(false);
        scrollPane_1.setBorder(null);
        scrollPane_1.setBounds(10, 362, 642, 186);
        frame.getContentPane().add(scrollPane_1);
        JLabel lblFirstComparasion = new JLabel("Prime Implicants");
        lblFirstComparasion.setForeground(SystemColor.activeCaption);
        lblFirstComparasion.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblFirstComparasion.setBounds(25, 328, 223, 23);
        frame.getContentPane().add(lblFirstComparasion);
        
        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                next2 second = new next2(minterms , step0 , step1 , remain , steps , check,finalres);
                frame.setVisible(false); // Hide current frame
                 second.setVisible(true);
            }
        });
        btnNext.setForeground(new Color(255, 0, 51));
        btnNext.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
        btnNext.setBounds(575, 564, 89, 30);
        frame.getContentPane().add(btnNext);
        if(z.length != 0 && h.size()>1 && m.size()>2){
        String [] colNames = { "Group", "input", "Binary" , "Checked"};
        int [][] o = steps.get(1);
        boolean[] b = check.get(2);
        Object [][] data = new Object [o.length][4] ;
        for(int i=0;i<o.length;i++){
                data[i][0]=o[i][4];
                int[] u = new int[4];
                u[0] = o[i][0];
                u[1] = o[i][1];
                u[2] = o[i][2];
                u[3] = o[i][3];
                String p = "( "+u[0]+" , "+u[1]+" , "+u[2]+" , "+u[3]+" )";
                data[i][1] = p;
                data[i][2]= ahmed.getS(u,ahmed.number_bits);
                data[i][3] = b[i];
        }
        table_2 = new JTable(data,colNames);
        table_2.setBackground(Color.CYAN);
        table_2.setBorder(null);
        table_2.setEnabled(false);
        scrollPane.setViewportView(table_2);
        String [] colNames2 = { "Group", "input", "Binary"};
        int [][] v = step1;
        int [][] re = remain;
        Object [][] data2 = new Object [v.length+re.length][3] ;
        for(int i=0;i<v.length;i++){
                data2[i][0]=v[i][v[0].length-1];
                int[] u = new int[v[0].length-1];
                for(int r=0;r<v[0].length-1;r++){
                    u[r] = v[i][r];
                }
                String p = "( ";
                for(int d=0;d<u.length-1;d++){
                    p+=u[d]+" , ";
                }
                p+=u[u.length-1];
                p+=" )";
                data2[i][1] = p;
                data2[i][2]= ahmed.getS(u,ahmed.number_bits);
        }
        for(int j=v.length ,i =0;i<re.length;i++,j++){
            data2[j][0]=re[i][re[0].length-1];
            int[] u = new int[re[0].length-1];
            for(int r=0;r<re[0].length-1;r++){
                u[r] = re[i][r];
            }
            String p = "( ";
            for(int d=0;d<u.length-1;d++){
                p+=u[d]+" , ";
            }
            p+=u[u.length-1];
            p+=" )";
            data2[j][1] = p;
            data2[j][2]= ahmed.getS(u,ahmed.number_bits);
    }
        table = new JTable(data2,colNames2);
        table.setBackground(Color.CYAN);
        table.setBorder(null);
        table.setEnabled(false);
        scrollPane_1.setViewportView(table);
        
        }
        else{
            /*int [][] o = steps.get(1);
            boolean[] b = check.get(2);
            Object [][] data = new Object [o.length][4] ;
            for(int i=0;i<o.length;i++){
                    data[i][0]=o[i][4];
                    int[] u = new int[4];
                    u[0] = o[i][0];
                    u[1] = o[i][1];
                    u[2] = o[i][2];
                    u[3] = o[i][3];
                    String p = "( "+u[0]+" , "+u[1]+" , "+u[2]+" , "+u[3]+" )";
                    data[i][1] = p;
                    data[i][2]= ahmed.getS(u,ahmed.number_bits);
                    data[i][3] = b[i];
            }*/
            Object [][] data = new Object [0][4] ;
            String [] colNames = { "Group", "input", "Binary"};
            table_2 = new JTable(data,colNames);
            table_2.setBackground(Color.CYAN);
            table_2.setBorder(null);
            table_2.setEnabled(false);
            table_2.setBounds(25, 75, 209, 369);
            frame.getContentPane().add(table_2);
            scrollPane.setViewportView(table_2);
            
            
            String [] colNames2 = { "Group", "input", "Binary"};
            int [][] v = step1;
            int [][] re = remain;
            Object [][] data2 = new Object [v.length+re.length][3] ;
            for(int i=0;i<v.length;i++){
                    data2[i][0]=v[i][v[0].length-1];
                    int[] u = new int[v[0].length-1];
                    for(int r=0;r<v[0].length-1;r++){
                        u[r] = v[i][r];
                    }
                    String p = "( ";
                    for(int d=0;d<u.length-1;d++){
                        p+=u[d]+" , ";
                    }
                    p+=u[u.length-1];
                    p+=" )";
                    data2[i][1] = p;
                    data2[i][2]= ahmed.getS(u,ahmed.number_bits);
            }
            for(int j=v.length ,i =0;i<re.length;i++,j++){
                data2[j][0]=re[i][re[0].length-1];
                int[] u = new int[re[0].length-1];
                for(int r=0;r<re[0].length-1;r++){
                    u[r] = re[i][r];
                }
                String p = "( ";
                for(int d=0;d<u.length-1;d++){
                    p+=u[d]+" , ";
                }
                p+=u[u.length-1];
                p+=" )";
                data2[j][1] = p;
                data2[j][2]= ahmed.getS(u,ahmed.number_bits);
        }
            table = new JTable(data2,colNames2);
            table.setBackground(Color.CYAN);
            table.setBorder(null);
            table.setEnabled(false);
            scrollPane_1.setViewportView(table);
           
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
}
