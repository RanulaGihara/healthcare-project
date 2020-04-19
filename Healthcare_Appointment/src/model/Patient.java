package model;

import java.sql.*;

public class Patient 
{//Connecting DB
	private Connection connect() 
	{
		Connection con = null;
		
		try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthcare_app", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
	}
	
	public String insertPatient(String name, String address, String gender, String doctor,String phone,String visit, String date, String email, String comments)
	{
		 String output = "";
		 
		 try
		 {
			 
			 Connection con = connect();
			 
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting.";
			 }
		 // create a prepared statement
		 String query =  "INSERT INTO `appointment`(`appointmentID`, `Name`, `Address`, `Gender`, `Doctor`, `Phone`, `Visit`, `Date`, `Email`, `Comments`)" + "VALUES (?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, gender);
		 preparedStmt.setString(5, doctor);
		 preparedStmt.setString(6, phone);
		 preparedStmt.setString(7, visit);
		 preparedStmt.setString(8, date);
		 preparedStmt.setString(9, email);
		 preparedStmt.setString(10, comments);
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the patient.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
	
	public String readPatients()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th> Appointment ID</th><th> Name</th><th>Address</th><th> Gender</th><th> Doctor</th><th> Phone</th><th> Alredy visited </th><th> Date</th><th> Email</th><th> Comments</th></tr>";
	 String query = "select * from appointment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String appointmentID = Integer.toString(rs.getInt("appointmentID"));
	 String Name = rs.getString("Name");
	 String Address = rs.getString("Address");
	 String Gender = rs.getString("Gender");
	 String Doctor = rs.getString("Doctor");
	 String Phone = rs.getString("Phone");
	 String Visit = rs.getString("Visit");
	 String Date = rs.getString("Date");
	 String Email = rs.getString("Email");
	 String Comments = rs.getString("Comments");
	 
	 // Add into the html table
	 output += "<tr><td>" + appointmentID + "</td>";
	 output += "<td>" + Name + "</td>";
	 output += "<td>" + Address + "</td>";
	 output += "<td>" + Gender + "</td>";
	 output += "<td>" + Doctor + "</td>";
	 output += "<td>" + Phone + "</td>";
	 output += "<td>" + Visit + "</td>";
	 output += "<td>" + Date + "</td>";
	 output += "<td>" + Email + "</td>";
	 output += "<td>" + Comments + "</td>";
	 
	 
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the patients details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
		 

	public String updatePatient(String appointmentID, String name, String address, String gender, String doctor, String phone, String visit, String date, String email, String comments)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE appointment SET Name=?,Address=?,Gender=?,Doctor=?,Phone=?,Visit=?,Date=?,Email=?,Comments=? WHERE appointmentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, name);
	 preparedStmt.setString(2, address);
	 preparedStmt.setString(3, gender);
	 preparedStmt.setString(4, doctor);
	 preparedStmt.setString(5, phone);
	 preparedStmt.setString(6, visit);
	 preparedStmt.setString(7, date);
	 preparedStmt.setString(8, email);
	 preparedStmt.setString(9, comments);
	 preparedStmt.setInt(10, Integer.parseInt(appointmentID));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the patients details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	//delete appointment
	public String deletePatient(String appointmentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from appointment where appointmentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(appointmentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the patient.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 
	

