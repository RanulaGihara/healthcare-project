package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/healthcare_app?useTimezone=true&serverTimezone=UTC", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return con;

	}

	// insert method which insert data to hospital table
	public String insertHosptal(String hName, String hAddress, String hPhone, String hEmail, String hCharge) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			String query = " insert into hospital(`hId`, `hName`,`hAddress`, `hPhone`, `hEmail`, `hCharge`)"
					+ " values (?, ?, ?, ?, ?, ?,)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hName);
			preparedStmt.setString(3, hAddress);
			preparedStmt.setString(4, hPhone);
			preparedStmt.setString(5, hEmail);
			preparedStmt.setString(6, hCharge);
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// View inserted data
	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// displaying html table
			output = "<table border=\"1\"><tr><th>Hospital name</th><th>Address</th><th>Phone Number</th><th>Email</th><th>Charge</th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from hospital";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String hId = Integer.toString(rs.getInt("hId"));
				String hName = rs.getString("hName");
				String hAddress = rs.getString("hAddress");
				String hPhone = rs.getString("hPhone");
				String hEmail = rs.getString("hEmail");
				String hCharge = rs.getString("hCharge");

				// Add into the html table
				output += "<tr><td>" + hName + "</td>";
				output += "<td>" + hAddress + "</td>";
				output += "<td>" + hPhone + "</td>";
				output += "<td>" + hEmail + "</td>";
				output += "<td>" + hCharge + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Delete\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + hId + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Hospital Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update hospital table

	public String updateHospital(String hId, String hName, String hAddress, String hPhone, String hEmail,
			String hCharge) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE hospital SET hName=?,hAddress=?,hPhone=?,hEmail=?,hCharge=? WHERE hId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, hName);
			preparedStmt.setString(2, hAddress);
			preparedStmt.setString(3, hPhone);
			preparedStmt.setString(4, hEmail);
			preparedStmt.setString(5, hCharge);
			preparedStmt.setInt(6, Integer.parseInt(hId));

			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update hospital table

	public String deleteHospital(String hId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from hospital where hId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(hId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Error while deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	

}
