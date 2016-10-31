package systems.crigges.gui.desktop;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BowTableModel implements TableModel{
	private Class<?>[] colClasses;
	private String[] colNames;
	private ArrayList<String> names;
	private ArrayList<String> classes;
	private ArrayList<Integer> rings;
	private ArrayList<Integer> placements;
	private ArrayList<?>[] data;
	private TableModelListener listener;
	
	
	public BowTableModel() {
		colClasses = new Class<?>[4];	
		colClasses[0] = String.class;
		colClasses[1] = String.class;
		colClasses[2] = Integer.class;
		colClasses[3] = Integer.class;
		colNames = new String[4];	
		colNames[0] = "Name";
		colNames[1] = "Klasse";
		colNames[2] = "Ringe";
		colNames[3] = "Platzierung";
		
		names = new ArrayList<String>();
		classes = new ArrayList<String>();
		rings = new ArrayList<Integer>();
		placements = new ArrayList<Integer>();
		data = new ArrayList[4];
		data[0] = names;
		data[1] = classes;
		data[2] = rings;
		data[3] = placements;
	}
	
	public void addEntry(String name, Object obj, int ringCount){
		//find class
		String cls = (String) obj;
		int pos = -1;
		for(String c : classes){
			if(c.equals(cls)){
				pos = classes.indexOf(c);
			}
		}
		int placement = 1;
		if(pos == -1){
			pos = classes.size();
		}else{
			int i = pos;
			int placementFix = 1;
			while(i < classes.size() && classes.get(i).equals(cls)){
				if(rings.get(pos) >= ringCount){
					placement++;
					pos++;
				}else{
					placements.set(i, placementFix + 1);
				}
				placementFix++;
				i++;
			}
		}
		writeEntry(name, cls, ringCount, placement, pos);
		listener.tableChanged(new TableModelEvent(this));
	}
	
	private void writeEntry(String name, String cs, int ringCount, int placement, int pos){
		names.add(pos, name);
		classes.add(pos, cs);
		rings.add(pos, ringCount);
		placements.add(pos, placement);
	}

	@Override
	public void addTableModelListener(TableModelListener listen) {
		listener = listen;
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return colClasses[col];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	@Override
	public int getRowCount() {
		return names.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[col].get(row);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 0;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		if(arg2 == 0){
			names.set(arg1, (String) arg0);
		}
	}
	
	
	
	

}
