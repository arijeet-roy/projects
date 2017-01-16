package com.manager.db;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class SqlConn {
	
	public SqlConn() {
		// TODO Auto-generated constructor stub
	}
	
	protected Vector<Vector<Object>> searchQuery() {

		//Using PreparedStatement to prevent SQL injection attacks.
		// Data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			// Step 3: Execute a SQL SELECT query, the query result
			//  is returned in a "ResultSet" object.
			//query for returning contact details along with appointment data if any
			String searchQuery = "SELECT c.contact_id, c.fname, c.minit, c.lname, c.gender, c.bdate, cp.phoneNo, ce.emailId, ca.address, app.appointment_day_Time, app.appointment_type FROM CONTACT AS c LEFT JOIN CONTACT_PHONE AS cp ON c.contact_id = cp.contact_id LEFT JOIN CONTACT_EMAIL AS ce ON c.contact_id = ce.contact_id LEFT JOIN CONTACT_ADDRESS AS ca ON c.contact_id = ca.contact_id LEFT JOIN APPOINTMENT AS app ON c.contact_id = app.appointment_contact_id";
			conn = getDBConnection();
			stmt = conn.prepareStatement(searchQuery);
			ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	//insert or update row data 
	//accordingly update data in multiple tables like contact, contact_phone, contact_email, contact_address, appointment
	//Transactions are used for this so that rollback occurs at any step if it encounters an exception.
	protected void insertorUpdate(String ssn, String fname, String minit, String lname, String gender,
			String dob, String phoneNo, String emailId, String address, String appointmentDayTime, String appointmentType) {
		System.out.println("ssn : "+ssn+" fname : "+fname+" minit : "+minit+" lname : "+lname+" gender : "
				+gender+" dob : "+dob.toString()+" phoneNo : "+phoneNo+" address : "+address+" emailId : "
				+emailId+" appointmentdaytime : "+appointmentDayTime+" appointType : "+appointmentType);
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement phnStmt = null;
		PreparedStatement emailStmt = null;
		PreparedStatement addrStmt = null;
		PreparedStatement appointmentStmt = null;
		
		try {
			
			String insertOrUpdate = "INSERT INTO CONTACT VALUES (?, ?, ?, ?, ?, ?)"
        			+ " ON DUPLICATE KEY UPDATE fname=?, minit=?, lname=?, gender=?, bdate=?";
			conn = getDBConnection();
			conn.setAutoCommit(false);
			//set transaction
			stmt = conn.prepareStatement(insertOrUpdate);
			stmt.setString(1, ssn);
			stmt.setString(2, fname);
			stmt.setString(3, minit.isEmpty()? null : minit);
			stmt.setString(4, lname.isEmpty()? null : lname);
			stmt.setString(5, gender);
			stmt.setDate(6, java.sql.Date.valueOf(dob));
			stmt.setString(7, fname);
			stmt.setString(8, minit.isEmpty()? null : minit);
			stmt.setString(9, lname.isEmpty()? null : lname);
			stmt.setString(10, gender); 
			stmt.setDate(11, java.sql.Date.valueOf(dob));
			stmt.executeUpdate();
			
			if(!phoneNo.isEmpty()) {
				
				String insertOrUpdatePhone = "INSERT INTO CONTACT_PHONE VALUES (?, ?)"
						+ " ON DUPLICATE KEY UPDATE phoneNo=?";
				phnStmt = conn.prepareStatement(insertOrUpdatePhone);
				phnStmt.setString(1, ssn);
				BigDecimal decimal = new BigDecimal(phoneNo);
				phnStmt.setBigDecimal(2, decimal);
				phnStmt.setBigDecimal(3, decimal);
				phnStmt.executeUpdate();
			}
			
			if(!emailId.isEmpty()) {
				
				String insertOrUpdateEmail = "INSERT INTO CONTACT_EMAIL VALUES (?, ?)"
						+ " ON DUPLICATE KEY UPDATE emailId=?";
				emailStmt = conn.prepareStatement(insertOrUpdateEmail);
				emailStmt.setString(1, ssn);
				emailStmt.setString(2, emailId.isEmpty() ? null : emailId);
				emailStmt.setString(3, emailId.isEmpty() ? null : emailId);
				emailStmt.executeUpdate();
			}
			
			if(!address.isEmpty()) {
				
				String insertOrUpdateAddress = "INSERT INTO CONTACT_ADDRESS VALUES (?, ?)"
						+ " ON DUPLICATE KEY UPDATE address=?";
				addrStmt = conn.prepareStatement(insertOrUpdateAddress);
				addrStmt.setString(1, ssn);
				addrStmt.setString(2, address.isEmpty() ? null : address);
				addrStmt.setString(3, address.isEmpty() ? null : address);
				addrStmt.executeUpdate();
			}
			
			if(!appointmentDayTime.isEmpty()) {
				String insertOrUpdateAppointment = "INSERT INTO APPOINTMENT VALUES (?, ?, ?)"
						+ " ON DUPLICATE KEY UPDATE appointment_day_Time=?, appointment_type=?";
				appointmentStmt = conn.prepareStatement(insertOrUpdateAppointment);
				appointmentStmt.setString(1, ssn);
				appointmentStmt.setDate(2, appointmentDayTime.isEmpty() ?
						null : java.sql.Date.valueOf(appointmentDayTime));
				appointmentStmt.setString(3, appointmentType.isEmpty() ? null : appointmentType);
				appointmentStmt.setDate(4, appointmentDayTime.isEmpty() ?
						null : java.sql.Date.valueOf(appointmentDayTime));
				appointmentStmt.setString(5, appointmentType.isEmpty() ? null : appointmentType);
				appointmentStmt.executeUpdate();				
			}
			//finish the transaction
			conn.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(phnStmt != null) {
				try {
					phnStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(emailStmt != null) {
				try {
					emailStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(addrStmt != null) {
				try {
					addrStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(appointmentStmt != null) {
				try {
					appointmentStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//Transactions are used for this so that rollback occurs at any step if it encounters an exception.
	//delete a record.
	protected boolean deleteRecord(String contact_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement phnStmt = null;
		PreparedStatement emailStmt = null;
		PreparedStatement addrStmt = null;
		
		try {
			
			String deleteQuery = "DELETE FROM CONTACT"
        			+ " WHERE contact_id=?";
			conn = getDBConnection();
			
			//set transaction
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(deleteQuery);
			stmt.setString(1, contact_id);
			stmt.executeUpdate();
			
			/*
			 * The below code is not required as referential integrity is maintained at the database level
			 * */
//			String deletePhoneQuery = "DELETE FROM CONTACT_PHONE"
//        			+ " WHERE contact_id=?";
//			phnStmt = conn.prepareStatement(deletePhoneQuery);
//			phnStmt.setString(1, contact_id);
//			phnStmt.executeUpdate();
//			
//			String deleteEmailQuery = "DELETE FROM CONTACT_EMAIL"
//        			+ " WHERE contact_id=?";
//			emailStmt = conn.prepareStatement(deleteEmailQuery);
//			emailStmt.setString(1, contact_id);
//			emailStmt.executeUpdate();
//			
//			String deleteAddressQuery = "DELETE FROM CONTACT_ADDRESS"
//        			+ " WHERE contact_id=?";
//			addrStmt = conn.prepareStatement(deleteAddressQuery);
//			addrStmt.setString(1, contact_id);
//			addrStmt.executeUpdate();
			
			//finish the transaction
			conn.commit();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}  finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(phnStmt != null) {
				try {
					phnStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(emailStmt != null) {
				try {
					emailStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(addrStmt != null) {
				try {
					addrStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {

			// Step 1: Allocate a database "Connection" object
			dbConnection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ContactManager?useSSL=false", "root", "root"); // MySQL
			return dbConnection;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return dbConnection;

	}

}
