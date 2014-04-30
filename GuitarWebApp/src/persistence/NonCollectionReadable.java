package persistence;

import java.util.List;

public class NonCollectionReadable implements CollectionReadable{

	@Override
	public <T> List<T> read(String criteria, int startIndex, int pageSize, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
