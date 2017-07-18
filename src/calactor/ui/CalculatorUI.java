/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calactor.ui;

import calactor.io.LuuDocFileIO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author Hong Hiep
 */
public class CalculatorUI extends JFrame implements ActionListener {

    JTabbedPane tab;

//    JTextField txtManHinh1, txtManHinh2;
    JTextArea txtManHinh1, txtManHinh2, txtHistory;

    JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnPhanTram, btnCanBac2, btnBinhPhuong, btnNghichDao,
            btnC, btnCE, btnNhan, btnChia, btnXoa, btnCong, btnTru, btnBang, btnPhay, btnThemDau,
            btnXoaHistory;

    JButton btnSelected = null;

    String chuoiEX = "";
    String chuoi1 = "";
    String chuoi2 = "0";
    Boolean check = false;

    Boolean checkBang = false;

    Boolean checkXulyLoi = false;

    Double numEX, num1, num2, numMath, result;

    Boolean checkAdd = false;
    Boolean checkSub = false;
    Boolean checkMulti = false;
    Boolean checkDiv = false;

    Integer checkMath = 0;
    Boolean checkBinhPhuong = false;
    Boolean checkPhanTram = false;
    Boolean checkNghichDao = false;

    public CalculatorUI(String title) {
        this.setTitle(title);
        addControls();
        addEvents();
    }

