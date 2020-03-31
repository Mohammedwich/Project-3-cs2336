//Mohammed Ahmed, msa190000



public class Node<T extends Comparable<T>> implements Comparable<Node<T>>
{
	private T heldObject;
	private Node<T> left;
	private Node<T> right;
	
	public Node()
	{ 
		heldObject = null;
		right = null;
		left = null;
	}
	
	public Node(T someObject)
	{
		this();
		heldObject = someObject;
	}
	
	public T getObject()
	{
		return heldObject;
	}
	
	public Node<T> getRight()
	{
		return right;
	}
	
	public Node<T> getLeft()
	{
		return left;
	}

	public void setObject(T someObject)
	{
		heldObject = someObject;
	}
	
	public void setRight(Node<T> aNode)
	{
		right = aNode;
	}
	
	public void setLeft(Node<T> aNode)
	{
		left = aNode;
	}

	@Override
	public int compareTo(Node<T> o)
	{
		int result;
		result = heldObject.compareTo( o.getObject() );
		
		return result;
	}
	
	@Override
	public String toString()
	{
		String result = heldObject.toString();
		return result;
	}

	@Override
	public boolean equals(Object someObject)
	{
		if(!(someObject instanceof Node) )
		{
			return false;
		}
		else
		{
			boolean result = heldObject.equals( ((Node<?>)someObject).getObject() );

			return result;
		}
	}
}
