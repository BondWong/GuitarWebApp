package persistence.components;

import java.util.List;

public interface CollectionReadable{
	public <T> List<T> read(String queryName, int startIndex, int pageSize, Class<T> type, Object...params);
	public <T> List<T> dynamicRead(String query, int startIndex, int pageSize, Class<T> type, Object...params);
}
