package com.daxue.applet;

import javax.swing.*;

public class AppletTest extends JApplet {
    public static void main(String[] args) {
        System.out.println("dada");
    }

    public void init() {
        JLabel label = new JLabel("Hello, applet!", SwingConstants.CENTER);
        add(label);
    }
}
