package com.CEN30241.nflms;

import com.CEN30241.nflms.Controllers.Menu;


import javax.swing.*;

/**
 * The NFLApp class serves as the entry point for the NFL Management System application.
 * It initializes and displays the main menu of the application.
 *
 * @author Anthony
 */
public class NFLApp {

    /**
     * The main method launches the application.
     * It creates and displays the main menu using a Swing GUI.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.showMainMenu();
        });
    }
}
