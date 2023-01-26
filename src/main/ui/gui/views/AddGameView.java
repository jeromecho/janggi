package ui.gui.views;

import persistence.Writable;
import ui.gui.JanggiGUI;
import ui.gui.components.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * class representing the add game view of the GUI
 */
public class AddGameView extends JPanel {
    private JanggiGUI gui;

    // EFFECTS: constructs the add view panel with empty game name, empty player one name,
    //          and empty player two name
    public AddGameView(JanggiGUI gui) {
        this.gui = gui;
        this.add(getTitle());
        this.add(Box.createHorizontalStrut(40));
        this.add(getForm());
    }

    // EFFECTS: constructs and returns the title
    private JLabel getTitle() {
        JLabel title = new JLabel("<html><h1><b>ADD YOUR GAME</h1></b></html>");
        return title;
    }

    // EFFECTS: constructs and returns the form
    private JPanel getForm() {
        JPanel form = new Form(this.gui);
        return form;
    }
}
