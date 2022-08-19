package jMainWIndow;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel
{
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		System.out.println(String.format("setValueAt(%s,%d,%d)",
										 aValue.toString(),
										 rowIndex,
										 columnIndex));
		//dataCache[rowIndex][columnIndex] = aValue.toString();
	}
	//me quedo con la implementacion por defecto que hace AbstractTableModel
	//@Override
	//public void removeTableModelListener(TableModelListener l)
	//{
	//	//????
	//}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		if( columnIndex == 0 )
			return false;
		return true;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		// TODO Auto-generated method stub
		//return dataCache[rowIndex][columnIndex];
		return rowIndex + " " + columnIndex;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 6;
	}
	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		if( columnIndex == 0 )
			return "Id";
		if( columnIndex == 1 )
			return "Temp";
		if( columnIndex == 2 )
			return "Hum";
		if( columnIndex == 3 )
			return "Presion";
		return "";
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
//		if( columnIndex == 0 )
//			return Integer.class;
		return String.class;
	}
	//dejo la implementacion por defecto
	//@Override
	//public void addTableModelListener(TableModelListener l)
	//{
	//	//????
	//}
}
