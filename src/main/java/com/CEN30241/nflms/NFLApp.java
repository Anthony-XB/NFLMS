package com.CEN30241.nflms;

import com.CEN30241.nflms.Controllers.Menu;


import javax.swing.*;


public class NFLApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.showMainMenu();
        });
    }
}