    public void addControls() {

        Container con = getContentPane();
        tab = new JTabbedPane();
        con.add(tab);

        JPanel pnCalculate = new JPanel();
        pnCalculate.setLayout(new BoxLayout(pnCalculate, BoxLayout.Y_AXIS));
        JPanel pnpnCalculateTren = new JPanel();
        pnpnCalculateTren.setLayout(new BorderLayout());
        JPanel pnpnCalculateDuoi = new JPanel();
        pnpnCalculateDuoi.setLayout(new BorderLayout());
        pnCalculate.add(pnpnCalculateTren);
        pnCalculate.add(pnpnCalculateDuoi);
        tab.add(pnCalculate, "Calculate");

        JPanel pnHistory = new JPanel();
        tab.add(pnHistory, "History");

        JPanel pnManHinh = new JPanel();
        pnManHinh.setLayout(new GridLayout(2, 1));

        Font font = new Font("Arial", Font.BOLD, 30);
        txtManHinh1 = new JTextArea(1, 20);
//        txtManHinh1.setPreferredSize(new Dimension(20, 20));
        pnManHinh.add(txtManHinh1);

        txtManHinh2 = new JTextArea(1, 20);
        txtManHinh2.setFont(font);

//        txtManHinh2.setPreferredSize(new Dimension(20, 20));
        pnManHinh.add(txtManHinh2);
        pnpnCalculateTren.add(pnManHinh, BorderLayout.CENTER);

        JPanel pnBanPhim = new JPanel();
        pnBanPhim.setLayout(new GridLayout(6, 4, 2, 2));
        pnpnCalculateDuoi.add(pnBanPhim, BorderLayout.CENTER);
        pnpnCalculateDuoi.setSize(new Dimension(50, 50));

        btnPhanTram = new JButton("%");
        pnBanPhim.add(btnPhanTram);
        btnCanBac2 = new JButton("√");
        pnBanPhim.add(btnCanBac2);
        btnBinhPhuong = new JButton("x^2");
        pnBanPhim.add(btnBinhPhuong);
        btnNghichDao = new JButton("1/x");
        pnBanPhim.add(btnNghichDao);
        btnCE = new JButton("CE");
        pnBanPhim.add(btnCE);
        btnC = new JButton("C");
        pnBanPhim.add(btnC);
        btnXoa = new JButton("<-");
        pnBanPhim.add(btnXoa);
        btnChia = new JButton(":");
        pnBanPhim.add(btnChia);
        btn7 = new JButton("7");
        pnBanPhim.add(btn7);
        btn8 = new JButton("8");
        pnBanPhim.add(btn8);
        btn9 = new JButton("9");
        pnBanPhim.add(btn9);
        btnNhan = new JButton("X");
        pnBanPhim.add(btnNhan);
        btn4 = new JButton("4");
        pnBanPhim.add(btn4);
        btn5 = new JButton("5");
        pnBanPhim.add(btn5);
        btn6 = new JButton("6");
        pnBanPhim.add(btn6);
        btnTru = new JButton("-");
        pnBanPhim.add(btnTru);
        btn1 = new JButton("1");
        pnBanPhim.add(btn1);
        btn2 = new JButton("2");
        pnBanPhim.add(btn2);
        btn3 = new JButton("3");
        pnBanPhim.add(btn3);
        btnCong = new JButton("+");
        pnBanPhim.add(btnCong);
        btnThemDau = new JButton("(-)");
        pnBanPhim.add(btnThemDau);
        btn0 = new JButton("0");
        pnBanPhim.add(btn0);
        btnPhay = new JButton(".");
        pnBanPhim.add(btnPhay);
        btnBang = new JButton("=");
        pnBanPhim.add(btnBang);

        pnHistory.setLayout(new BoxLayout(pnHistory, BoxLayout.Y_AXIS));
        txtHistory = new JTextArea(15, 20);
        JScrollPane sc = new JScrollPane(txtHistory,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        btnXoaHistory = new JButton("Xóa lịch sử");
        pnHistory.add(sc);
        pnHistory.add(btnXoaHistory);
    }

    public void addEvents() {
        LuuDocFileIO io = new LuuDocFileIO();
        txtHistory.setText(io.docFile());
        txtHistory.setEditable(false);
        txtManHinh1.setEditable(false);
        txtManHinh2.setEditable(false);
//        txtManHinh2.setCaretPosition(PROPERTIES);
        txtManHinh2.setText(chuoi2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btn0.addActionListener(this);
        btnCong.addActionListener(this);
        btnTru.addActionListener(this);
        btnNhan.addActionListener(this);
        btnChia.addActionListener(this);
        btnBang.addActionListener(this);
        btnC.addActionListener(this);
        btnCE.addActionListener(this);
        btnXoa.addActionListener(this);
        btnCanBac2.addActionListener(this);
        btnThemDau.addActionListener(this);
        btnBinhPhuong.addActionListener(this);
        btnNghichDao.addActionListener(this);
        btnPhay.addActionListener(this);
        btnPhanTram.addActionListener(this);
        btnXoaHistory.addActionListener(this);

    }

    public void showWindow() {
        this.setSize(330, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LuuDocFileIO io = new LuuDocFileIO();
        Object source = e.getSource();
        if (source == btn1) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("1");
            check = false;

        } else if (source == btn2) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("2");
            check = false;

        } else if (source == btn3) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("3");
            check = false;

        } else if (source == btn4) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("4");
            check = false;

        } else if (source == btn5) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("5");
            check = false;

        } else if (source == btn6) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("6");
            check = false;

        } else if (source == btn7) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("7");
            check = false;

        } else if (source == btn8) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("8");
            check = false;

        } else if (source == btn9) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("9");
            check = false;

        } else if (source == btn0) {
            if (txtManHinh2.getText().equals("0") || check || checkMath > 0) {
                txtManHinh2.setText("");
            }
            if (checkMath > 0) {
                xuLyReset();
            }
            txtManHinh2.append("0");
            check = false;

        } else if (source == btnCong) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                if (check && (checkAdd || checkSub || checkMulti || checkDiv)) {
                    chuoi1 = txtManHinh1.getText();
                    int l = chuoi1.length();
                    if (l > 0) {
                        chuoi1 = chuoi1.substring(0, l - 1);
                        chuoi1 = chuoi1.concat("+");
                    }
                    setCheckFalse();
                } else if (checkAdd || checkSub || checkMulti || checkDiv) {

                    chuoi1 += chuoi2 + "+";
//                    check = true;                            //set trc khi vao xulySo de luu doc File 
                    xuLySo();
                } else {
                    chuoi1 += chuoi2 + "+";
                }
            } else {

                chuoi1 += "+";
//                check = true;
                xuLySo();
            }

            txtManHinh1.setText(chuoi1);
