package com.mycompany.framework_manager;

import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.lang.Integer;
import java.util.Observable;
import java.awt.event.ActionListener;

/**
 * The view. Listens to model for changes.
 *
 * @author Martin
 */
class View implements java.util.Observer {

    private TextField myTextField;
    private Button readButton, sortButton, addButton, removeButton;

    View() {
        System.out.println("View()");

        Frame frame = new Frame("Framework Manager");
        frame.add("North", new Label("Frameworks"));

        myTextField = new TextField();
        frame.add("Center", myTextField);

        Panel panel = new Panel();

        readButton = new Button("Read");
        addButton = new Button("Add");
        sortButton = new Button("Sort");
        removeButton = new Button("Remove");

        panel.add(readButton);
        panel.add(sortButton);
        panel.add(addButton);
        panel.add(removeButton);

        frame.add("South", panel);

        frame.addWindowListener(new CloseListener());
        frame.setSize(800, 600);
        frame.setLocation(100, 100);
        frame.setVisible(true);

    }

    /**
     * Receive updated data.
     *
     * @param obs
     * @param obj
     */
    public void update(Observable obs, Object obj) {
//        myTextField.setText("" + ((Integer) obj).intValue());	//obj is an Object, need to cast to an Integer
        myTextField.setText("" + (String) obj);

    }

    /**
     * Objects the controller will react on/listen to.
     *
     * @param controller
     */
    public void addController(ActionListener controller) {
        readButton.addActionListener(controller);
        sortButton.addActionListener(controller);
        addButton.addActionListener(controller);
        removeButton.addActionListener(controller);
    }

    public static class CloseListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
