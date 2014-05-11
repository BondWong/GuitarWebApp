package components;

import java.util.List;

public interface CollectionReadable{
	public <T> List<T> read(String criteria, int startIndex, int pageSize, Class<T> type, Object...params);
	public <T> List<T> read(String criteria, Class<T> type);
}