//            txtManHinh2.setText(chuoi2);
            check = true;
            num1 = read();
            checkAdd = true;

        } else if (source == btnTru) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                if (check && (checkAdd || checkSub || checkMulti || checkDiv)) {
                    chuoi1 = txtManHinh1.getText();
                    int l = chuoi1.length();
                    if (l > 0) {
                        chuoi1 = chuoi1.substring(0, l - 1);
                        chuoi1 = chuoi1.concat("-");
                    }
                    setCheckFalse();
                } else if (checkAdd || checkSub || checkMulti || checkDiv) {

                    chuoi1 += chuoi2 + "-";
                    xuLySo();
                } else {
                    chuoi1 += chuoi2 + "-";
                }
            } else {

                chuoi1 += "-";

                xuLySo();
            }

            txtManHinh1.setText(chuoi1);
//            txtManHinh2.setText(chuoi2);
            check = true;
            num1 = read();
            checkSub = true;
        } else if (source == btnNhan) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                if (check && (checkAdd || checkSub || checkMulti || checkDiv)) {
                    chuoi1 = txtManHinh1.getText();
                    int l = chuoi1.length();
                    if (l > 0) {
                        chuoi1 = chuoi1.substring(0, l - 1);
                        chuoi1 = chuoi1.concat("x");
                    }
                    setCheckFalse();
                } else if (checkAdd || checkSub || checkMulti || checkDiv) {

                    chuoi1 += chuoi2 + "x";
                    xuLySo();
                } else {
                    chuoi1 += chuoi2 + "x";
                }
            } else {
                chuoi1 += "x";
                xuLySo();
            }

            txtManHinh1.setText(chuoi1);
//            txtManHinh2.setText(chuoi2);
            check = true;
            num1 = read();
            checkMulti = true;
        } else if (source == btnChia) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                if (check && (checkAdd || checkSub || checkMulti || checkDiv)) {
                    chuoi1 = txtManHinh1.getText();
                    int l = chuoi1.length();
                    if (l > 0) {
                        chuoi1 = chuoi1.substring(0, l - 1);
                        chuoi1 = chuoi1.concat(":");
                    }
                    setCheckFalse();
                } else if (checkAdd || checkSub || checkMulti || checkDiv) {

                    chuoi1 += chuoi2 + ":";
                    xuLySo();
                } else {
                    chuoi1 = chuoi2 + ":";
                }
            } else {

                chuoi1 += ":";

                xuLySo();
            }

            txtManHinh1.setText(chuoi1);
