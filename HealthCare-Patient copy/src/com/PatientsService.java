package com;

import model.Patients;
import com.google.gson.*;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.nodes.Document;

@Path("/Patients")

public class PatientsService {
	Patients patiObj = new Patients();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readPatients() {
		return patiObj.readPatients();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("pFname") String pFname, @FormParam("pLname") String pLname,
			@FormParam("pNIC") String pNIC, @FormParam("pAddress") String pAddress,
			@FormParam("pGender") String pGender, @FormParam("pEmail") String pEmail, @FormParam("pAge") int pAge,
			@FormParam("pPhone") int pPhone, @FormParam("pAdmitted") String pAdmitted) {
		String output = patiObj.insertPatient(pFname, pLname, pNIC, pAddress, pGender, pEmail, pAge, pPhone, pAdmitted);

		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatients(String patientData) {

		// Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();

		// Read the values from the JSON object
		String pId = patientObject.get("pId").getAsString();
		String pFname = patientObject.get("pFname").getAsString();
		String pLname = patientObject.get("pLname").getAsString();
		String pNIC = patientObject.get("pNIC").getAsString();
		String pAddress = patientObject.get("pAddress").getAsString();
		String pGender = patientObject.get("pGender").getAsString();
		String pEmail = patientObject.get("pEmail").getAsString();
		String pAge = patientObject.get("pAge").getAsString();
		String pPhone = patientObject.get("pPhone").getAsString();
		String pAdmitted = patientObject.get("pAdmitted").getAsString();

		String output = patiObj.updatePatients(pId, pFname, pLname, pNIC, pAddress, pGender, pEmail, pAge, pPhone,
				pAdmitted);
		return output;
	}
	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatients(String PatientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(PatientData, "", Parser.xmlParser());

		// Read the value from the element <docId>
		String pId = doc.select("pId").text();
		
		String output = patiObj.deletePatients(pId);
		return output;
	}
}

