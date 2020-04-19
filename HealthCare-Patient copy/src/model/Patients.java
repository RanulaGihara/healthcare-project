package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




public class Patients { 
	
private Connection connect() {
		
		Connection con = null;
		try
		
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare_app", "root", "facebook"); 
			System.out.println("Database connected");
			}
		
		catch (Exception e) {
			
			 e.printStackTrace();
			 
			 }
		
		return con;
	}
		
	public String insertPatient(String pFname, String pLname,String pNIC, String pAddress, String pGender, String pEmail,
			int pAge, int pPhone, String pAdmitted) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query =" insert into patient(`pId`, `pFname`, `pLname`, `pNIC`, `pAddress`, `pGender`, `pEmail`, `pAge`, `pPhone`, `pAdmitted`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pFname);
			preparedStmt.setString(3, pLname);
			preparedStmt.setString(4, pNIC);
			preparedStmt.setString(5, pAddress);
			preparedStmt.setString(6, pGender);
			preparedStmt.setString(7, pEmail);
			preparedStmt.setInt(8, pAge);
			preparedStmt.setInt(9, pPhone);
			preparedStmt.setString(10, pAdmitted);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting the patients.";
			System.err.println(e.getMessage());

		}
		return output;
	}

		
	
		public String readPatients() {
		
		String output = ""; 
		try
		{
			
		   Connection con = connect(); 
		   if (con == null)
		   {return "Error while connecting to the database for reading."; }
		   
		   
		// Prepare the html table to be displayed
		   
		output = "<table border=\"1\"><tr><th>PatientId</th><th>Patient FirstName</th><th>Patient LastName</th><th>Patient NIC</th><th>Patient Address</th>"
				+ "<th>Gender</th><th>Email</th><th>Age</th><th>Phone</th><th>Admitted Date</th>"
		        + "<th>Update</th><th>Remove</th></tr>";
		
		 String query = "select * from patient"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query);
		 
		 
		// iterate through the rows in the result set
		while (rs.next()) 
		{
			
		
	    String patientId = Integer.toString(rs.getInt("pId"));     
		String patientFname = rs.getString("pFname");
		String patientLname = rs.getString("pLname");
		String patientNIC =   rs.getString("pNIC"); 
		String patientAddress = rs.getString("pAddress");
		String patientGender = rs.getString("pGender");
		String patientEmail = rs.getString("pEmail");
		String patientAge = Integer.toString(rs.getInt("pAge"));
		String patientPhone = Integer.toString(rs.getInt("pPhone"));
		String patientAdmitted = rs.getString("pAdmitted");


		

		 //Add into the html table

		output += "<tr><td>" + patientId + "</td>";
        output += "<td>" + patientFname + "</td>"; 
		output += "<td>" + patientLname + "</td>"; 
		output += "<td>" + patientNIC + "</td>"; 
		output += "<td>" + patientAddress + "</td>"; 
		output += "<td>" + patientGender + "</td>"; 
		output += "<td>" + patientEmail + "</td>"; 
		output += "<td>" + patientAge + "</td>"; 
		output += "<td>" + patientPhone+ "</td>"; 
        output += "<td>" + patientAdmitted + "</td>";
		
		
     	// buttons
		output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
		       + "<td><form method=\"post\" action=\"patient.jsp\">"
		       + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" "
		       + "class=\"btn btn-danger\">"
		       + "<input name=\"itemID\" type=\"hidden\" value=\"" + patientId + "\">" + "</form></td></tr>";
		}
		
		
		con.close();
		
		
		// Complete the html table
		   output += "</table>"; 
		}
		
		
		   catch (Exception e)
		{
			   
		      output = "Error while reading the patients."; 
		      System.err.println(e.getMessage());
		}
		
		
		return output;
		}

		    
		public String updatePatients(String pId,String pFname,String pLname,String pNIC,String pAddress, String pGender,String pEmail,String pAge,String pPhone,
	               String pAdmitted) 
		{
			
		     String output = "";
		     try
		{
		    	 
		
		          Connection con = connect(); 
		          
		          if (con == null)
		          {return "Error while connecting to the database for updating."; }
		          
		           
		// create a prepared statement
		          String query = "UPDATE patient SET pFname=?,pLname=?,pNIC=?,pAddress=?,pGender=?,pEmail=?,pAge=?,pPhone=?,pAdmitted=? WHERE pId=?";
		          
		          PreparedStatement preparedStmt = con.prepareStatement(query);
		          
		// binding values
            preparedStmt.setString(1, pFname); 
		    preparedStmt.setString(2, pLname); 
		    preparedStmt.setString(3, pNIC); 
		    preparedStmt.setString(4,pAddress); 
		    preparedStmt.setString(5, pGender); 
		    preparedStmt.setString(6, pEmail); 
			preparedStmt.setInt(7, Integer.parseInt(pAge));
			preparedStmt.setInt(8, Integer.parseInt(pPhone));
		    preparedStmt.setString(9, pAdmitted);
			preparedStmt.setInt(10, Integer.parseInt(pId));



		    
		 // execute the statement
		preparedStmt.execute(); 
		con.close();
		
		output = "Updated successfully"; 
		}
		catch (Exception e)
		     {
		        output = "Error while updating the patients.";
		        System.err.println(e.getMessage());
		} 
		
		return output;
		}
		
		
		public String deletePatients(String pId) 
		{
			String output = "";
			
			try
			{
				
			       Connection con = connect(); 
			       
			       if (con == null)
			       {return "Error while connecting to the database for deleting."; } 
			       
     		       // create a prepared statement
			         String query = "delete from patient where pId=?"; 
			         PreparedStatement preparedStmt = con.prepareStatement(query);
			         
			      // binding values
			            preparedStmt.setInt(1, Integer.parseInt(pId));
		            
   		        // execute the statement
			          preparedStmt.execute(); 
			          con.close();
			          
			          output = "Deleted successfully"; 
			}
			          
			catch (Exception e) {
				
			
			        output = "Error while deleting the patients."; 
			        System.err.println(e.getMessage());
			}
			return output;
			
		}
}

