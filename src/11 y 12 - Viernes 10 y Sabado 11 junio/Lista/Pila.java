
public class Pila extends List
{
	public Pila()
	{
//		super();
		System.out.println("Pila() ctor");
	}
	public void push(int data)
	{
		append(data);
		super.show();
//		show();
	}
	public int pop()
	{
		int ret = getData(getSize()-1);
		removeData(getSize()-1);
		return ret;
	}
	@Override
	public void show()
	{
		System.out.println("show() de Pila()");
	}
}
