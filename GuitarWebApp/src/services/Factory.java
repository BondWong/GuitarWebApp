package services;

import java.util.Map;

public interface Factory{
	public Object create(Map<String, Object> params);
}
