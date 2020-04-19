package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/home")
public class gatewayService {

	//Read API
			@GET
			@Path("/doctor")
			@Produces(MediaType.TEXT_HTML)
			public String getDoctorDetails() throws IOException
			{
				URL obj = new URL("http://localhost:8080/HealthCare-Doctor/Doctor/doctor");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();
				System.out.println("GET Response Code :: " + responseCode);
							
				if (responseCode == HttpURLConnection.HTTP_OK) { // success
					BufferedReader in = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					// print result
					System.out.println(response.toString());
					return response.toString();
				} else {
					System.out.println("GET request not worked");
					return "GET request not worked";
				}
			}
			
			//Read API
			@GET
			@Path("/patient")
			@Produces(MediaType.TEXT_HTML)
			public String getPatientDetails() throws IOException
			{
				URL obj = new URL("http://localhost:8080/HealthCare-Patient/HealthcareService/Patients");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();
				System.out.println("GET Response Code :: " + responseCode);
							
				if (responseCode == HttpURLConnection.HTTP_OK) { // success
					BufferedReader in = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					// print result
					System.out.println(response.toString());
					return response.toString();
				} else {
					System.out.println("GET request not worked");
					return "GET request not worked";
				}
			}
			
			//Read API
			@GET
			@Path("/appointment")
			@Produces(MediaType.TEXT_HTML)
			public String getAppointmentDetails() throws IOException
			{
				URL obj = new URL("http://localhost:8080/Healthcare_Appointment/PatientService/patient");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();
				System.out.println("GET Response Code :: " + responseCode);
							
				if (responseCode == HttpURLConnection.HTTP_OK) { // success
					BufferedReader in = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					// print result
					System.out.println(response.toString());
					return response.toString();
				} else {
					System.out.println("GET request not worked");
					return "GET request not worked";
				}
			}
			
			//Read API
			@GET
			@Path("/hospital")
			@Produces(MediaType.TEXT_HTML)
			public String getHospitalDetails() throws IOException
			{
				URL obj = new URL("http://localhost:8080/HospitalManagementService/HospitalService/Hospital");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();
				System.out.println("GET Response Code :: " + responseCode);
							
				if (responseCode == HttpURLConnection.HTTP_OK) { // success
					BufferedReader in = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					// print result
					System.out.println(response.toString());
					return response.toString();
				} else {
					System.out.println("GET request not worked");
					return "GET request not worked";
				}
			}
			
			//Read API
			@GET
			@Path("/payment")
			@Produces(MediaType.TEXT_HTML)
			public String getPaymentDetails() throws IOException
			{
				URL obj = new URL("http://localhost:8081/Hospital-payments/Payments/payments");
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();
				System.out.println("GET Response Code :: " + responseCode);
							
				if (responseCode == HttpURLConnection.HTTP_OK) { // success
					BufferedReader in = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					// print result
					System.out.println(response.toString());
					return response.toString();
				} else {
					System.out.println("GET request not worked");
					return "GET request not worked";
				}
			}


}
