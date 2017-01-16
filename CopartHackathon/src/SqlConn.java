import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SqlConn {

	public SqlConn() {
		// TODO Auto-generated constructor stub
	}

	protected String searchForCustomerState(String customerId) {

		Connection conn = null;
		PreparedStatement stmt = null;
		String result = "";
		
		try {
			
			// Step 3: Execute a SQL SELECT query, the query result
			//  is returned in a "ResultSet" object.
			//query for returning contact details along with appointment data if any
			String searchQuery = "SELECT * FROM CustomerData WHERE customer_id = " +customerId;
			conn = getDBConnection();
			stmt = conn.prepareStatement(searchQuery);
			ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                result = rs.getString("customer_address");
                result += "_" + rs.getString("customer_state");
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
		return result;
	}
	
	protected ArrayList<String> searchForCities(String state) {

		Connection conn = null;
		PreparedStatement stmt = null;
		List<String> cities = new ArrayList<>();
		
		try {
			
			// Step 3: Execute a SQL SELECT query, the query result
			//  is returned in a "ResultSet" object.
			//query for returning contact details along with appointment data if any
			String searchQuery = "SELECT * FROM CopartLocations WHERE state = '" +state+ "'";
			conn = getDBConnection();
			stmt = conn.prepareStatement(searchQuery);
			ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                cities.add(rs.getString("city"));
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
		return (ArrayList<String>) cities;
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
					"jdbc:mysql://localhost:3306/CopartFacilities?useSSL=false", "root", "root"); // MySQL
			return dbConnection;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return dbConnection;

	}
}
