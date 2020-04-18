package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.onlinePayments;
import model.payments;

@Path("/onlinePayments")
public class healthcareOnlineService {

	onlinePayments paymentDetails = new onlinePayments();

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
			@FormParam("date") String date, @FormParam("commission") String commission) {
		String output = paymentDetails.insertPaymentOnline(payment_method, amount, date, commission);
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
		String output = paymentDetails.updatePayment(id, payment_method, amount, date, commission);
		return output;
	}

}
