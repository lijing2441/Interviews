package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Closest_Binary_Search_Tree_Value {
	/**
	 * Given a non-empty binary search tree and a target value, find the value
	 * in the BST that is closest to the target.
	 */
	public int closestValue(TreeNode root, double target) {
		double minDiff = Double.MAX_VALUE;
		TreeNode node = root;
		int res = Integer.MAX_VALUE;
		while (node != null) {
			double curDiff = Math.abs(node.val - target);
			if (curDiff < minDiff) {
				minDiff = curDiff;
				res = node.val;
			}
			if (target == node.val) {
				return node.val;
			} else if (node.val < target) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return res;
	}
	/**
	 * Given a non-empty binary search tree and a target value, find k values in
	 * the BST that are closest to the target.
	 * 
	 * Note: Given target value is a floating point. You may assume k is always
	 * valid, that is: k ≤ total nodes. You are guaranteed to have only one
	 * unique set of k values in the BST that are closest to the target. Follow
	 * up: Assume that the BST is balanced, could you solve it in less than O(n)
	 * runtime (where n = total nodes)?
	 */
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> stack = findClosestIterative(root, target);
		TreeNode closestNode = stack.pop();
	    k--;
	    res.add(closestNode.val);
		Stack<TreeNode> smallerStack = new Stack<TreeNode>();
		Stack<TreeNode> largerStack = new Stack<TreeNode>();
		// in order to keep the order in the stack (root in the bottom)
		Stack<TreeNode> tmpStack = new Stack<TreeNode>(); 
		while(!stack.isEmpty()) {
		    tmpStack.push(stack.pop());
		}
		while(!tmpStack.isEmpty()) {
		    TreeNode popped = tmpStack.pop();
		    smallerStack.push(popped);
		    largerStack.push(popped);
		}
		TreeNode nextSmaller = getPredecessorIte(closestNode, smallerStack);
		TreeNode nextLarger = getSuccessorIte(closestNode, largerStack);
		while(k > 0) {
		    if(nextSmaller == null && nextLarger == null) return res;
		    else if(nextSmaller != null && nextLarger != null) {
		        if(Math.abs(nextSmaller.val - target) <= Math.abs(nextLarger.val - target)) {
		            res.add(nextSmaller.val);
		            k--;
		            nextSmaller = getPredecessorIte(nextSmaller, smallerStack);
		        } else {
		            res.add(nextLarger.val);
		            k--;
		            nextLarger = getSuccessorIte(nextLarger, largerStack);
		        }
		    } else if(nextSmaller != null) {
		        res.add(nextSmaller.val);
		        nextSmaller = getPredecessorIte(nextSmaller, smallerStack);
		        k--;
		    } else {
		        res.add(nextLarger.val);
		        nextLarger = getSuccessorIte(nextLarger, largerStack);
		        k--;
		    }
		}
		return res;
    }
    public Stack<TreeNode> findClosestIterative(TreeNode root, double key) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode res = root;
        double diff = Math.abs(root.val - key);
        while(root != null) {
            stack.push(root);
            if(diff > Math.abs(root.val - key)) {
                res = root;
                diff = Math.abs(root.val - key);
            }
            if(root.val == key) return stack;
            else if(root.val > key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        // in case we push too many nodes into the stack that peek() cannot get the target node
        // we need to pop out some unnecessary daughter nodes
        while(stack.peek() != res) {
            stack.pop();
        }
        return stack;
    }
    public TreeNode getPredecessorIte(TreeNode cur, Stack<TreeNode> stack) {
        if(cur.left != null) {
            TreeNode leftChild = cur.left;
            while(leftChild.right != null) {
                stack.push(leftChild);
                leftChild = leftChild.right;
            }
            return leftChild;
        } else {
            if(!stack.isEmpty()) {
                TreeNode parent = stack.pop();
                while(parent != null) {
                    if(parent.val < cur.val) break;
                    else {
                        parent = stack.isEmpty() ? null : stack.pop();
                    }
                }
                return parent;
            }
            return null;
        }
    }
    public TreeNode getSuccessorIte(TreeNode cur, Stack<TreeNode> stack) {
        if(cur.right != null) {
            TreeNode rightChild = cur.right;
            while(rightChild.left != null) {
                stack.push(rightChild);
                rightChild = rightChild.left;
            }
            return rightChild;
        } else {
            if(!stack.isEmpty()) {
                TreeNode parent = stack.pop();
                while(parent != null) {
                    if(parent.val > cur.val) break;
                    else {
                        parent = stack.isEmpty() ? null : stack.pop();
                    }
                }
                return parent;
            }
            return null;
        }
    }
    
    // 有母节点
 // Main Test Function, step 3)
    public static List<TreeNodeWithParent> findKClosestNodeInBST(TreeNodeWithParent root, int key, int k)
    {
        List<TreeNodeWithParent> list = new ArrayList<TreeNodeWithParent>();
        TreeNodeWithParent closestNode = findTreeWithKeyNearestToTheKey(root, key);
        k--;
        list.add(closestNode);
        TreeNodeWithParent nextlarger = nextLargerNodeInBST(closestNode);
        TreeNodeWithParent nextSmaller = nextSmallerNodeInBST(closestNode);
        while (k > 0)
        {
            if (nextlarger == null && nextSmaller == null)
				try {
					throw new Exception();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if (nextlarger != null && nextSmaller != null)
            {
                if (Math.abs(nextlarger.val - key) >= Math.abs(nextSmaller.val - key))
                {
                    list.add(nextSmaller);
                    k--;
                    nextSmaller = nextSmallerNodeInBST(nextSmaller);
                }
                else
                {
                    list.add(nextlarger);
                    k--;
                    nextlarger = nextLargerNodeInBST(nextlarger);
                }
            }
            else if (nextlarger != null)
            {
                list.add(nextlarger);
                k--;
                nextlarger = nextLargerNodeInBST(nextlarger);
            }
            else
            {
                list.add(nextSmaller);
                k--;
                nextSmaller = nextSmallerNodeInBST(nextSmaller);
            }
        }
        return list;
    }
     
    //find out the node that is closed to the key, step 1)
    public static TreeNodeWithParent findTreeWithKeyNearestToTheKey(TreeNodeWithParent root, int key)
    {
    	TreeNodeWithParent desiredRoot = root;
        int diff = Math.abs(root.val - key);
        while (root != null)
        {
            if (diff > Math.abs(root.val - key))
            {
                diff = Math.abs(root.val - key);
                desiredRoot = root;
            }
            if (root.val > key)
                root = root.left;
            else if (root.val < key)
                root = root.right;
            else
                return root;
            }
        return desiredRoot;
    }
     
    //step 2) find its next larger node in BST
    public static TreeNodeWithParent nextLargerNodeInBST(TreeNodeWithParent current)
    {
        if (current.right != null)
        {
        	TreeNodeWithParent nextTree = current.right;
            while (nextTree.left != null)
                nextTree = nextTree.left;
            return nextTree;
        }
        else
       {
            while (current.parent!=null)
            {
                if (current != current.parent.right)
                    return current.parent;
                else
                {
                    while(current.parent != null && current==current.parent.right)
                        current = current.parent;
                    return current.parent;
                }
            }
            return null;
        }
    }
     
    //step 2) find its next smaller node in BST
    public static TreeNodeWithParent nextSmallerNodeInBST(TreeNodeWithParent current)
    {
        if (current.left != null)
        {
        	TreeNodeWithParent nextTree = current.left;
            while (nextTree.right != null)
                nextTree = nextTree.right;
            return nextTree;
        }
        else
        {
            while (current.parent != null)
            {
                if (current == current.parent.right)
                    return current.parent;
                else
               {
                    while (current.parent != null && current == current.parent.left)
                        current = current.parent;
                    return current.parent;
                }
            }
            return null;
        }
    }
}
class TreeNodeWithParent {
	int val;
	TreeNodeWithParent left;
	TreeNodeWithParent right;
	TreeNodeWithParent parent;
	public TreeNodeWithParent(int val) {
		this.val = val;
	}
}