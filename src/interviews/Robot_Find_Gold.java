package interviews;

public class Robot_Find_Gold {
	public int[] x = {-1, 0, 1, 1, 1, 0, -1, -1, -1};
	public int[] y = {-1, -1, -1, 0, 1, 1, 1, 0, -1};
	
	public int[] isGold(boolean[][] matrix, int x, int y){
		return isGold(matrix, y, x, new boolean[matrix.length][matrix[0].length]);
	}
	public int[] isGold(boolean[][] matrix, int row, int col, boolean[][] used){
		if(matrix[row][col] == true){
			int[] res = new int[2];
			res[0] = row;
			res[1] = col;
		}else{
			used[row][col] = true;
			//random shuffle to find a new direction
			for(int i = 0; i < 8; i++){
				//find the new point position
				int newRow = row + y[i];
				if(newRow < 0) newRow += matrix[0].length;
				if(newRow >= matrix[0].length) newRow -= matrix[0].length;
				
				int newCol = col + x[i];
				if(newCol < 0) newCol += matrix.length;
				if(newCol >= matrix.length) newCol -= matrix[0].length;
				
				if(!used[newRow][newCol]){
					return isGold(matrix, newRow, newCol, used);
				}
			}
		}
		return null;
	}
}
