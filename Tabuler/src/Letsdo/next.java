package Letsdo;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JTable;
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

public class next extends JFrame {

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
    static String finalres ;
    public next(int [] x , int[][] y  , int [][] z , int [][] t , LinkedList<int[][]> h , LinkedList<boolean[]> m , String qw){
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
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    next window = new next(minterms , step0  , step1 , remain , steps,check, finalres);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize(int [] x , int[][] y  , int [][] z , int [][] t , LinkedList<int[][]> h , LinkedList<boolean[]>m ,String qw) {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(100, 100, 615, 481);
        frame.setTitle("Steps");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JLabel lblStep = new JLabel("Inputs");
        lblStep.setForeground(SystemColor.activeCaption);
        lblStep.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblStep.setBounds(25, 26, 125, 23);
        frame.getContentPane().add(lblStep);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setBackground(Color.CYAN);
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 60, 253, 270);
        frame.getContentPane().add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setEnabled(false);
        scrollPane_1.setBorder(null);
        scrollPane_1.setBounds(303, 60, 276, 336);
        frame.getContentPane().add(scrollPane_1);
        JLabel lblFirstComparasion = new JLabel("First Comparasion");
        lblFirstComparasion.setForeground(SystemColor.activeCaption);
        lblFirstComparasion.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblFirstComparasion.setBounds(326, 26, 223, 23);
        frame.getContentPane().add(lblFirstComparasion);
        
        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                next1 second = new next1(minterms , step0 , step1 , remain , steps , check,finalres);
                frame.setVisible(false); // Hide current frame
                 second.setVisible(true);
            }
        });
        btnNext.setForeground(new Color(255, 0, 51));
        btnNext.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
        btnNext.setBounds(490, 414, 89, 30);
        frame.getContentPane().add(btnNext);
        if(y.length!=1 && m.size() > 0){
        String [] colNames = { "Group", "input", "Binary" , "Checked"};
        boolean[] b = check.get(0);
        Object [][] data = new Object [step0.length][4] ;
        for(int i=0;i<step0.length;i++){
                data[i][0]=step0[i][1];
                data[i][1] = step0[i][0];
                int[] u = new int[1];
                u[0] = (int) data[i][1];
                data[i][2]= ahmed.getS(u,ahmed.number_bits);
                data[i][3] = b[i];
        }
        table_2 = new JTable(data,colNames);
        table_2.setBackground(Color.CYAN);
        table_2.setBorder(null);
        table_2.setEnabled(false);
        table_2.setBounds(25, 75, 209, 369);
        frame.getContentPane().add(table_2);
        scrollPane.setViewportView(table_2);
        
        String [] colNames2 = { "Group", "input", "Binary" , "Checked"};
        int [][] o = steps.get(0);
        boolean [] w = check.get(1);
        Object [][] data2 = new Object [o.length][4] ;
        for(int i=0;i<o.length;i++){
                data2[i][0]=o[i][2];
                int[] u = new int[2];
                u[0] = o[i][0];
                u[1] = o[i][1];
                String p = "( "+u[0]+" , "+u[1]+" )";
                data2[i][1] = p;
                data2[i][2]= ahmed.getS(u,ahmed.number_bits);
                data2[i][3]= w[i];
        }
        table = new JTable(data2,colNames2);
        table.setBackground(Color.CYAN);
        table.setBorder(null);
        table.setEnabled(false);
        scrollPane_1.setViewportView(table);
        
       }
        else{
            JOptionPane.showMessageDialog(null, "Calculate it by yourself \n>:( ");
            frame.setVisible(false);
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
