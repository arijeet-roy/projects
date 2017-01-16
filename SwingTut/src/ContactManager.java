

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class ContactManager extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 755377619372040520L;
	final static int maxGap = 20;
    GridLayout experimentLayout = new GridLayout(0,4);
    private String[] fieldLabels = {"First Name", "M.I.", "Last Name", "Gender", "Phone Number", "Address", "Email ID", "D.O.B"};
    private String[] btnLabels = {"Save", "Modify", "Delete"};
    
    public ContactManager(String name) {
        super(name);
        setResizable(false);
    }
    
    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        b.setMargin(new Insets(1, 1, 1, 1));
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));
        
        JPanel fieldsPanel;
        for (int i = 0; i < 7; i++) {
			fieldsPanel = new JPanel(null);
			placeComponents(fieldsPanel,  fieldLabels[i]);
			compsToExperiment.add(fieldsPanel);
		}
        
        JPanel bDatePanel = new JPanel(null);
        JLabel userLabel = new JLabel(fieldLabels[fieldLabels.length - 1]);
        userLabel.setBounds(10,20,80,25);
        bDatePanel.add(userLabel);
        DateFormat format = new SimpleDateFormat("yyyy--MMMM--dd");
        JFormattedTextField dateTextField = new JFormattedTextField(format);
        dateTextField.setBounds(100,20,165,25);
        bDatePanel.add(dateTextField);
        
        compsToExperiment.add(bDatePanel);
        
        //Create a Results table to show the results and select to delete
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(1,0));
        
        setTableContents(resultsPanel);
        
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        JButton btn;
        for (int i=0; i<3; i++) {
            btn = new JButton();
            btn.setText(btnLabels[i]);
            buttonsPanel.add(btn);
        }
        
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(buttonsPanel, BorderLayout.CENTER);
        pane.add(resultsPanel, BorderLayout.SOUTH);
    }
    
    private void setTableContents(JPanel resultsPanel) {
		// TODO Auto-generated method stub

    	Object[][] data = new Object[100][8];
		
		final JTable table = new JTable(data, fieldLabels);
//		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(false);
		
		//Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        resultsPanel.add(scrollPane);
		
	}

	private void placeComponents(JPanel panel, String label) {


        // Creating JLabel
        JLabel userLabel = new JLabel(label);
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        ContactManager frame = new ContactManager("ContactManager");
        frame.setSize(1200,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}