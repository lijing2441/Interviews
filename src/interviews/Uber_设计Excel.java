package interviews;

import java.util.Map;
import java.util.HashMap;

public class Uber_设计Excel {
	private static final int MAX_CELL_INDEX=65000;
	// row, <col, string>
	private final Map<Integer, Map<Integer, String>> data = new HashMap<>();
	
	public void setValue(int row, int col, String val) {
		checkRowAndColIndex(col, col);
		Map<Integer, String> colMap = data.get(row);
		if (colMap == null) {
			colMap = new HashMap<>();
			data.put(row, colMap);
		}
		colMap.put(col, val);
	}
	
	public String getValue(int row, int col) {
		checkRowAndColIndex(col, col);
		Map<Integer, String> colMap = data.get(row);
		if (colMap != null) {
			return colMap.get(col);
		}
		return null;
	}
	
	public void checkRowAndColIndex(int row, int col) {
		if (row * col > MAX_CELL_INDEX)
			try {
				throw new Exception("cannot hold more than max_cell_index.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
