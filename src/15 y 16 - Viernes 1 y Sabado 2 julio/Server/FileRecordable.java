import java.io.IOException;

public interface FileRecordable
{
	public void saveToFile  (String path);
	public void loadFromFile(String path);
}
