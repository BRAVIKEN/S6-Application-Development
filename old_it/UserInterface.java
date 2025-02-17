import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener {
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;
    private MenuBar menuBar;
    private Menu menu;
    private MenuItem menuItemQuit;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine (an object
     * processing and executing the game commands) is needed.
     * 
     * @param gameEngine The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine) {
        engine = gameEngine;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     *
     * @param text The text to print
     */
    public void print(String text) {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     *
     * @param text The text to print before the line break.
     */
    public void println(String text) {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     *
     * @param imageName The path to the image.
     */
    public void showImage(String imageName) {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if (imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     *
     * @param on The boolean that decide if we activate or deactivate the input field.
     */
    public void enable(boolean on) {
        entryField.setEditable(on);
        if (!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI() {
        myFrame = new JFrame("Zork");
        entryField = new JTextField(34);

        menuBar = new MenuBar();
        menu = new Menu("Game");

        menuItemQuit = new MenuItem("Quit");
        menuItemQuit.addActionListener(this);
        menu.add(menuItemQuit);
        menuBar.add(menu);

        myFrame.setMenuBar(menuBar);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100, 100));

        JPanel panel = new JPanel();
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);

        myFrame.getContentPane().add(panel, BorderLayout.CENTER);

        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        entryField.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }

    /**
     * Actionlistener interface for entry textfield.
     *
     * @param e The action event
     */
    public void actionPerformed(ActionEvent e) {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        if (e.getSource() == menuItemQuit) {
            processCommand("quit");
        } else {
            processCommand(entryField.getText());
        }
    }

    /**
     * A command has been entered. Read the command and do whatever is necessary to
     * process it.
     *
     * @param input The string containing the command to interpret.
     */
    private void processCommand(String input) {
        // boolean finished = false;
        // String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
}
