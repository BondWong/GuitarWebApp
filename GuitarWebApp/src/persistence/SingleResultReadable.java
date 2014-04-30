package persistence;

public interface SingleResultReadable {
	public <T> T read(String ID, Class<T> type);
}
