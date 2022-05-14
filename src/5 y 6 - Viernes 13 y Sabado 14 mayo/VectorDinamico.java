
public class VectorDinamico
{
	private int[] vec;
	public VectorDinamico(int N)
	{
		if( N < 1 )
		{
			System.out.println("no se pueden alocar longuitudes menores que 1");
			System.exit( -1 );
		}
		//llego aca porque todo esta bien..
		vec = new int[N];
	}
	public void setItem(int pos, int val)
	{
		if( (pos >= 0) && (pos < vec.length) )
			vec[pos] = val;
		else
		{
			System.out.println("VectorDinamico.setItem() error: acceso invalido");
			System.exit( -1 );
		}
	}
	public int getItem( int pos )
	{
		if( (pos < 0) || (pos >= vec.length) )
		{
			System.out.println("VectorDinamico.getItem() error: acceso invalido");
			System.exit( -1 );
		}
		return vec[pos];
	}
	public int length()
	{
		return vec.length;
	}
	public void show()
	{
		for(int i=0 ; i<vec.length ; i++ )
			System.out.print(vec[i]+" ");
		System.out.print("\n");	// equivale a: System.out.println("");
	}
	public void sort(boolean asc)
	{
		//minimun scope (visivilidad minima)
		for( int j=vec.length-1 ; j>0 ; j-- )
		{
			for( int i=0 ; i<j ; i++ )
			{
				if( asc )
				{
					if( vec[i] > vec[i+1] )
					{
						int aux1 = vec[j];
						vec[j] = vec[i];
						vec[i] = aux1;
					}
				}
				else
				{
					if( vec[i] < vec[i+1] )
					{
						int aux1 = vec[j];
						vec[j] = vec[i];
						vec[i] = aux1;
					}
				}
			}
			show();
		}
	}
	public void sortVector(boolean asc)
	{
		for(int i = 0; i < vec.length- 1; i++)
		{
			for(int j = i + 1; j < vec.length; j++)
			{
				boolean cond = (vec[i] > vec[j]);
				if( asc == false )
					cond = !cond;
				if( cond )
				{
					int aux1;
					aux1 = vec[j];
					vec[j] = vec[i];
					vec[i] = aux1;
				}
			}
		}
	}
	public void sortVector()
	{
		sortVector(true);
	}
	public void resize( int newSize )
	{
		//0: cheque argumentos
		if( newSize < 1 )
		{
			System.out.println("VectorDinamico.resize() error: newSize debe ser > 0");
			System.exit( -1 );
		}
		if( newSize == vec.length )
			return;
		//1: crear el nuevo vector
		int[] temp = new int[newSize];
		//2: copio los datos de vec a temp
		int N = vec.length;
		if( N > newSize )
			N = newSize;
		//ahora tengo en N la minima longitud
		for( int i=0 ; i<N ; i++ )
			temp[i] = vec[i];
		//3: asigno vec al nuevo espacio de mem redimencionado
		vec = temp;	//ahora el espacio original quedo sin referencia
	}
	public void removeItem_( int removePos )
	{
		//0: chequear argumentos
	}
	public void remove(int item)
	{
		int [] newvector = new int [(vec.length - 1)];
		for (int i = 0; i< vec.length; i++)
		{ 
			if (i !=item )
			{ 
				newvector [i] = vec[i];
			}
		}
		vec = newvector; 
	}
	public void removeItem(int pos)
	{
//		if(!(pos >= 0 && pos <= vec.length))
		if( (pos < 0 || pos > vec.length))
		{ 
			System.out.println("La posición es invalida"); 
			System.exit(-1); 
		}
		int[] newVec = new int[vec.length-1];
		int x = 0;
		for(int i = 0; i < newVec.length; i++)
		{
			if(i == pos)
				x = 1;
			newVec[i] = vec[i + x];
		}
		vec = newVec;
	}
	public void addItem(int pos, int val)
	{ 
		if(!(pos >= 0 && pos < vec.length)) 
		{ 
			System.out.println("La posición es invalida"); 
			System.exit(-1); 
		}
		int[] newVec = new int[vec.length+1];
		int x = 0;
		for(int i = 0; i < newVec.length; i++) 
		{
			if(i == pos) 
			{
				x = 1; 
				newVec[i] = val; 
			}
			else
			{ 
				newVec[i] = vec[i - x];
			}
		}
		vec = newVec;
	}
}
