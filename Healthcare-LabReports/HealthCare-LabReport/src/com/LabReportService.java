package com;

import model.LabReport;

import java.sql.Date;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/LabReport")
public class LabReportService {
	LabReport LabReportObj = new LabReport();

	@POST
	@Path("/ViewLabDetailsbyLabID")
	@Produces(MediaType.TEXT_HTML)
	public String  ViewLabDetailsbyLabID() {
		return LabReportObj.ViewLabDetailsbyLabID();
	}
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddLabDetails(@FormParam("type") String type, @FormParam("Description") String Description, @FormParam("date") Date date)
	{
		String output = LabReportObj.AddLabDetails(type, Description, date);
		return output;
	}
	
	
	
	@PUT 
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) public String Updatelab(String LabData) 
	
	{  
		//Convert the input string to a JSON object  
		JsonObject LabReportObj = new JsonParser().parse(LabData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String LabID = LabReportObj.get("LabID").getAsString();  
		String type = LabReportObj.get("type").getAsString();  
		String date = LabReportObj.get("date").getAsString();
		String Description = LabReportObj.get("Description").getAsString();  
		
		 
		String output = LabReportObj.Updatelab(LabID, type, date, Description); 
		System.out.println(output);
		 return output; 
		 
	}
	
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String RemoveLab(String LabData) 
	
	{  
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(LabData, "", Parser.xmlParser());     
		
		//Read the value from the element <Lab_Id>  
		String LabID = doc.select("LabID").text(); 
	
		 
		 String output = LabReportObj.RemoveLab(LabID);
		 
		 return output;
	} 

	
}