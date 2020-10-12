package Home;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class main extends javax.swing.JFrame {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = dbConfig.getConn();

    public main() {
        initComponents();
        defaults();
        time();
        accountsTabls();
    }

    private void time() {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int Month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        datemi.setText("logged in at: " + day + "/" + Month + "/" + (year));
        datemi.setForeground(Color.RED);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        timeMi.setText("time: " + hour + ":" + min + ":" + sec + " sec");
        timeMi.setForeground(Color.RED);
    }

    private void clear() {

        txtArea.setText("");
        txtContact.setText("");
        txtDateIsseud.setDate(new Date());
        txtDuedate.setDate(new Date());
        txtFname.setText("");
        txtId.setText("");
        txtInterest.setText("");
        txtLname.setText("");
        txtTotalRetUrn.setText("");
        txtSpecificAmount.setText("");
        ComboAmountLend.setSelectedIndex(0);
        ComboCoraterol.setSelectedIndex(0);
        ComboDuration.setSelectedIndex(0);
        ComboState.setSelectedIndex(0);
        ComboServedby.setSelectedIndex(0);
        txtInterest.setText("");

        txtArea1.setText("");
        txtContact1.setText("");
        txtDateIsseud.setDate(new Date());
        txtDuedate1.setDate(new Date());
        txtFname1.setText("");
        txtId1.setText("");
        txtInterest1.setText("");
        txtLname1.setText("");
        txtTotalRetUrn1.setText("");
        txtSpecificAmount1.setText("");
        ComboAmountLend1.setSelectedIndex(0);
        ComboCoraterol1.setSelectedIndex(0);
        ComboDuration1.setSelectedIndex(0);
        ComboState1.setSelectedIndex(0);
        ComboServedby1.setSelectedIndex(0);
        txtInterest1.setText("");
    }

    private void defaults() {
        ComboAmountLend.setSelectedIndex(6);

    }
    public void pst(){
    
    
                       try {
             pst.setString(1, txtId.getText());
                        pst.setString(2, txtFname.getText());
                        pst.setString(3, txtLname.getText());
                        pst.setString(4, txtContact.getText());
                        pst.setString(5, ComboCoraterol.getSelectedItem().toString());
                        pst.setString(6, ComboState.getSelectedItem().toString());
                        pst.setString(7, txtArea.getText());
                        pst.setString(8, ComboAmountLend.getSelectedItem().toString());
                        pst.setString(9, ComboDuration.getSelectedItem().toString());
                        pst.setString(10, txtInterest.getText());
                        pst.setString(11, ((JTextField) txtDateIsseud.getDateEditor().getUiComponent()).getText());
                        pst.setString(12, ((JTextField) txtDuedate.getDateEditor().getUiComponent()).getText());
                        pst.setString(13, ComboServedby.getSelectedItem().toString());
                        pst.setString(14, txtSpecificAmount.getText());
                        pst.setString(15, txtTotalRetUrn.getText());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void clearAcc(){
    
    
        try {
             pst.setString(1, txtId1.getText());
                    pst.setString(2, txtFname1.getText());
                    pst.setString(3, txtLname1.getText());
                    pst.setString(4, txtContact1.getText());
                    pst.setString(5, ComboCoraterol1.getSelectedItem().toString());
                    pst.setString(6, ComboState1.getSelectedItem().toString());
                    pst.setString(7, txtArea1.getText());
                    pst.setString(8, ComboAmountLend1.getSelectedItem().toString());
                    pst.setString(9, ComboDuration1.getSelectedItem().toString());
                    pst.setString(10, txtInterest1.getText());
                    pst.setString(11, txtIssueTime.getText());
                    pst.setString(12, ((JTextField) txtDuedate1.getDateEditor().getUiComponent()).getText());
                    pst.setString(13, ComboServedby1.getSelectedItem().toString());
                    pst.setString(14, txtSpecificAmount1.getText());
                    pst.setString(15, txtTotalRetUrn1.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private  void accountsTabls(){
 //============================    setup account tables=========================   
        try {
            String sql="select * from withdrawals";
            String sql1="select * from deposits";
            String sql2="select * from overall";
 //========================withdrwal==================
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            tblWithdraw.setModel(DbUtils.resultSetToTableModel(rs));
            int i=0;
            for (int j = 0; j < tblWithdraw.getRowCount(); j++) {
               i+=Integer.parseInt(tblWithdraw.getValueAt(j, 13).toString());
               
            }
            lblwithdraw.setText(""+ i);
           
    //========================================= deposit========================     
            pst=conn.prepareStatement(sql1);
            rs=pst.executeQuery();
            tblDeposit.setModel(DbUtils.resultSetToTableModel(rs));
            try {
                int k=0;
            for (int j = 0; j < tblDeposit.getRowCount(); j++) {
               k+=Integer.parseInt(tblDeposit.getValueAt(j, 14).toString());
               
            }
            lbldeposit.setText(""+ k);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
   //======================================= members============================ 
   
            pst=conn.prepareStatement(sql2);
            rs=pst.executeQuery();
            tblmembersinfo.setModel(DbUtils.resultSetToTableModel(rs));
            
             int i2=0;
            for (int j = 0; j < tblmembersinfo.getRowCount(); j++) {
               i2+=Integer.parseInt(tblmembersinfo.getValueAt(j, 1).toString());
               
            }
            lblmambers.setText(""+ i2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel58 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pnl_layout = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        Action = new javax.swing.JPanel();
        btnGeneralInfo = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnnewAccount = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        btncloseAcc = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        CreateAcc = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtLname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInterest = new javax.swing.JTextField();
        ComboState = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        ComboCoraterol = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        ComboAmountLend = new javax.swing.JComboBox<>();
        ComboDuration = new javax.swing.JComboBox<>();
        txtDateIsseud = new com.toedter.calendar.JDateChooser();
        txtDuedate = new com.toedter.calendar.JDateChooser();
        ComboServedby = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotalRetUrn = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSpecificAmount = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        CloseAcc = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtLname1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtInterest1 = new javax.swing.JTextField();
        ComboState1 = new javax.swing.JComboBox<>();
        txtId1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtContact1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtFname1 = new javax.swing.JTextField();
        ComboCoraterol1 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        ComboAmountLend1 = new javax.swing.JComboBox<>();
        ComboDuration1 = new javax.swing.JComboBox<>();
        txtDuedate1 = new com.toedter.calendar.JDateChooser();
        ComboServedby1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea1 = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtTotalRetUrn1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtSpecificAmount1 = new javax.swing.JTextField();
        txtIssueTime = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        AdminLogIn = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        AdminPassword = new javax.swing.JPasswordField();
        jSeparator4 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        AdminUserName = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        AdminLoader = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        AboutPnl = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        ACCOUnts = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Withdrawn = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblWithdraw = new javax.swing.JTable();
        lblwithdraw = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        Deposited = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDeposit = new javax.swing.JTable();
        lbldeposit = new javax.swing.JLabel();
        lbldeposit1 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        MembersInfo = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblmembersinfo = new javax.swing.JTable();
        lblmambersj = new javax.swing.JLabel();
        lblmambers = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        datemi = new javax.swing.JMenu();
        timeMi = new javax.swing.JMenu();

        jLabel58.setText("jLabel58");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 60)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Main Section");
        jLabel5.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_layout.setBackground(new java.awt.Color(255, 255, 255));
        pnl_layout.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(255, 255, 255));

        Action.setBackground(new java.awt.Color(204, 204, 204));
        Action.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(255, 0, 0), new java.awt.Color(255, 0, 0), new java.awt.Color(0, 0, 204)));

        btnGeneralInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/Administrator-icon.png"))); // NOI18N
        btnGeneralInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralInfoActionPerformed(evt);
            }
        });

        jLabel32.setBackground(new java.awt.Color(240, 218, 27));
        jLabel32.setFont(new java.awt.Font("Monospaced", 0, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(204, 102, 0));
        jLabel32.setText("Withdraw");

        jLabel34.setBackground(new java.awt.Color(240, 218, 27));
        jLabel34.setFont(new java.awt.Font("Monospaced", 0, 36)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(204, 102, 0));
        jLabel34.setText("Admin");

        btnnewAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/get-money (1).png"))); // NOI18N
        btnnewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewAccountActionPerformed(evt);
            }
        });

        jLabel33.setBackground(new java.awt.Color(240, 218, 27));
        jLabel33.setFont(new java.awt.Font("Monospaced", 0, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 102, 0));
        jLabel33.setText("Deposit");

        btncloseAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/deposit.png"))); // NOI18N
        btncloseAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncloseAccActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ActionLayout = new javax.swing.GroupLayout(Action);
        Action.setLayout(ActionLayout);
        ActionLayout.setHorizontalGroup(
            ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ActionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnewAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(138, 138, 138)
                .addGroup(ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(btncloseAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129)
                .addGroup(ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ActionLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGeneralInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        ActionLayout.setVerticalGroup(
            ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ActionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ActionLayout.createSequentialGroup()
                        .addComponent(btnnewAccount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32))
                    .addGroup(ActionLayout.createSequentialGroup()
                        .addGroup(ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btncloseAcc)
                            .addComponent(btnGeneralInfo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33))))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 204), new java.awt.Color(255, 0, 0), new java.awt.Color(255, 0, 0), new java.awt.Color(102, 255, 255)));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/money-bag.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/team (1).png"))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Monospaced", 0, 36)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 102, 0));
        jLabel41.setText("About");

        jLabel40.setFont(new java.awt.Font("Monospaced", 0, 36)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(204, 102, 0));
        jLabel40.setText("Account");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/man.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Monospaced", 0, 36)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 102, 0));
        jLabel42.setText("Members");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jSeparator1.setBackground(new java.awt.Color(0, 153, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 204, 0));

        jSeparator2.setBackground(new java.awt.Color(0, 153, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 204, 0));

        jSeparator3.setBackground(new java.awt.Color(0, 153, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Action, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pnl_layout.add(home, "card2");

        CreateAcc.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(102, 255, 255), new java.awt.Color(102, 255, 255)), "Create Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 0, 24), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("LastName:");
        jLabel3.setPreferredSize(new java.awt.Dimension(15, 20));

        jLabel4.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Contact:");

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("ID:");
        jLabel1.setPreferredSize(new java.awt.Dimension(15, 20));

        jLabel12.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Refund Date:");

        txtLname.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Collateral:");

        txtInterest.setEditable(false);
        txtInterest.setFont(new java.awt.Font("Yu Gothic", 1, 36)); // NOI18N
        txtInterest.setForeground(new java.awt.Color(255, 0, 0));
        txtInterest.setPreferredSize(new java.awt.Dimension(10, 30));
        txtInterest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInterestActionPerformed(evt);
            }
        });

        ComboState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perfect", "Good", "Fair", "Bad" }));
        ComboState.setPreferredSize(new java.awt.Dimension(60, 30));

        txtId.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Collateral-State:");

        jLabel9.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("Duration:");

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("FirstName:");
        jLabel2.setPreferredSize(new java.awt.Dimension(52, 20));

        jLabel10.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("Date Issued:");

        jLabel11.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Interst Charged:");

        txtContact.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel13.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Served By:");

        txtFname.setPreferredSize(new java.awt.Dimension(10, 30));

        ComboCoraterol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "Phone", "Tablet", "Others" }));
        ComboCoraterol.setPreferredSize(new java.awt.Dimension(66, 30));

        jLabel8.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Money_Lend:");

        ComboAmountLend.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1000-1950", "3000-4950", "5000-6950", "7000-9950", "10000-14950", "Others", "SelectRange", " " }));
        ComboAmountLend.setPreferredSize(new java.awt.Dimension(66, 30));
        ComboAmountLend.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboAmountLendItemStateChanged(evt);
            }
        });

        ComboDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 week", "2 weeks", "3 weeks", "4 weeks", "Other" }));
        ComboDuration.setPreferredSize(new java.awt.Dimension(64, 30));

        txtDateIsseud.setPreferredSize(new java.awt.Dimension(91, 30));

        txtDuedate.setPreferredSize(new java.awt.Dimension(91, 30));

        ComboServedby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bonface", "Anthony", "Ken", "Wilson" }));
        ComboServedby.setPreferredSize(new java.awt.Dimension(66, 30));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jLabel14.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 102));
        jLabel14.setText("Other Infor:");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("Total:");

        txtTotalRetUrn.setEditable(false);
        txtTotalRetUrn.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        txtTotalRetUrn.setForeground(new java.awt.Color(255, 0, 0));

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 102));
        jLabel16.setText("Specific_Amount:");

        txtSpecificAmount.setPreferredSize(new java.awt.Dimension(59, 30));
        txtSpecificAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSpecificAmountFocusGained(evt);
            }
        });
        txtSpecificAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSpecificAmountKeyReleased(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/legal-issue.png"))); // NOI18N
        jButton10.setText("Submit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/Home-icon.png"))); // NOI18N
        jButton11.setText("Back");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/Clear-icon.png"))); // NOI18N
        jButton12.setText("Clear");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtContact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtLname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ComboCoraterol, javax.swing.GroupLayout.Alignment.TRAILING, 0, 200, Short.MAX_VALUE))
                            .addComponent(ComboState, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton12)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(195, 195, 195)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel15))
                                    .addGap(26, 26, 26)
                                    .addComponent(txtDateIsseud, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addComponent(ComboDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addGap(28, 28, 28)
                                            .addComponent(txtInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(66, 66, 66)
                                    .addComponent(jButton10))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(167, 167, 167)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel8))
                            .addGap(28, 28, 28)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ComboAmountLend, 0, 200, Short.MAX_VALUE)
                                .addComponent(txtSpecificAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalRetUrn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDuedate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboServedby, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(ComboAmountLend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtSpecificAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(ComboCoraterol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel14))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(ComboDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateIsseud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtDuedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboServedby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalRetUrn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout CreateAccLayout = new javax.swing.GroupLayout(CreateAcc);
        CreateAcc.setLayout(CreateAccLayout);
        CreateAccLayout.setHorizontalGroup(
            CreateAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateAccLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CreateAccLayout.setVerticalGroup(
            CreateAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateAccLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 53, Short.MAX_VALUE))
        );

        pnl_layout.add(CreateAcc, "card3");

        CloseAcc.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(102, 255, 255), new java.awt.Color(102, 255, 255)), "Clear Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 0, 24), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("LastName:");
        jLabel17.setPreferredSize(new java.awt.Dimension(15, 20));

        jLabel18.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 102));
        jLabel18.setText("Contact:");

        jLabel19.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 102));
        jLabel19.setText("ID:");
        jLabel19.setPreferredSize(new java.awt.Dimension(15, 20));

        jLabel20.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Refund Date:");

        txtLname1.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel21.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setText("Collateral:");

        txtInterest1.setEditable(false);
        txtInterest1.setFont(new java.awt.Font("Yu Gothic", 1, 36)); // NOI18N
        txtInterest1.setForeground(new java.awt.Color(255, 0, 0));
        txtInterest1.setPreferredSize(new java.awt.Dimension(10, 30));
        txtInterest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInterest1ActionPerformed(evt);
            }
        });

        ComboState1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perfect", "Good", "Fair", "Bad" }));
        ComboState1.setPreferredSize(new java.awt.Dimension(60, 30));

        txtId1.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel22.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 102));
        jLabel22.setText("Collateral-State:");

        jLabel23.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 102));
        jLabel23.setText("Duration:");

        jLabel24.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 102));
        jLabel24.setText("FirstName:");
        jLabel24.setPreferredSize(new java.awt.Dimension(52, 20));

        jLabel25.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 102));
        jLabel25.setText("Date Issued:");

        jLabel26.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 102));
        jLabel26.setText("Interst Charged:");

        txtContact1.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel27.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 102));
        jLabel27.setText("Served By:");

        txtFname1.setPreferredSize(new java.awt.Dimension(10, 30));

        ComboCoraterol1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "Phone", "Tablet", "Others" }));
        ComboCoraterol1.setPreferredSize(new java.awt.Dimension(66, 30));

        jLabel28.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 102));
        jLabel28.setText("Money_Lend:");

        ComboAmountLend1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1000-1950", "3000-4950", "5000-6950", "7000-9950", "10000-14950", "Others", "SelectRange", " " }));
        ComboAmountLend1.setPreferredSize(new java.awt.Dimension(66, 30));
        ComboAmountLend1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboAmountLend1ItemStateChanged(evt);
            }
        });

        ComboDuration1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 week", "2 weeks", "3 weeks", "4 weeks", "Other" }));
        ComboDuration1.setPreferredSize(new java.awt.Dimension(64, 30));

        txtDuedate1.setPreferredSize(new java.awt.Dimension(91, 30));

        ComboServedby1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bonface", "Anthony", "Ken", "Wilson" }));
        ComboServedby1.setPreferredSize(new java.awt.Dimension(66, 30));

        txtArea1.setColumns(20);
        txtArea1.setRows(5);
        jScrollPane2.setViewportView(txtArea1);

        jLabel29.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 102));
        jLabel29.setText("Other Infor:");

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 102));
        jLabel30.setText("Total:");

        txtTotalRetUrn1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        txtTotalRetUrn1.setForeground(new java.awt.Color(255, 0, 0));

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 102));
        jLabel31.setText("Specific_Amount:");

        txtSpecificAmount1.setEditable(false);
        txtSpecificAmount1.setPreferredSize(new java.awt.Dimension(59, 30));
        txtSpecificAmount1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSpecificAmount1FocusGained(evt);
            }
        });
        txtSpecificAmount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSpecificAmount1KeyReleased(evt);
            }
        });

        txtIssueTime.setPreferredSize(new java.awt.Dimension(59, 30));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/legal-issue.png"))); // NOI18N
        jButton13.setText("Execute");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/Home-icon.png"))); // NOI18N
        jButton14.setText("Back");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/Clear-icon.png"))); // NOI18N
        jButton15.setText("Clear");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/search-icon.png"))); // NOI18N
        jButton16.setText("Search");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtId1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFname1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtContact1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtLname1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ComboCoraterol1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 200, Short.MAX_VALUE))
                            .addComponent(ComboState1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton15)
                        .addGap(33, 33, 33)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(189, 189, 189)
                                    .addComponent(jLabel31))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28)))
                            .addGap(28, 28, 28)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ComboAmountLend1, 0, 200, Short.MAX_VALUE)
                                .addComponent(txtSpecificAmount1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGap(184, 184, 184)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel27)
                                        .addComponent(jLabel30))
                                    .addGap(228, 228, 228))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel26))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addComponent(ComboDuration1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addGap(28, 28, 28)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtInterest1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(txtIssueTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addGap(127, 127, 127)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalRetUrn1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDuedate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboServedby1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jButton13)))))
                .addContainerGap(943, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(ComboAmountLend1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtSpecificAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtContact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(ComboCoraterol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel29))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(ComboDuration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInterest1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel25))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(txtIssueTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtDuedate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboServedby1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalRetUrn1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CloseAccLayout = new javax.swing.GroupLayout(CloseAcc);
        CloseAcc.setLayout(CloseAccLayout);
        CloseAccLayout.setHorizontalGroup(
            CloseAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CloseAccLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CloseAccLayout.setVerticalGroup(
            CloseAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CloseAccLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pnl_layout.add(CloseAcc, "card4");

        AdminLogIn.setBackground(new java.awt.Color(255, 255, 255));
        AdminLogIn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 255, 0));
        jLabel43.setText("Admin LogIn");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));

        jLabel38.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/ModernXP-07-Keys-icon_2.png"))); // NOI18N

        jSeparator6.setBackground(new java.awt.Color(51, 255, 51));
        jSeparator6.setForeground(new java.awt.Color(51, 255, 51));
        jSeparator6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 51), 1, true));

        jLabel37.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 0, 0));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/green-lock-icon.png"))); // NOI18N

        AdminPassword.setText("Admin Ullllllllllllllllll");
        AdminPassword.setBorder(null);
        AdminPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                AdminPasswordFocusGained(evt);
            }
        });

        jSeparator4.setBackground(new java.awt.Color(51, 255, 51));
        jSeparator4.setForeground(new java.awt.Color(51, 255, 51));
        jSeparator4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 255, 51), 1, true));

        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/if_login_173049.png"))); // NOI18N
        jButton4.setText("Login");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        AdminUserName.setText("usernae");
        AdminUserName.setBorder(null);
        AdminUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                AdminUserNameFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel37))
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(AdminPassword)
                                .addComponent(AdminUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AdminUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(4, 4, 4)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton6.setBackground(new java.awt.Color(255, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/back.png"))); // NOI18N
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminLogInLayout = new javax.swing.GroupLayout(AdminLogIn);
        AdminLogIn.setLayout(AdminLogInLayout);
        AdminLogInLayout.setHorizontalGroup(
            AdminLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminLogInLayout.createSequentialGroup()
                .addGroup(AdminLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdminLogInLayout.createSequentialGroup()
                        .addGroup(AdminLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AdminLogInLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43))
                            .addGroup(AdminLogInLayout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 335, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminLogInLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        AdminLogInLayout.setVerticalGroup(
            AdminLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminLogInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdminLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(89, 89, 89)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(84, 84, 84))
        );

        pnl_layout.add(AdminLogIn, "card6");

        AdminLoader.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/digital_number_five_green_animation_clipart.gif"))); // NOI18N

        javax.swing.GroupLayout AdminLoaderLayout = new javax.swing.GroupLayout(AdminLoader);
        AdminLoader.setLayout(AdminLoaderLayout);
        AdminLoaderLayout.setHorizontalGroup(
            AdminLoaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminLoaderLayout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );
        AdminLoaderLayout.setVerticalGroup(
            AdminLoaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminLoaderLayout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(334, Short.MAX_VALUE))
        );

        pnl_layout.add(AdminLoader, "card7");

        AboutPnl.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 102, 102)));

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel45.setFont(new java.awt.Font("Vladimir Script", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 204, 255));
        jLabel45.setText("Records");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 204, 204));
        jLabel36.setText("Money ");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 204, 255));
        jLabel46.setText("CopyRight@2017");

        jLabel55.setFont(new java.awt.Font("Vladimir Script", 0, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 204, 255));
        jLabel55.setText("Management System");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel55)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(45, 45, 45))
        );

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/ptc.jpg"))); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/back.png"))); // NOI18N
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setForeground(new java.awt.Color(153, 153, 153));

        jLabel51.setFont(new java.awt.Font("Yu Gothic UI Light", 3, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 0));
        jLabel51.setText("ferreirawillis");

        jLabel48.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 255, 255));
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/facebook-icon.png"))); // NOI18N

        jLabel47.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 255, 255));
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/mail-icon.png"))); // NOI18N

        jLabel50.setFont(new java.awt.Font("Yu Gothic UI Light", 3, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(102, 102, 0));
        jLabel50.setText("gatheruwilson@gmail.com");

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/whatsapp-icon.png"))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Yu Gothic UI Light", 3, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 0));
        jLabel57.setText("0735182056");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButton5)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout AboutPnlLayout = new javax.swing.GroupLayout(AboutPnl);
        AboutPnl.setLayout(AboutPnlLayout);
        AboutPnlLayout.setHorizontalGroup(
            AboutPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutPnlLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        AboutPnlLayout.setVerticalGroup(
            AboutPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutPnlLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pnl_layout.add(AboutPnl, "card7");

        ACCOUnts.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 153, 51));
        jTabbedPane1.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N

        Withdrawn.setBackground(new java.awt.Color(255, 255, 255));

        tblWithdraw.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblWithdraw);

        lblwithdraw.setFont(new java.awt.Font("Onyx", 0, 48)); // NOI18N
        lblwithdraw.setForeground(new java.awt.Color(255, 0, 0));

        jLabel49.setFont(new java.awt.Font("Onyx", 0, 48)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 0, 0));
        jLabel49.setText("Total:");

        jLabel52.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 102));
        jLabel52.setText("Amount of money lend out:");

        jButton9.setForeground(new java.awt.Color(204, 0, 0));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/back.png"))); // NOI18N
        jButton9.setText("Back");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout WithdrawnLayout = new javax.swing.GroupLayout(Withdrawn);
        Withdrawn.setLayout(WithdrawnLayout);
        WithdrawnLayout.setHorizontalGroup(
            WithdrawnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WithdrawnLayout.createSequentialGroup()
                .addContainerGap(318, Short.MAX_VALUE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblwithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213)
                .addComponent(jButton9)
                .addGap(44, 44, 44))
            .addGroup(WithdrawnLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabel52)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(WithdrawnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        WithdrawnLayout.setVerticalGroup(
            WithdrawnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WithdrawnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(WithdrawnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WithdrawnLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(WithdrawnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblwithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(WithdrawnLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPane1.addTab("WITHDRAWN", Withdrawn);

        Deposited.setBackground(new java.awt.Color(255, 255, 255));

        tblDeposit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblDeposit);

        lbldeposit.setFont(new java.awt.Font("Onyx", 0, 48)); // NOI18N
        lbldeposit.setForeground(new java.awt.Color(204, 0, 0));

        lbldeposit1.setFont(new java.awt.Font("Onyx", 0, 48)); // NOI18N
        lbldeposit1.setForeground(new java.awt.Color(255, 0, 0));
        lbldeposit1.setText("Total:");

        jLabel53.setFont(new java.awt.Font("Tw Cen MT", 2, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 0, 102));
        jLabel53.setText("Amount Of Money Paid Back Plus Interest:");

        jButton8.setForeground(new java.awt.Color(204, 0, 0));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/back.png"))); // NOI18N
        jButton8.setText("Back");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DepositedLayout = new javax.swing.GroupLayout(Deposited);
        Deposited.setLayout(DepositedLayout);
        DepositedLayout.setHorizontalGroup(
            DepositedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepositedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(DepositedLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jLabel53)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DepositedLayout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addComponent(lbldeposit1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbldeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257)
                .addComponent(jButton8)
                .addGap(43, 43, 43))
        );
        DepositedLayout.setVerticalGroup(
            DepositedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepositedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(DepositedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DepositedLayout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(30, 30, 30)
                        .addGroup(DepositedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbldeposit1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbldeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("BEPOSITED", Deposited);

        MembersInfo.setBackground(new java.awt.Color(255, 255, 255));

        tblmembersinfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblmembersinfo);

        lblmambersj.setFont(new java.awt.Font("Onyx", 0, 48)); // NOI18N
        lblmambersj.setForeground(new java.awt.Color(255, 0, 0));
        lblmambersj.setText("Total:");

        lblmambers.setFont(new java.awt.Font("Onyx", 0, 48)); // NOI18N
        lblmambers.setForeground(new java.awt.Color(204, 0, 0));

        jLabel54.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 102));
        jLabel54.setText("Overall Amount Contributed By All Members:");

        jButton7.setForeground(new java.awt.Color(204, 0, 0));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/back.png"))); // NOI18N
        jButton7.setText("Back");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MembersInfoLayout = new javax.swing.GroupLayout(MembersInfo);
        MembersInfo.setLayout(MembersInfoLayout);
        MembersInfoLayout.setHorizontalGroup(
            MembersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MembersInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MembersInfoLayout.createSequentialGroup()
                .addContainerGap(324, Short.MAX_VALUE)
                .addComponent(lblmambersj)
                .addGap(18, 18, 18)
                .addComponent(lblmambers, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216)
                .addComponent(jButton7)
                .addGap(45, 45, 45))
            .addGroup(MembersInfoLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MembersInfoLayout.setVerticalGroup(
            MembersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MembersInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(MembersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MembersInfoLayout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(11, 11, 11)
                        .addGroup(MembersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblmambers, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblmambersj, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("MEMBERS INFO", MembersInfo);

        javax.swing.GroupLayout ACCOUntsLayout = new javax.swing.GroupLayout(ACCOUnts);
        ACCOUnts.setLayout(ACCOUntsLayout);
        ACCOUntsLayout.setHorizontalGroup(
            ACCOUntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCOUntsLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ACCOUntsLayout.setVerticalGroup(
            ACCOUntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCOUntsLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );

        pnl_layout.add(ACCOUnts, "card8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_layout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_layout, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/logout.png"))); // NOI18N
        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home/img/power-button.png"))); // NOI18N
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        datemi.setText("date");
        jMenuBar1.add(datemi);

        timeMi.setText("time");
        jMenuBar1.add(timeMi);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(944, 755));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtInterestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInterestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInterestActionPerformed

    private void btnnewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewAccountActionPerformed
        home.hide();
        CreateAcc.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnewAccountActionPerformed

    private void ComboAmountLendItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboAmountLendItemStateChanged
        txtSpecificAmount.setText(null);
        txtTotalRetUrn.setText(null);
        txtInterest.setText(null);
        switch (ComboAmountLend.getSelectedIndex()) {
            case 0:
                txtInterest.setText("300");
                break;
            case 1:
                txtInterest.setText("500");
                break;
            case 2:
                txtInterest.setText("700");
                break;
            case 3:
                txtInterest.setText("1000");
                break;
            case 4:
                txtInterest.setText("1400");
                break;
            default:
                txtInterest.setText("N/A");

                txtTotalRetUrn.setText("N/A");
                txtInterest.setText("N/A");
                txtSpecificAmount.setText("Select Range First");
                break;
        }
    }//GEN-LAST:event_ComboAmountLendItemStateChanged

    private void txtSpecificAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSpecificAmountKeyReleased
        int Interest = 0;
        int Amount = 0;

        try {
            Amount = (int) Double.parseDouble(txtSpecificAmount.getText());
            try {
                Interest = (int) Double.parseDouble(txtInterest.getText());
                txtTotalRetUrn.setText("" + (Amount + Interest));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "select Range first");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Number Input Only");
        }

    }//GEN-LAST:event_txtSpecificAmountKeyReleased

    private void txtSpecificAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSpecificAmountFocusGained
        //txtSpecificAmount.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpecificAmountFocusGained

    private void btncloseAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseAccActionPerformed
        home.hide();
        CloseAcc.show();

    }//GEN-LAST:event_btncloseAccActionPerformed

    private void txtInterest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInterest1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInterest1ActionPerformed

    private void ComboAmountLend1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboAmountLend1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboAmountLend1ItemStateChanged

    private void txtSpecificAmount1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSpecificAmount1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpecificAmount1FocusGained

    private void txtSpecificAmount1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSpecificAmount1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpecificAmount1KeyReleased

    private void btnGeneralInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralInfoActionPerformed
        AdminLogIn.show();
        home.hide();
    }//GEN-LAST:event_btnGeneralInfoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        home h = new home();
        h.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void AdminPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AdminPasswordFocusGained
        AdminPassword.setText("");
    }//GEN-LAST:event_AdminPasswordFocusGained

    private void AdminUserNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AdminUserNameFocusGained
        AdminUserName.setText("");
    }//GEN-LAST:event_AdminUserNameFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String Sql = "Select * from Admin where User='" + AdminUserName.getText() + "' and Password='" + AdminPassword.getText() + "'";
        try {
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                // home.hide();
                AdminLoader.show();
                AdminLogIn.hide();
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // JOptionPane.showMessageDialog(null, "setting thing up for you horse");

                        Admin a = new Admin();
                        a.setVisible(true);
                        dispose();
                    }
                }, 1000 * 5);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong User Name Or Password", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        home.hide();
        //AboutPnl.show();
         try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        home.show();
        AboutPnl.hide();
         try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        AdminLogIn.hide();
        home.show();
         try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        home.hide();
        ACCOUnts.show();
         try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ACCOUnts.hide();
        home.show();
         try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
           ACCOUnts.hide();
           home.show();// TODO add your handling code here:
            try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
