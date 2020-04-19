package com;

import model.Patient;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 


@Path("/patient")

public class PatientService 
{
	Patient pObj = new Patient();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readPatient() {
		return pObj.readPatients();
	}
	
	

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertPatient(
 @FormParam("Name") String Name,
 @FormParam("Address") String Address,
 @FormParam("Gender") String Gender,
 @FormParam("Doctor") String Doctor,
 @FormParam("Phone") String Phone,
 @FormParam("Visit") String Visit,
 @FormParam("Date") String Date,
 @FormParam("Email") String Email,
 @FormParam("Comments") String Comments
 )
{
 String output = pObj.insertPatient(Name, Address, Gender, Doctor, Phone, Visit, Date, Email,Comments);
return output;
}

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatePatient(String patientData)
{
 JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
 
 String appointmentID = patientObject.get("appointmentID").getAsString();
 String Name = patientObject.get("Name").getAsString();
 String Address = patientObject.get("Address").getAsString();
 String Gender = patientObject.get("Gender").getAsString();
 String Doctor = patientObject.get("Doctor").getAsString();
 String Phone = patientObject.get("Phone").getAsString(); 
 String Visit = patientObject.get("Visit").getAsString();
 String Date = patientObject.get("Date").getAsString();
 String Email = patientObject.get("Email").getAsString();
 String Comments = patientObject.get("Comments").getAsString();
 
 String output = pObj.updatePatient(appointmentID, Name, Address, Gender, Doctor, Phone, Visit, Date, Email, Comments);
 return output;
} 

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String patientData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String appointmentID = doc.select("appointmentID").text();
 String output = pObj.deletePatient(appointmentID);
return output;
}


}