//            txtManHinh2.setText(chuoi2);
            check = true;
            num1 = read();
            checkDiv = true;
        } else if (source == btnBang) {
            xuLyDuoiChuoiPhay();
            checkBang = true;
            xuLySo();

            chuoi1 = "";
            txtManHinh1.setText(chuoi1);
            check = true;
        } else if (source == btnC) {
            xuLyReset();
            txtManHinh2.setText("0");

        } else if (source == btnCE) {
            txtManHinh2.setText("0");
        } else if (source == btnXoa) {
            if (checkXulyLoi) {
                xuLyReset();
                txtManHinh2.setText("0");
            } else if (!check) {
                String txt = txtManHinh2.getText();
                int l = txt.length();
                if (l > 0) {
                    txt = txt.substring(0, l - 1);
                    if (txt.equals("")) {
                        txt = "0";
                    }
                    txtManHinh2.setText(txt);
                }
            }

        } else if (source == btnCanBac2) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                numMath = num1;
                if (checkAdd) {
                    num1 = num1 + Math.sqrt(read());
                } else if (checkSub) {
                    num1 = num1 - Math.sqrt(read());
                } else if (checkMulti) {
                    num1 = num1 * Math.sqrt(read());
                } else if (checkDiv) {
                    num1 = num1 / Math.sqrt(read());
                } else {
                    num1 = Math.sqrt(read());
                }
                chuoi1 += "√(" + chuoi2 + ")";
                txtManHinh1.setText(chuoi1);

                numEX = Math.sqrt(read());
                chuoi2 = numEX.toString();
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);
            } else {

                numEX = Math.sqrt(read());
                if (checkMath == 1) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("√"));
                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("√"));

                    chuoi1 = s1 + "√(" + s2 + ")";
                } else if (checkMath == 2) {
                    String s1 = chuoi1.substring(0, chuoi1.indexOf("s"));
                    String s2 = chuoi1.substring(chuoi1.indexOf("s"));

                    chuoi1 = s1 + "√(" + s2 + ")";
                } else if (checkMath == 3) {
                    String s1 = chuoi1.substring(0, chuoi1.indexOf("/") - 1);
                    String s2 = chuoi1.substring(chuoi1.indexOf("/") - 1);

                    chuoi1 = s1 + "√(" + s2 + ")";
                } else if (checkMath == 4) {
                    int i = chuoi1.lastIndexOf(read().toString());
                    String s1 = chuoi1.substring(0, i);
                    chuoi1 = s1 + "√(" + read().toString() + ")";
                    txtManHinh2.setText(numEX.toString());
                }

                txtManHinh1.setText(chuoi1);

                chuoi2 = numEX.toString();
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);

                if (checkAdd) {
                    num1 = numMath + numEX;
                } else if (checkSub) {
                    num1 = numMath - numEX;
                } else if (checkMulti) {
                    num1 = numMath * numEX;
                } else if (checkDiv) {
                    num1 = numMath / numEX;
                } else {
                    num1 = Math.sqrt(read());
                }

            }
            checkMath = 1;
            check = true;
        } else if (source == btnThemDau) {
            chuoi2 = txtManHinh2.getText();
            if (chuoi2.contains("-")) {
                chuoi2 = chuoi2.substring(1);
            } else {
                chuoi2 = "-" + chuoi2;
            }
            txtManHinh2.setText(chuoi2);
        } else if (source == btnBinhPhuong) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                numMath = num1;
                if (checkAdd) {
                    num1 = num1 + Math.pow(read(), 2);
                } else if (checkSub) {
                    num1 = num1 - Math.pow(read(), 2);
                } else if (checkMulti) {
                    num1 = num1 * Math.pow(read(), 2);
                } else if (checkDiv) {
                    num1 = num1 / Math.pow(read(), 2);
                } else {
                    num1 = Math.pow(read(), 2);
                }
                chuoi1 += "sqr(" + chuoi2 + ")";
                txtManHinh1.setText(chuoi1);

                numEX = Math.pow(read(), 2);
                chuoi2 = numEX.toString();
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);
            } else {
                numEX = Math.pow(read(), 2);
                if (checkMath == 1) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("√"));
                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("√"));

                    chuoi1 = s1 + "sqr(" + s2 + ")";
                } else if (checkMath == 2) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("s"));
                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("s"));

                    chuoi1 = s1 + "sqr(" + s2 + ")";
                } else if (checkMath == 3) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("/") - 1);
                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("/") - 1);

                    chuoi1 = s1 + "sqr(" + s2 + ")";
                } else if (checkMath == 4) {
                    int i = chuoi1.lastIndexOf(read().toString());
                    String s1 = chuoi1.substring(0, i);
                    chuoi1 = s1 + "sqr(" + read().toString() + ")";
                    txtManHinh2.setText(numEX.toString());
                }
                txtManHinh1.setText(chuoi1);

                chuoi2 = numEX.toString();
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);
                if (checkAdd) {
                    num1 = numMath + numEX;
                } else if (checkSub) {
                    num1 = numMath - numEX;
                } else if (checkMulti) {
                    num1 = numMath * numEX;
                } else if (checkDiv) {
                    num1 = numMath / numEX;
                } else {
                    num1 = Math.pow(read(), 2);
                }

            }
            checkMath = 2;
            check = true;
        } else if (source == btnNghichDao) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                numMath = num1;
                if (checkAdd) {
                    num1 = num1 + 1 / read();
                } else if (checkSub) {
                    num1 = num1 - 1 / read();
                } else if (checkMulti) {
                    num1 = num1 * (1 / read());
                } else if (checkDiv) {
                    num1 = num1 / (1 / read());
                } else {
                    num1 = Math.pow(read(), 2);
                }
                chuoi1 += "1/(" + chuoi2 + ")";
                txtManHinh1.setText(chuoi1);

                numEX = 1 / read();
                chuoi2 = numEX.toString();
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);
            } else {
                numEX = 1 / read();
                String s = "1/";
                
                if (checkMath == 1) {

//                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("√"));
//                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("√"));
//
//                    chuoi1 = s1 + "1/(" + s2 + ")";
                    xuLyThu(s);
                } else if (checkMath == 2) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("s"));
                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("s"));

                    chuoi1 = s1 + "1/(" + s2 + ")";
                } else if (checkMath == 3) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("/") - 1);
                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("/") - 1);

                    chuoi1 = s1 + "1/(" + s2 + ")";
                } else if (checkMath == 4) {
                    int i = chuoi1.lastIndexOf(read().toString());
                    String s1 = chuoi1.substring(0, i);
                    chuoi1 = s1 + "1/(" + read().toString() + ")";
                    txtManHinh2.setText(numEX.toString());
                }
                txtManHinh1.setText(chuoi1);

                chuoi2 = numEX.toString();
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);
                if (checkAdd) {
                    num1 = numMath + numEX;
                } else if (checkSub) {
                    num1 = numMath - numEX;
                } else if (checkMulti) {
                    num1 = numMath * numEX;
                } else if (checkDiv) {
                    num1 = numMath / numEX;
                } else {
                    num1 = 1 / read();
                }

            }
            checkMath = 3;
            check = true;
        } else if (source == btnPhay) {
            if (check && checkMath == 0) {
                txtManHinh2.setText("0.");
                check = false;
            } else if (check && checkMath > 0) {
                xuLyReset();
                txtManHinh2.setText("0.");
            } else {
                String s = txtManHinh2.getText();
                if (s.contains(".")) {
                    return;
                } else {
                    txtManHinh2.append(".");
                }
            }
        } else if (source == btnPhanTram) {
            xuLyDuoiChuoiPhay();
            if (checkMath == 0) {
                numMath = num1;

                if (checkAdd) {
                    numEX = num1 * (read() / 100);
                    num1 = num1 + num1 * (read() / 100);
                    chuoi1 += numEX.toString();
                } else if (checkSub) {
                    numEX = num1 * (read() / 100);
                    num1 = num1 - num1 * (read() / 100);
                    chuoi1 += numEX.toString();
                } else if (checkMulti) {
                    numEX = num1 * (read() / 100);
                    num1 = num1 * num1 * (read() / 100);
                    chuoi1 += numEX.toString();
                } else if (checkDiv) {
                    numEX = num1 * (read() / 100);
                    num1 = num1 / num1 * (read() / 100);
                    chuoi1 += numEX.toString();
                } else {

                    num1 = 0 * (read() / 100);
                    numEX = num1;
                    chuoi1 += numEX.toString();
                }

                if (chuoi1.endsWith(".0")) {
                    chuoi1 = chuoi1.substring(0, chuoi1.length() - 2);
                }

                txtManHinh1.setText(chuoi1);

                chuoi2 = numEX.toString();
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);
            } else {
                xuLyDuoiChuoiPhayKhong();
                txtManHinh2.setText(chuoi2);
                if (checkAdd) {
                    numEX = numMath * (read() / 100);
                    chuoi2 = numEX.toString();
                    num1 = numMath + numEX;

                } else if (checkSub) {
                    numEX = numMath * (read() / 100);
                    chuoi2 = numEX.toString();
                    num1 = numMath - numEX;
                } else if (checkMulti) {
                    numEX = numMath * (read() / 100);
                    chuoi2 = numEX.toString();
                    num1 = numMath * numEX;
                } else if (checkDiv) {
                    numEX = numMath * (read() / 100);
                    chuoi2 = numEX.toString();
                    num1 = numMath / numEX;
                } else {
                    num1 = read() * (read() / 100);
                    numEX = num1;
                    chuoi2 = numEX.toString();
                }
                xuLyDuoiChuoiPhayKhong();
                if (checkMath == 1) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("√"));
