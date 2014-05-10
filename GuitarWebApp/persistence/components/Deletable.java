package components;

public interface Deletable {
	public <T> void delete(String ID, Class<T> type);
}
