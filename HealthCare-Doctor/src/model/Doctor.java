package model;
import java.sql.*;

public class Doctor {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare_app?useTimezone=true&serverTimezone=UTC","root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	// insert method which insert data to Doctor table
	public String insertDoctor(String name, String phoneno, String email, String gender,  
			String specialization) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			String query = " insert into doctor(`id`,`name`,`phoneno`,`email`,`gender`,`specialization`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, phoneno);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, specialization);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
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
			output = "<table border=\"1\"><tr><th>Id</th><th>Name</th><th>PhoneNo</th><th>Email</th><th>Gender</th><th>Specialization</th><th>Insert</th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String name = rs.getString("name");
				String phoneno = rs.getString("phoneno");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String specialization = rs.getString("specialization");

				// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + phoneno + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + specialization + "</td>";

				// buttons
				output += "<td><input name=\"btnInsert\" type=\"button\" value=\"Insert\" class=\"btn btn-first\"></td>"
						+"<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Delete\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + id + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update doctor table
	public String updateDoctor(String id, String name, String phoneno, String email, String gender,
			String specialization) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE doctor SET name=?,phoneno=?,email=?,gender=?,specialization=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, phoneno);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, specialization);
			preparedStmt.setInt(6, Integer.parseInt(id));

			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctor where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}