ACCOUnts.hide();
home.show();// TODO add your handling code here:
 try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
                } catch (Exception e) {
                }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       if (txtId.getText().equals("") || txtLname.getText().equals("") || txtContact.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "FILL IN ALL FIELDS ", "BLANK FIELDS DITECTED", JOptionPane.ERROR_MESSAGE);
        } else {
            int SpeficAm = (int) Double.parseDouble(txtSpecificAmount.getText());
            int range = ComboAmountLend.getSelectedIndex();
            int Amount = (int) Double.parseDouble(txtSpecificAmount.getText());
            int Total = 0;
            if (range == 0 && Amount <= 2950 || range == 1 && Amount <= 4950 || range == 2 && Amount <= 6950 || range == 3 && Amount <= 9950 || range == 4 && Amount <= 14950) {
                if (range == 0 && Amount >= 1000 || range == 1 && Amount >= 3000 || range == 2 && Amount >= 5000 || range == 3 && Amount >= 7000 || range == 4 && Amount >= 1000) {
                    try {
                        try {
                            Total = SpeficAm + Amount;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        String sql = "insert into BAccount(Id,Fname,Lname,Contact,Collateral,CollateralState,OtherInfor,MoneyLend,Duration,Interest,Issued,ReturnDate,Servedby,SpecificAmount,TotalAmount)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        String sql2 = "insert into withdrawals(Id,Fname,Lname,Contact,Collateral,CollateralState,OtherInfor,MoneyLend,Duration,Interest,Issued,ReturnDate,Servedby,SpecificAmount,TotalAmount)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                        pst = conn.prepareStatement(sql);
                       pst();
                        pst.execute();
                         pst = conn.prepareStatement(sql2);
                       pst();
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "success");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, txtSpecificAmount.getText() + " Doesn'n fall in " + ComboAmountLend.getSelectedItem().toString(), " ", JOptionPane.WARNING_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(null, txtSpecificAmount.getText() + " Doesn'n fall in " + ComboAmountLend.getSelectedItem().toString(), " ", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        accountsTabls();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
 CreateAcc.hide();
        home.show();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
clear();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
try {
            String sql2 = "Select * from BAccount where Id='" + txtId1.getText() + "'";
            //String sql3 = "Select * from BAccount where Id='" + txtId1.getText() + "'";
            pst = conn.prepareStatement(sql2);
            pst = conn.prepareStatement(sql2);
            rs = pst.executeQuery();
            if (rs.next()) {
                String sql = "Insert Into CloseAc(Id,Fname,Lname,Contact,Collateral,CollateralState,OtherInfor,MoneyLend,Duration,Interest,Issued,ReturnDate,Servedby,SpecificAmount,TotalAmount)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    pst = conn.prepareStatement(sql);
                    clearAcc();
                    pst.execute();
                    
           String sql3 = "Insert Into deposits(Id,Fname,Lname,Contact,Collateral,CollateralState,OtherInfor,MoneyLend,Duration,Interest,Issued,ReturnDate,Servedby,SpecificAmount,TotalAmount)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";         
                    pst = conn.prepareStatement(sql3);
                   clearAcc();
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Account Cleared");

                    String sql1 = "delete from BAccount where Id='" + txtId1.getText() + "'";
                    pst = conn.prepareStatement(sql1);
                    pst.execute();
                    accountsTabls();
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No result matching the given Id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
 String sql = "Select * from BAccount where Id='" + txtId1.getText() + "'";
        try {

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtFname1.setText(rs.getString("Fname"));
                txtLname1.setText(rs.getString("Lname"));
                txtContact1.setText(rs.getString("Contact"));

                ComboCoraterol1.setSelectedItem(rs.getString("Collateral"));
                ComboState1.setSelectedItem(rs.getString("CollateralState"));
                txtArea1.setText(rs.getString("Otherinfor"));
                ComboAmountLend1.setSelectedItem(rs.getString("MoneyLend"));
                ComboDuration1.setSelectedItem(rs.getString("Duration"));
                txtInterest1.setText(rs.getString("Interest"));
                txtSpecificAmount1.setText(rs.getString("SpecificAmount"));
                txtIssueTime.setText(rs.getString("Issued"));
                ComboServedby1.setSelectedItem(rs.getString("Servedby"));
                txtTotalRetUrn1.setText(rs.getString("TotalAmount"));

            } else {
                JOptionPane.showMessageDialog(null, "no Record Found");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       home.show();
       CloseAcc.hide();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
clear();
    }//GEN-LAST:event_jButton15ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ACCOUnts;
    private javax.swing.JPanel AboutPnl;
    private javax.swing.JPanel Action;
    private javax.swing.JPanel AdminLoader;
    private javax.swing.JPanel AdminLogIn;
    private javax.swing.JPasswordField AdminPassword;
    private javax.swing.JTextField AdminUserName;
    private javax.swing.JPanel CloseAcc;
    private javax.swing.JComboBox<String> ComboAmountLend;
    private javax.swing.JComboBox<String> ComboAmountLend1;
    private javax.swing.JComboBox<String> ComboCoraterol;
    private javax.swing.JComboBox<String> ComboCoraterol1;
    private javax.swing.JComboBox<String> ComboDuration;
    private javax.swing.JComboBox<String> ComboDuration1;
    private javax.swing.JComboBox<String> ComboServedby;
    private javax.swing.JComboBox<String> ComboServedby1;
    private javax.swing.JComboBox<String> ComboState;
    private javax.swing.JComboBox<String> ComboState1;
    private javax.swing.JPanel CreateAcc;
    private javax.swing.JPanel Deposited;
    private javax.swing.JPanel MembersInfo;
    private javax.swing.JPanel Withdrawn;
    private javax.swing.JButton btnGeneralInfo;
    private javax.swing.JButton btncloseAcc;
    private javax.swing.JButton btnnewAccount;
    private javax.swing.JMenu datemi;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbldeposit;
    private javax.swing.JLabel lbldeposit1;
    private javax.swing.JLabel lblmambers;
    private javax.swing.JLabel lblmambersj;
    private javax.swing.JLabel lblwithdraw;
    private javax.swing.JPanel pnl_layout;
    private javax.swing.JTable tblDeposit;
    private javax.swing.JTable tblWithdraw;
    private javax.swing.JTable tblmembersinfo;
    private javax.swing.JMenu timeMi;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextArea txtArea1;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtContact1;
    private com.toedter.calendar.JDateChooser txtDateIsseud;
    private com.toedter.calendar.JDateChooser txtDuedate;
    private com.toedter.calendar.JDateChooser txtDuedate1;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtFname1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtInterest;
    private javax.swing.JTextField txtInterest1;
    private javax.swing.JTextField txtIssueTime;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtLname1;
    private javax.swing.JTextField txtSpecificAmount;
    private javax.swing.JTextField txtSpecificAmount1;
    private javax.swing.JTextField txtTotalRetUrn;
    private javax.swing.JTextField txtTotalRetUrn1;
    // End of variables declaration//GEN-END:variables
}
