package Letsdo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
public class Tabular{
    String finalres;
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    realWork ahmed = new realWork();
    letsStart pool = new letsStart();
    int [] minterms;
    int[][] step0 ;
    int [][] step1;
    int [][] remain;
    LinkedList<int[][]> steps;
    LinkedList<boolean[]> check;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tabular window = new Tabular();
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
    public Tabular() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(100, 100, 615, 481);
        frame.setTitle("Project Main");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblTabularApp = new JLabel("Tabular App");
        lblTabularApp.setForeground(Color.PINK);
        lblTabularApp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 44));
        lblTabularApp.setBounds(156, 11, 359, 61);
        frame.getContentPane().add(lblTabularApp);
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "Thanks for using \n Created By :\n Ahmed Khaled Saad Hassan (7) \n Ahmed Ezzat Elmaghawry (13)\nAhmed Moustafa Youssef ElNaggar(19)\nArsanuous Eissa Atia (21)\nMarwan Morsy Mohamed Morsy (75)");
                frame.setVisible(false);
            }
        });
        btnExit.setBackground(SystemColor.menu);
        btnExit.setFont(new Font("Viner Hand ITC", Font.BOLD, 19));
        btnExit.setForeground(new Color(255, 0, 51));
        btnExit.setBounds(510, 418, 89, 26);
        frame.getContentPane().add(btnExit);
        
        JLabel lblMinTerms = new JLabel("Min Terms :");
        lblMinTerms.setForeground(new Color(30, 144, 255));
        lblMinTerms.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMinTerms.setBounds(10, 101, 105, 14);
        frame.getContentPane().add(lblMinTerms);
        
        JLabel lblDontCare = new JLabel("Don't Care :");
        lblDontCare.setForeground(new Color(30, 144, 255));
        lblDontCare.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDontCare.setBounds(347, 101, 105, 14);
        frame.getContentPane().add(lblDontCare);
        
        textField = new JTextField();
        textField.setForeground(Color.PINK);
        textField.setBackground(Color.CYAN);
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setBounds(10, 126, 252, 26);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setForeground(Color.PINK);
        textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField_1.setColumns(10);
        textField_1.setBackground(Color.CYAN);
        textField_1.setBounds(347, 126, 252, 26);
        frame.getContentPane().add(textField_1);
        
        JButton btnClacualate = new JButton("Calcualate");
        btnClacualate.setForeground(new Color(255, 0, 51));
        btnClacualate.setFont(new Font("Viner Hand ITC", Font.BOLD, 14));
        btnClacualate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String x =textField.getText() ;
                String y =textField_1.getText() ;
                String z = textField.getText()+textField_1.getText();
                 minterms = ahmed.Min(textField.getText());
                 step0 = ahmed.StepZero(x+" "+y);
                 step1 = ahmed.StepOne(step0);
                remain = ahmed.remain;
                steps = ahmed.di;
                check = ahmed.ch;
                if(y.length() == 0)
                letsStart.call(x, y,0);
                else
                    letsStart.call(x, y, 1);
                finalres = letsStart.resa;
                next second = new next(minterms , step0 , step1 , remain , steps , check,finalres);
                frame.setVisible(false); // Hide current frame
                 second.setVisible(true);
            }
        });
        btnClacualate.setBounds(238, 211, 156, 31);
        frame.getContentPane().add(btnClacualate);
        
    }
    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        frame.setVisible(b);
    }
}
