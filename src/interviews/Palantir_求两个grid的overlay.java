package interviews;

public class Palantir_求两个grid的overlay {
	/**
	 * 题目是求两个grid的overlay，二维grid内部用黑白二色涂色，然后黑+黑=黑，白+黑=黑，白+白=白，求两个grid重叠之后的结果
	 * grid是用四叉树而不是矩阵来表示的，如果某一个区域全部都是同一个颜色，则该节点就变成叶子节点，因此这个树每个分支的深度不一定一样
	 * 
	 * Node: color; child[] 如果这片区域都是同一个颜色则child为空数组,否则color就是undefined
	 */
	public 
}
