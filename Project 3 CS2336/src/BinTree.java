//Mohammed Ahmed, msa190000

public class BinTree<T extends Comparable<T>>
{
	private Node<T> root;
	
	public BinTree()
	{
		root = null;
	}
	
	public BinTree(Node<T> aNode)
	{
		this();
		root = aNode;
	}
	
	public void insert(Node<T> theNode)
	{
		
	}
	
	public Node<T> search(Node<T> keywordHolderNode, Node<T> rootToBeginAt)
	{
		Node<T> currentNode = rootToBeginAt;
		Node<T> result = null;
		
		if(currentNode == null)
		{
			return null;
		}
		
		if (keywordHolderNode.compareTo(currentNode) == 0) //if node found
		{ 
			result = currentNode;
			return result; // The desired node was found
		}
		else if (keywordHolderNode.compareTo(currentNode) < 0) //if node is to the left of currentNode, create new tree from subtree and search it
		{
			search(keywordHolderNode, currentNode.getLeft());
		}
		else if (keywordHolderNode.compareTo(currentNode) > 0) //if node is to the right of currentNode, create new tree from subtree and search it
		{
			search(keywordHolderNode, currentNode.getRight());
		}
		
		
		return result;
	}
	
	public void delete(Node<T> theNode)
	{
		
	}
	
	public void sort(boolean byAscending)
	{
		
	}
	
	@Override
	public String toString()
	{
		
	}
	
}
