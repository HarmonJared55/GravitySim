package com.mycompany;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener{
    
    JButton randomPlanet;
    GamePanel gp;

    public Window(){
        gp = new GamePanel(1280,720);
        this.setTitle("Gravity Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gp);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout( new BorderLayout());
        randomPlanet = new JButton("add random");
        this.add(randomPlanet,BorderLayout.SOUTH);
        randomPlanet.addActionListener(this);
        this.setSize(1280, 800);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gp.addBody();
    }

}
