package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class paypalPayments {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare_app", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	// insert method which insert data to payments table
	public String insertPayment(String paypal_account, String amount, String account_num, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			String query = " insert into payments_paypal(`id`,`paypal_account`,`amount`,`account_num`,`date`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, paypal_account);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, account_num);
			preparedStmt.setString(5, date);

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
			output = "<table border=\"1\"><tr><th>paypal account</th><th>amount</th><th>account number</th><th>date</th></tr>";
			String query = "select * from payments_paypal";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String paypal_account = rs.getString("paypal_account");
				String amount = Double.toString(rs.getDouble("amount"));
				String account_num = rs.getString("account_num");
				String date = rs.getString("date");

				// Add into the html table
				output += "<tr><td>" + paypal_account + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + account_num + "</td>";
				output += "<td>" + date + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Deletex\"class=\"btn btn-danger\">"
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

	// update payments table
	public String updatePayment(String id, String paypal_account, String amount, String account_num, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE payments_paypal SET paypal_account=?,amount=?,account_num=?,date=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, paypal_account);
			preparedStmt.setDouble(2, Double.parseDouble(amount));
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, account_num);
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

}
