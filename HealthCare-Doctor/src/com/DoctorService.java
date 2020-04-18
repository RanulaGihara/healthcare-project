package com;



import javax.lang.model.element.Element;

//import javax.websocket.server.PathParam;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



import model.Doctor;

@Path("/doctor")
public class DoctorService {
	Doctor doctorDetails = new Doctor();

	// read doctor
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return doctorDetails.readItems();
	}

	// inserting doctor
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("name") String name, @FormParam("phoneno") String phoneno,
			@FormParam("email") String email, @FormParam("gender") String gender,
			@FormParam("specialization") String specialization) {
		String output = doctorDetails.insertDoctor(name, phoneno, email, gender, specialization);
		return output;
	}

	// updating doctor
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String itemData) {
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		String id = itemObject.get("id").getAsString();
		String name = itemObject.get("name").getAsString();
		String phoneno = itemObject.get("phoneno").getAsString();
		String email = itemObject.get("email").getAsString();
		String gender = itemObject.get("gender").getAsString();
		String specialization = itemObject.get("specialization").getAsString();
		String output = doctorDetails.updateDoctor(id, name, phoneno, email, gender, specialization);
		return output;
	}

	// delete doctor
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String docData)
	{
	//Convert the input string to an XML document
	 org.jsoup.nodes.Document doc = Jsoup.parse(docData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String docId = ((org.jsoup.nodes.Element) doc).select("docId").text();
	 String output = doctorDetails.deleteDoctor(docId);
	return output;
	}
}
