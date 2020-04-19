package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LabReport {
	//A common method to connect to the DB 
	private Connection connect() {
		Connection con = null;

		try

		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare_app", "root", "");
			System.out.println("Database Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// Add Laboratory Details
	
	public String AddLabDetails(String type ,String Description, Date date ) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into reports (`LabID`,`type`,`date`,`Description`)" + " values (?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, type);
			preparedStmt.setDate(3, date);
			preparedStmt.setString(4, Description);
			

			preparedStmt.execute();
			con.close();

			output = "Inserted Lab Details successfully";
		} catch (Exception e) {
			output = "Error while inserting the Lab details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	// View Lab Details by Lab ID
	
	public String ViewLabDetailsbyLabID() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border=\"1\"><tr><th>LabID</th><th>type</th><th>date</th><th>Description</th></tr>";

			String query = "select * from reports";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			
			while (rs.next()) {
				String LabID =Integer.toString(rs.getInt("labID"));
				String type = rs.getString("type");
				Date date = rs.getDate("date");
				String Description = rs.getString("Description");
				
			
				
				// add to html table
				output += "<tr><td>" + LabID + "</td>";
				output += "<td>" + type + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + Description + "</td>";
				
			
				

				// buttons
				output += "</tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Lab details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	
	// update lab report

	public String Updatelab(String LabID, String type, String date, String Description ) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE report SET type=?,date=?, Description=?, WHERE LabId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1,type);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, Description);
			preparedStmt.setInt(4, Integer.parseInt(LabID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated Lab Details successfully";
		} catch (Exception e) {
			output = "Error while updating the Lab details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	// Delete Laboratory details
	
	public String RemoveLab(String LabID)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for removing."; } 
	 
			String query = "delete from report where labID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values   
			preparedStmt.setInt(1, Integer.parseInt(LabID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			output = "Deleted successfully";   
			}   
			catch (Exception e)   
			{    
				output = "Error while deleting the Lab details.";    
				System.err.println(e.getMessage());   } 
	 
	  return output;  }
	
	

}
