package service.transactions;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import service.transactions.daoTransactions.UpdateUserBasicInfoTransaction;

public class GetUserBasicInfoTransaction implements Transaction{
	private static Pattern p;
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		String response = (String) params.get("response");
		Map<String, String> patternMap = (Map<String, String>) params.get("patterns");
		String userID = (String) params.get("userID");
		
		p = Pattern.compile(patternMap.get("campusPattern"));
		Matcher m = p.matcher(response);
		String campus = "";
		if(m.find())
			campus = m.group(1).trim();
		
		p = Pattern.compile(patternMap.get("institutionPattern"));
		m = p.matcher(response);
		String institution = "";
		if(m.find())
			institution = m.group(1).trim();
		
		p = Pattern.compile(patternMap.get("namePattern"));
		m = p.matcher(response);
		String name = "";
		if(m.find())
			name = m.group(1).trim();
		
		p = Pattern.compile(patternMap.get("majorPattern"));
		m = p.matcher(response);
		String major = "";
		if(m.find())
			major = m.group(1).trim();
		
		p = Pattern.compile(patternMap.get("genderPattern"));
		m = p.matcher(response);
		String gender = "";
		if(m.find())
			gender = m.group(1).trim();
		
		p = Pattern.compile(patternMap.get("bdayPattern"));
		m = p.matcher(response);
		String year = "";
		String month = "";
		String date = "";
		if(m.find()){
			year = m.group(1).trim();
			month = m.group(2).trim();
			date = m.group(3).trim();
		}
		
		p = Pattern.compile(patternMap.get("telnumPattern"));
		m = p.matcher(response);
		String telnum = "";
		if(m.find())
			telnum = m.group(1).trim();
		
		p = Pattern.compile(patternMap.get("emailPattern"));
		m = p.matcher(response);
		String email = "";
		if(m.find())
			email = m.group(1).trim();
		
		params.clear();
		params.put("userID", userID);
		params.put("campus", campus);
		params.put("institution", institution);
		params.put("name", name);
		params.put("major", major);
		params.put("gender", gender);
		params.put("year", year);
		params.put("month", month);
		params.put("date", date);
		params.put("telnum", telnum);
		params.put("email", email);
		
		System.out.println(params);
		Transaction transaction = new UpdateUserBasicInfoTransaction();
		transaction.execute(params);
		
		return null;
	}

}
