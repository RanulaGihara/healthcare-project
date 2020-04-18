package com;

import model.payments;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payments")
public class healthcareService {

	payments paymentDetails = new payments();

	// read payment
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return paymentDetails.readItems();
	}

	// inserting payment
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("payment_method") String payment_method, @FormParam("amount") String amount,
			@FormParam("date") String date, @FormParam("commission") String commission,
			@FormParam("credit_card_num") String credit_card_num) {
		String output = paymentDetails.insertPayment(payment_method, amount, date, commission, credit_card_num);
		return output;
	}

	// updating payment
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		String id = itemObject.get("id").getAsString();
		String payment_method = itemObject.get("payment_method").getAsString();
		String amount = itemObject.get("amount").getAsString();
		String date = itemObject.get("date").getAsString();
		String commission = itemObject.get("commission").getAsString();
		String credit_card_num = itemObject.get("credit_card_num").getAsString();
		String output = paymentDetails.updatePayment(id, payment_method, amount, date, commission, credit_card_num);
		return output;
	}

	// delete payment
	@DELETE
	@Path("{id}")
	public String deleteItem(@PathParam("id")String id) {
		String output = paymentDetails.deleteItem(id);
		return output;
	}

}
