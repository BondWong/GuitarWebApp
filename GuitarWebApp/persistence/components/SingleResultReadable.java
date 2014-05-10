package components;

public interface SingleResultReadable {
	public <T> T read(String ID, Class<T> type);
	
	public <T> T read(Long ID, Class<T> type);
}
