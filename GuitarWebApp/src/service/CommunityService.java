package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Community;
import service.transactions.Transaction;
import service.transactions.daoTransactions.FetchAllCommunitiesTransaction;

@Path("/community")
public class CommunityService {
	
	@SuppressWarnings("unchecked")
	@Path("/fetchAllCommunities")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchAllCommunities() throws Exception{
		List<Community> communities = new ArrayList<Community>();
		
		Transaction transaction = new FetchAllCommunitiesTransaction();
		communities = (List<Community>) transaction.execute(null);
		
		return Response.ok(new GenericEntity<List<Community>>(communities){}).build();
	}
}
