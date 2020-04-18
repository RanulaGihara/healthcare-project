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

import model.paypalPayments;

@Path("/paypalPayments")
public class paypalPaymentService {

	paypalPayments paymentDetails = new paypalPayments();
	
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
		public String insertItem(@FormParam("paypal_account") String paypal_account, @FormParam("amount") String amount,@FormParam("account_num") String account_num,
				@FormParam("date") String date) {
			String output = paymentDetails.insertPayment(paypal_account, amount,account_num, date);
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
			String paypal_account = itemObject.get("paypal_account").getAsString();
			String amount = itemObject.get("amount").getAsString();
			String account_num = itemObject.get("account_num").getAsString();
			String date = itemObject.get("date").getAsString();
			String output = paymentDetails.updatePayment(id, paypal_account,amount,account_num,date);
			return output;
		}

}
