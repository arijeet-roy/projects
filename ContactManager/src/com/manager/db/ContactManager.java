package com.manager.db;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

/*
 * Input format : 
 * First name : upto 20 characters
 * Middle initials : 1 character
 * Last name : 20 chars
 * Gender : 1 char
 * Date of Birth & Appointment Date : YYYY-MM-DD 
 * phone number : 11 chars 
 * Appointment type : Phone or Meeting*/

public class ContactManager extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 755377619372040520L;
	final static int maxGap = 20;
    final static String[] fieldLabels = {"SSN:", "First Name:", "Middle Initial:", "Last Name:", "Gender:", "Date of Birth:",
    		"Phone No:", "Email ID:", "Address:", "Appt Date:", "Appt Type:"};
    final static JTextField[] textFields = new JTextField[fieldLabels.length];
    final static String[] btnLabels = {"SEARCH", "SAVE/UPDATE", "DELETE"};
    //static connection for database
    static SqlConn sqlConn;
    
    //layout for showing the input teext labels
    GridLayout experimentLayout = new GridLayout(0,3);
    
    public ContactManager(String name) {
        super(name);
        setResizable(false);
        sqlConn = new SqlConn();
    }
    
    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 4.5)+maxGap,
                (int)(buttonSize.getHeight() * 5.5)+maxGap * 2));
        
        //Set the first panel row of textfields.
        JPanel fieldsPanel;
        for (int i = 0; i < fieldLabels.length; i++) {
			fieldsPanel = new JPanel(null);
			placeComponents(fieldsPanel,  fieldLabels[i], i);
			compsToExperiment.add(fieldsPanel);
		}
        setCharacterLimitValidation();
        //Set the second panel row of buttons.
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        JButton btn;
        ButtonClick buttonClick = new ButtonClick();
        
        //Set text for buttons and click action listeners for SAVE, MODIFY/UPDATE and DELETE records.
        for (int i=0; i<btnLabels.length; i++) {
        	btn = new JButton();
        	btn.setText(btnLabels[i]);
        	btn.setActionCommand(btnLabels[i]);
        	btn.addActionListener(buttonClick);
        	buttonsPanel.add(btn);
        }
        
        //Create a Results table to show the results and select to delete
        //Set the second panel row
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(1,0));
        
        //initialize data to used for display in the jTable
    	Vector<Vector<String>> data = new Vector<>();
    	
    	//set table contents using model
        setTableContents(resultsPanel, data);
        
        
        
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(buttonsPanel, BorderLayout.CENTER);
        pane.add(resultsPanel, BorderLayout.SOUTH);
    }
    
    private void setCharacterLimitValidation() {
		// TODO Auto-generated method stub
    	//Limit Middle initials to 1 character
		textFields[2].setDocument(new JTextFieldLimit(1));
		//limit gender to 1 character
		textFields[4].setDocument(new JTextFieldLimit(1));
		//limit phone number to 11 digits
		textFields[6].setDocument(new JTextFieldLimit(11));
	}

	private void addDateFieldWithValidation(JPanel panel, int index) {
		// TODO Auto-generated method stub
        JLabel userLabel = new JLabel(fieldLabels[index]);
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        //date format used for taking birth date and appointment date as input.
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        textFields[index] = new JFormattedTextField(format);
        textFields[index].setBounds(100,20,165,25);
        panel.add(textFields[index]);
        
	}

	private void setTableContents(JPanel resultsPanel, Vector<Vector<String>> data) {
		// TODO Auto-generated method stub
		Vector<String> columnNames = new Vector<String>(Arrays.asList(fieldLabels));
		JTable table = new JTable(new DefaultTableModel(data, columnNames));
//		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(false);
		
		//Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        
        resultsPanel.add(scrollPane);
        
        setTable(table);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
		
	}
	
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        
        // get the model from the jtable
       DefaultTableModel model = (DefaultTableModel)table.getModel();

        // get the selected row index
       int selectedRowIndex = table.getSelectedRow();
       
        // set the selected row data into jtextfields
       try {
    	   textFields[0].setText(model.getValueAt(selectedRowIndex, 0).toString());
       } catch(Exception e) {
    	   textFields[0].setText("");
       }
       try {
    	   textFields[1].setText(model.getValueAt(selectedRowIndex, 1).toString());
       } catch(Exception e) {
    	   textFields[1].setText("");
       }
       try {
    	   textFields[2].setText(model.getValueAt(selectedRowIndex, 2).toString());
       } catch(Exception e) {
    	   textFields[2].setText("");
       }
       try {
    	   textFields[3].setText(model.getValueAt(selectedRowIndex, 3).toString());
       } catch(Exception e) {
    	   textFields[3].setText("");
       }
       try {
    	   textFields[4].setText(model.getValueAt(selectedRowIndex, 4).toString());
       } catch(Exception e) {
    	   textFields[4].setText("");
       }
       try {
    	   textFields[5].setText(model.getValueAt(selectedRowIndex, 5).toString());
       } catch(Exception e) {
    	   textFields[5].setText("");
       }
       try {
    	   textFields[6].setText(model.getValueAt(selectedRowIndex, 6).toString());
       } catch(Exception e) {
    	   textFields[6].setText("");
       }
       try {
    	   textFields[7].setText(model.getValueAt(selectedRowIndex, 7).toString());
       } catch(Exception e) {
    	   textFields[7].setText("");
       }
       try {
    	   textFields[8].setText(model.getValueAt(selectedRowIndex, 8).toString());
       } catch(Exception e) {
    	   textFields[8].setText("");
       }
       try {
    	   textFields[9].setText(model.getValueAt(selectedRowIndex, 9).toString());
       } catch(Exception e) {
    	   textFields[9].setText("");
       }
       try {
    	   textFields[10].setText(model.getValueAt(selectedRowIndex, 10).toString());
       } catch(Exception e) {
    	   textFields[10].setText("");
       }
        
    }  

	private static JTable table = null;
	private static void setTable(JTable table) {
		// TODO Auto-generated method stub
		ContactManager.table = table;
	}

	protected static JTable getTable() {
		return table;
	}
	
	private void placeComponents(JPanel panel, String label, int index) {

		if(label.equalsIgnoreCase(fieldLabels[5]) || label.equalsIgnoreCase(fieldLabels[9])) {
			addDateFieldWithValidation(panel, index);
		}
		else {
			// Creating JLabel
			JLabel userLabel = new JLabel(label);
			userLabel.setBounds(10,20,80,25);
			panel.add(userLabel);
			
			/* Creating text field where user is supposed to
			 * enter data.
			 */
			textFields[index] = new JTextField(20);				
			
			textFields[index].setBounds(100,20,165,25);
			panel.add(textFields[index]);
			
		}
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        ContactManager frame = new ContactManager("Contact Manager");
        frame.setSize(1250,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
    	//initialize the database
    	
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