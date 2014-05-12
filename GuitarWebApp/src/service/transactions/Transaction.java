package service.transactions;

import java.util.Map;

public interface Transaction {
	public Object execute(Map<String, Object> params) throws Exception;
}