//                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("√"));

                    chuoi1 = s1 + chuoi2;
                } else if (checkMath == 2) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("s"));
//                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("s"));

                    chuoi1 = s1 + chuoi2;
                } else if (checkMath == 3) {
                    String s1 = chuoi1.substring(0, chuoi1.lastIndexOf("/") - 1);
//                    String s2 = chuoi1.substring(chuoi1.lastIndexOf("/") - 1);

                    chuoi1 = s1 + chuoi2;
                } else if (checkMath == 4) {
                    int i = chuoi1.lastIndexOf(read().toString());
                    String s1 = chuoi1.substring(0, i);
                    chuoi1 = s1 + chuoi2;
                    txtManHinh2.setText(chuoi2);
                }
                txtManHinh1.setText(chuoi1);

            }
            checkMath = 4;
            check = true;
        } else if (source == btnXoaHistory) {
            io.luuFile("", false);
        }

        setTrueButton();
        chuoi2 = txtManHinh2.getText();
        xuLyLoi();
        txtHistory.setText(io.docFile());
    }

    public Double read() {
        Double num;
        num = Double.parseDouble(txtManHinh2.getText());
        return num;
    }

    public void xuLySo() {
        LuuDocFileIO io = new LuuDocFileIO();
        if (checkAdd) {
            if (checkMath > 0 && check) {
                num2 = 0.0;
            } else {
                num2 = read();

            }

            result = num1 + num2;
            chuoi2 = result.toString();
//            txtManHinh2.setText(chuoi2);
//                chuoi1="";
//            txtManHinh1.setText(chuoi1);
//            checkAdd = false;
        } else if (checkSub) {
            if (checkMath > 0) {
                num2 = 0.0;
            } else {
                num2 = read();
            }
            result = num1 - num2;
            chuoi2 = result.toString();
//            txtManHinh2.setText(chuoi2);
//                chuoi1="";
//            txtManHinh1.setText(chuoi1);
//            checkSub = false;
        } else if (checkMulti) {
            if (checkMath > 0) {
                num2 = 1.0;
            } else {
                num2 = read();
            }
            result = num1 * num2;
            chuoi2 = result.toString();
//            txtManHinh2.setText(chuoi2);
//                chuoi1="";
//            txtManHinh1.setText(chuoi1);
//            checkMulti = false;
        } else if (checkDiv) {
            if (checkMath > 0) {
                num2 = 1.0;
            } else {
                num2 = read();
            }
            result = num1 / num2;
            chuoi2 = result.toString();
//            txtManHinh2.setText(chuoi2);
//                chuoi1="";
//            txtManHinh1.setText(chuoi1);
//            checkDiv = false;
        }
        xuLyDuoiChuoiPhayKhong();
        if (checkBang) {
            String luu = "";
            if (checkMath == 0) {
                luu = chuoi1 + read().toString();
                if (luu.endsWith(".0")) {
                    luu = luu.substring(0, luu.length() - 2);
                }
                luu = luu + "=" + chuoi2;
            } else {
                luu = chuoi1 + "=" + chuoi2;
            }

//            txtManHinh2.setText(chuoi2);
//            txtManHinh1.setText(chuoi1);
            io.luuFile(luu, true);
        }
        txtManHinh2.setText(chuoi2);
        txtManHinh1.setText(chuoi1);

        setCheckFalse();
        setCheckFalse2();
    }

    public void setCheckFalse() {
        checkBang = false;
        check = false;

        checkAdd = false;
        checkSub = false;
        checkMulti = false;
        checkDiv = false;

//        checkMath = false;
    }

    public void setCheckFalse2() {
        checkMath = 0;

    }

    public void setFalseButton() {
        btnPhanTram.setEnabled(false);
        btnCanBac2.setEnabled(false);
        btnBinhPhuong.setEnabled(false);
        btnNghichDao.setEnabled(false);
        btnChia.setEnabled(false);
        btnNhan.setEnabled(false);
        btnTru.setEnabled(false);
        btnCong.setEnabled(false);
        btnPhay.setEnabled(false);
        btnThemDau.setEnabled(false);
        checkXulyLoi = true;
    }

    public void setTrueButton() {

        btnPhanTram.setEnabled(true);
        btnCanBac2.setEnabled(true);
        btnBinhPhuong.setEnabled(true);
        btnNghichDao.setEnabled(true);
        btnChia.setEnabled(true);
        btnNhan.setEnabled(true);
        btnTru.setEnabled(true);
        btnCong.setEnabled(true);
        btnPhay.setEnabled(true);
        btnThemDau.setEnabled(true);
        checkXulyLoi = false;
    }

    public void xuLyLoi() {
        if (chuoi2.equalsIgnoreCase("Infinity")) {
            chuoi1 = "";
            txtManHinh1.setText(chuoi1);
            setFalseButton();
            txtManHinh2.setText("Cannot devide by zero");
        } else if (chuoi2.equalsIgnoreCase("NaN")) {
            chuoi1 = "";
            txtManHinh1.setText(chuoi1);
            txtManHinh2.setText("Invalid input");
            setFalseButton();
        }
    }

    public void xuLyReset() {
        chuoi1 = "";
        chuoi2 = "";
        txtManHinh1.setText("");

        setCheckFalse();

        setCheckFalse2();

        numMath = 0.0;
    }

    public void xuLyDuoiChuoiPhayKhong() {
        if (chuoi2.endsWith(".0")) {
            chuoi2 = chuoi2.substring(0, chuoi2.length() - 2);
        }
    }

    public void xuLyDuoiChuoiPhay() {
        if (chuoi2.endsWith(".")) {
            chuoi2 = chuoi2.substring(0, chuoi2.length() - 1);
        }
    }

    public void xuLyThu(String s) {
        String s1;
        String s2;
        if (checkAdd) {
            s1 = chuoi1.substring(0, chuoi1.lastIndexOf("+")+1);
            s2 = chuoi1.substring(chuoi1.lastIndexOf("+")+1);
        } else if (checkSub) {
            s1 = chuoi1.substring(0, chuoi1.lastIndexOf("-")+1);
            s2 = chuoi1.substring(chuoi1.lastIndexOf("-")+1);
        } else if (checkMulti) {
            s1 = chuoi1.substring(0, chuoi1.lastIndexOf("x")+1);
            s2 = chuoi1.substring(chuoi1.lastIndexOf("x")+1);
        } else if (checkDiv) {
            s1 = chuoi1.substring(0, chuoi1.lastIndexOf(":")+1);
            s2 = chuoi1.substring(chuoi1.lastIndexOf(":")+1);
        }else{
            s1="";
            s2=chuoi1;
        }
        chuoi1= s1 + s + "(" + s2 + ")";
    }
}
