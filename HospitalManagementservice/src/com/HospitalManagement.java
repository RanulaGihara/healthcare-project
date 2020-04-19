package com;


import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospital")
public class HospitalManagement {
	
	Hospital hospitalDetails = new Hospital();

	// read hospitalDetails
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return hospitalDetails.readItems();
	}

	// inserting hospitalDetails
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("hName") String hName, @FormParam("hAddress") String hAddress,
			@FormParam("hPhone") String hPhone, @FormParam("hEmail") String hEmail,@FormParam("hCharge") String hCharge
			)
	{
		String output = hospitalDetails.insertHosptal(hName, hAddress, hPhone, hEmail, hCharge);
		return output;
	}

	// updating Hospital
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateDoctor(String itemData) {
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
			String hId = itemObject.get("hId").getAsString();
			String hName = itemObject.get("hName").getAsString();
			String hAddress = itemObject.get("hAddress").getAsString();
			String hPhone = itemObject.get("hPhone").getAsString();
			String hEmail = itemObject.get("hEmail").getAsString();
			String hCharge = itemObject.get("hCharge").getAsString();
			String output = hospitalDetails.updateHospital(hId, hName, hAddress, hPhone, hEmail, hCharge);
			return output;
		}
	
	
		// delete hospital

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteHospital(String hostData)
		{
		//Convert the input string to an XML document
		 org.jsoup.nodes.Document doc = Jsoup.parse(hostData, "", Parser.xmlParser());

		//Read the value from the element <hostpitalID>
		 String hId = ((org.jsoup.nodes.Element) doc).select("hId").text();
		 String output = hospitalDetails.deleteHospital(hId);
		return output;
		}
		



}