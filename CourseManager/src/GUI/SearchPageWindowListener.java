package GUI;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SearchPageWindowListener implements WindowListener {

    private JButton b;
    //Constructor
    SearchPageWindowListener(JButton b0){
        b = b0;
    }

    public void windowOpened(WindowEvent e) {
        b.setEnabled(false);
    }

    public void windowClosed(WindowEvent e) {
        b.setEnabled(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //do nothing
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //do nothing
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //do nothing
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //do nothing
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //do nothing
    }
}
