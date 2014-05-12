package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import model.Community;
import service.transactions.DAOTransaction;
import service.transactions.GetCommunitiesTransaction;

@Path("/community")
public class CommunityService {
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/getCommunities")
	public Response getCommunities(){
		DAOTransaction transaction = new GetCommunitiesTransaction();
		List<Community> communities = new ArrayList<Community>();
		try {
			communities = (List<Community>) transaction.execute(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok(new GenericEntity<List<Community>>(communities){}).build();
	}
}
