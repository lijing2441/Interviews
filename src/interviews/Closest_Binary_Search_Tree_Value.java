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
		Stack<TreeNode> tmpStack = new Stack<TreeNode>(); // in order to keep the order in the stack (root in the bottom)
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
    public static List<Tree> findKClosestNodeInBST(Tree root, int key, int k)
    {
        List<Tree> list = new List<Tree>();
        Tree closestNode = findTreeWithKeyNearestToTheKey(root, key);
        k--;
        list.Add(closestNode);
        Tree nextlarger = nextLargerNodeInBST(closestNode);
        Tree nextSmaller = nextSmallerNodeInBST(closestNode);
        while (k > 0)
        {
            if (nextlarger == null && nextSmaller == null)
                throw new StackOverflowException();
            else if (nextlarger != null && nextSmaller != null)
            {
                if (Math.Abs(nextlarger.node - key) >= Math.Abs(nextSmaller.node - key))
                {
                    list.Add(nextSmaller);
                    k--;
                    nextSmaller = nextSmallerNodeInBST(nextSmaller);
                }
                else
                {
                    list.Add(nextlarger);
                    k--;
                    nextlarger = nextLargerNodeInBST(nextlarger);
                }
            }
            else if (nextlarger != null)
            {
                list.Add(nextlarger);
                k--;
                nextlarger = nextLargerNodeInBST(nextlarger);
            }
            else
            {
                list.Add(nextSmaller);
                k--;
                nextSmaller = nextSmallerNodeInBST(nextSmaller);
            }
        }
        return list;
    }
     
    //find out the node that is closed to the key, step 1)
    public static Tree findTreeWithKeyNearestToTheKey(Tree root, int key)
    {
        Tree desiredRoot = root;
        int diff = Math.Abs(root.node - key);
        while (root != null)
        {
            if (diff > Math.Abs(root.node - key))
            {
                diff = Math.Abs(root.node - key);
                desiredRoot = root;
            }
            if (root.node > key)
                root = root.leftChild;
            else if (root.node < key)
                root = root.rightChild;
            else
                return root;
            }
        return desiredRoot;
    }
     
    //step 2) find its next larger node in BST
    public static Tree nextLargerNodeInBST(Tree current)
    {
        if (current.rightChild != null)
        {
            Tree nextTree = current.rightChild;
            while (nextTree.leftChild != null)
                nextTree = nextTree.leftChild;
            return nextTree;
        }
        else
       {
            while (current.parentTree!=null)
            {
                if (current != current.parentTree.rightChild)
                    return current.parentTree;
                else
                {
                    while(current.parentTree!=null&&current==current.parentTree.rightChild)
                        current = current.parentTree;
                    return current.parentTree;
                }
            }
            return null;
        }
    }
     
    //step 2) find its next smaller node in BST
    public static Tree nextSmallerNodeInBST(Tree current)
    {
        if (current.leftChild != null)
        {
            Tree nextTree = current.leftChild;
            while (nextTree.rightChild != null)
                nextTree = nextTree.rightChild;
            return nextTree;
        }
        else
        {
            while (current.parentTree != null)
            {
                if (current == current.parentTree.rightChild)
                    return current.parentTree;
                else
               {
                    while (current.parentTree != null && current == current.parentTree.leftChild)
                        current = current.parentTree;
                    return current.parentTree;
                }
            }
            return null;
        }
    }
}
