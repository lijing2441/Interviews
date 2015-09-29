package interviews;

public class Snapchat_Company_Party {
	/**
	 * Professor Stewart is consulting for the president of a corporation that
	 * is planning a company party. The company has a hierarchical structure;
	 * that is, the supervisor relation forms a tree rooted at the president.
	 * The personnel office has ranked each employee with a happy rating,
	 * which is a real number. In order to make the party fun for all attendees,
	 * the president does not want both an employee and his or her immediate
	 * supervisor to attend.
	 * 
	 * Professor Stewart is given the tree that describes the structure of the
	 * corporation, using the left-child, right-sibling representation. Each
	 * node of the tree holds, in addition to the pointers, the name of an
	 * employee and that employee’s happy ranking. Describe an algorithm
	 * to make up a guest list that maximizes the sum of the happy
	 * ratings of the guests. Analyze the running time of your algorithm.
	 */
	
	/**
	 *    Find-Max-Conv(Tree t)
      		Let MC[] be an array of length n that contains max conviviality from this node down in the tree
      		for i = Node n downto 1
         		MC[i] = max(i.rating + Sum of all MC[i.grandchildren], Sum of all MC[i.children])
           		(If node i has no grandchildren or children, replace i.grandchildren and/or i.children with 0)
      		return MC[1]
	 */
	 
}
