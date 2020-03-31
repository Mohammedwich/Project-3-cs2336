//Mohammed Ahmed, msa190000

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


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
	
	// insert anode then return it.
	public Node<T> insert(Node<T> theNode, Node<T> rootOfTree)
	{
		Node<T> currentNode = rootOfTree;
		
		if(currentNode == null)
		{
			//create a new node with the same object in case the node is one from another tree so we don't null its left/right
			currentNode = new Node<T>(theNode.getObject()); 
			currentNode.setLeft(null);
			currentNode.setRight(null);
		}
		else if(theNode.compareTo(currentNode) < 0)
		{
			insert(theNode, currentNode.getLeft());
		}
		else
		{
			insert(theNode, currentNode.getRight()); // if greater than or equal, insert to the right
		}
		
		return currentNode;
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
		else if (keywordHolderNode.compareTo(currentNode) < 0) 
		{
			search(keywordHolderNode, currentNode.getLeft()); //search the left subtree if what we want is less than currentNode
		}
		else if (keywordHolderNode.compareTo(currentNode) > 0) 
		{
			search(keywordHolderNode, currentNode.getRight()); //search the right subtree if what we want is greater than currentNode
		}
		
		
		return result;
	}
	
	
	//TODO: implement this, check if it is good
	//This search requires an empty arrayList so it can fill it with partial matches
	public void search2(Node<T> theNode, Node<T> rootOfTree, ArrayList<Node<T>> theList)
	{		
		if (rootOfTree == null)
		{
			return;
		}                  
		
		search2(theNode, rootOfTree.getLeft(), theList); 
		
		if(rootOfTree.equals(theNode))
		{
			theList.add(theNode);
		}
		
		search2(theNode, rootOfTree.getRight(), theList); 
	}
	
	
	public void edit(Node<T> nodeToEdit, Node<T> newNode)
	{
		//TODO: implement this
	}
	
	//Helper function: feed this function the right child of the node that is being deleted
	private Node<T> getSuccessor(Node<T> rightChildOfDeletedNode) 
	{
		// keep recursing until we have the left-most node of the right branch of the node we are deleting and return that
        if(rightChildOfDeletedNode.getLeft() != null) 
        {
            return getSuccessor(rightChildOfDeletedNode.getLeft());
        }
        else
        {
        	return rightChildOfDeletedNode;
        }
    }
	
	//TODO: remove the System prints when deleting
	public Node<T> delete(Node<T> theNode, Node<T> root)
	{
		if(root == null)
		{
			return null;
		}

		if(theNode.compareTo(root) < 0) 
		{
            root.setLeft(delete(theNode, root.getLeft()));
        } 
		else if(theNode.compareTo(root) > 0) 
		{
            root.setRight(delete(theNode, root.getRight()));
        } 
		else //if we found the node to delete
		{
            // node with no children
            if(root.getLeft() == null && root.getRight() == null) 
            {
                //System.out.println("deleting "+ theNode.toString());
                root = null;
                return root;
            } 
            //if node has only a right child
            else if(root.getLeft() == null) 
            {
               // System.out.println("deleting "+ theNode.toString());
                root = root.getRight();
                return root;
            } 
            //if node has only a left child
            else if(root.getRight() == null) 
            {
            	//System.out.println("deleting "+ theNode.toString());
                root = root.getLeft();
                return root;
            } 
            else   // if node with two children
            {
                // search for successor node
                Node<T> theSuccessor = getSuccessor(root.getRight());
                //put its object in the node we are trying to delete
                root.setObject(theSuccessor.getObject());
                //Call delete() on the successor since we moved it into the current node. 
                //Its right child will be moved into its place as programmed above.
                root.setRight(delete(theSuccessor, root.getRight()));
               // System.out.println("deleting "+theNode.toString());
            }
        }
 
        return root;

	}
	
	public void writeSorted(Node<T> root, boolean byAscending, FileWriter writer) throws IOException
	{	
		if(byAscending == true)
		{
			if (root == null)
			{
				return;
			}                  
			
			writeSorted(root.getLeft(), byAscending, writer); 
			writer.append(root.toString());                   
			writeSorted(root.getRight(), byAscending, writer); 
		}
		
		if(byAscending == false)
		{
			if (root == null)
			{
				return;
			}                  
			
			writeSorted(root.getRight(), byAscending, writer); 
			writer.append(root.toString() + "\n");                   
			writeSorted(root.getLeft(), byAscending, writer); 
		}

	}
	
	// Helper function: Recursively appends each node in the tree to the builder provided by toString()
	private void adder(Node<T> root, StringBuilder builder)
	{		
		if (root == null)
		{
			return;
		} 
		
		adder(root.getRight(), builder); 
		builder.append(root.toString() + "\n");                   
		adder(root.getLeft(), builder); 
	}
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		
		adder(root, result);
		
		return result.toString();
	}
	
	
	
}
