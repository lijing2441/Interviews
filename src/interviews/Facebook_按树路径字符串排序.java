package interviews;

public class Facebook_按树路径字符串排序 {
	/**
	 * 给个树，要求把所有路径排序后，按照字符串那样的比较大小方法找出最小路径，要求时间线性
	 * 
	 * 		 5
	 * 	   /   \
	 * 	  10     3
	 * 	 /  \   /
	 *  1    7  8  
	 * 
	 * 路径有：5 10 1; 5 10 7; 5 3 8
	 * 排序后有：1 5 10, 5 7 10, 3 5 8
	 * 按字符串排序后：1 5 10 < 3 5 8 < 5 7 10
	 * 
	 */
	public static void main(String[] args) {
		
	}
	public getSmallestPath(TreeNode root) {
		list = inorder(root);
		hashmap<TreeNode, index> map;  //node --- position in list;
		int l = 0 , r = list.size() - 1;
		cur = root;
		while(l <= r){
		   int index  = map.get(cur);
		   left =  findmin(list , l , index - 1);
		   right = findmin(list , index + 1 , r);
		   cur = left > right ? root.right : root.left;
		   l = left > right ? index + 1 : l;
		   r = left > right ? r : index - 1;
		}
	}
}
