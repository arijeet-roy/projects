package com.manager.db;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JOptionPane.showMessageDialog;

public class ButtonClick implements ActionListener{

	private final String search = ContactManager.btnLabels[0];
	private final String save = ContactManager.btnLabels[1];
	private final String delete = ContactManager.btnLabels[2];
	private final SqlConn sqlConn = ContactManager.sqlConn;
	
	public ButtonClick() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                loadData();
                return null;
            }

			private void loadData() {
				// TODO Auto-generated method stub
                // TODO Auto-generated method stub
                String action = e.getActionCommand();
                //search query
                if (action.equalsIgnoreCase(search)) {
                	searchResults();
                } 
                //save record or update if exists
                else if(action.equalsIgnoreCase(save)){
                	String ssn = ContactManager.textFields[0].getText();
                	String fname = ContactManager.textFields[1].getText();
                	String minit = ContactManager.textFields[2].getText();
                	String lname = ContactManager.textFields[3].getText();
                	String gender = ContactManager.textFields[4].getText();
                	String dob = ContactManager.textFields[5].getText();
                	String phoneNo = ContactManager.textFields[6].getText();
                	String emailId = ContactManager.textFields[7].getText();
                	String address = ContactManager.textFields[8].getText();
                	String appointmentDayTime = ContactManager.textFields[9].getText();
                	String appointmentType = ContactManager.textFields[10].getText();
                	//perform the necessary validations
                	if(ssn.isEmpty() || fname.isEmpty() || gender.isEmpty() || phoneNo.isEmpty()) {
                		showMessageDialog(null, "SSN, First Name, Gender and Phone No are mandatory fields.");
                		return;
                	}
                	EmailValidator emailValidator = new EmailValidator();
                	if(!emailId.isEmpty()) {
                		if(!emailValidator.validate(emailId.trim())) {
                			showMessageDialog(null, "Invalid Email ID.");
                			return;
                		}                		
                	}
                	
                	   if(!phoneNo.isEmpty()) {
                		   if(!isNumeric(phoneNo)) {
                			   showMessageDialog(null, "Invalid Phone number.");
                			   return;
                		   }                		   
                	   }
                	sqlConn.insertorUpdate(ssn, fname, minit, lname, gender, dob, phoneNo, 
                			emailId, address, appointmentDayTime, appointmentType);
                	searchResults();
                	showMessageDialog(null, "Row inserted.");
                } 
                //delete row.
                else if(action.equalsIgnoreCase(delete)) {
                	String ssn = ContactManager.textFields[0].getText();
                	if(ssn.isEmpty()) {
                		showMessageDialog(null, "No records selected.");
                		return;
                	}
                	sqlConn.deleteRecord(ssn);
                	searchResults(); 
                	showMessageDialog(null, "Row deleted.");
                }
                

                //upon any action reset the value in text fields.
            	for(int i=0; i<ContactManager.textFields.length; i++) {
            		ContactManager.textFields[i].setText(null);
            	}

			}

			private void searchResults() {
				// TODO Auto-generated method stub
				//search for results and arrange the tuples in the table rows accordingly.
				Vector<Vector<Object>> data = sqlConn.searchQuery();
            	JTable jTable = ContactManager.getTable();
            	Vector<String> columnNames = new Vector<String>(Arrays.asList(ContactManager.fieldLabels));
            	jTable.setModel(new DefaultTableModel(data, columnNames));
			}
        }.execute();
        
	}
	
	public static boolean isNumeric(String str)
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}

}
