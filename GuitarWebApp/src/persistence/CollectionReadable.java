package persistence;

import java.util.List;

public interface CollectionReadable{
	public <T> List<T> read(String criteria, int startIndex, int pageSize, Class<T> type);
}
