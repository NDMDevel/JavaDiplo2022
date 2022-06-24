
public class SubClass implements Recordable,Other
{
	@Override
	public String saveToString()
	{
		return "msg";
	}
	@Override
	public void loadFromString(String backup)
	{
		
	}
	@Override
	public int someMethod(boolean b)
	{
		return 3;
	}
}
