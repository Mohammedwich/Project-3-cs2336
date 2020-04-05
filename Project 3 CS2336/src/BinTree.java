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
	
	public Node<T> getRoot()
	{
		return root;
	}
	
	public void setRoot(Node<T> theNode)
	{
		root = theNode;
	}
	
	public void setNode(Node<T> inputNode, Node<T> nodeBeingChanged)
	{
		nodeBeingChanged = inputNode;
	}
	
	// insert anode then return it.
	public void insert(Node<T> theNode, Node<T> rootOfTree)
	{
		//Node<T> currentNode = rootOfTree;
		
		if(rootOfTree == null)
		{
			root = theNode;
		}
		else if(theNode.compareTo(rootOfTree) < 0)
		{
			if(rootOfTree.getLeft() == null)
			{
				rootOfTree.setLeft(theNode);
			}
			else
			{
				insert(theNode, rootOfTree.getLeft());
			}			
		}
		else
		{
			if(rootOfTree.getRight() == null)
			{
				rootOfTree.setRight(theNode);
			}
			else
			{
				insert(theNode, rootOfTree.getRight());
			}	
		}
		
		//return currentNode;
	}
	
	
	public Node<T> findNode(Node<T> keywordHolderNode, Node<T> rootToBeginAt)
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
			//return result; // The desired node was found
		}
		else if (keywordHolderNode.compareTo(currentNode) < 0) 
		{
			result = findNode(keywordHolderNode, currentNode.getLeft()); //search the left subtree if what we want is less than currentNode
		}
		else if (keywordHolderNode.compareTo(currentNode) > 0) 
		{
			result = findNode(keywordHolderNode, currentNode.getRight()); //search the right subtree if what we want is greater than currentNode
		}
		
		
		return result;
	}
	
	
	//This search requires an empty arrayList so it can fill it with partial matches
	public void search(Node<T> theNode, Node<T> rootOfTree, ArrayList<Node<T>> theList)
	{		
		if (rootOfTree == null)
		{
			return;
		}                  
		
		search(theNode, rootOfTree.getLeft(), theList); 
		
		if(rootOfTree.equals(theNode) == true)
		{
			theList.add(rootOfTree);
		}
		
		search(theNode, rootOfTree.getRight(), theList); 
	}
	
	
	public void edit(Node<T> nodeToEdit, Node<T> newNode)
	{
		Node<T> currentNode = root;
		
		while(currentNode != null)
		{
			if(nodeToEdit.compareTo(currentNode) == 0)
			{
				currentNode.setObject(newNode.getObject());
				return;
			}
			else if(nodeToEdit.compareTo(currentNode) < 0)
			{
				currentNode = currentNode.getLeft();
			}
			else
			{
				currentNode = currentNode.getRight();
			}
		}
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
	
	public void writeSorted(Node<T> currentRoot, boolean byAscending, FileWriter writer) throws IOException
	{	
		Node<T> currentNode = currentRoot;
		
		if(byAscending == true)
		{
			if (currentNode == null)
			{
				return;
			}                  
			
			writeSorted(currentNode.getLeft(), byAscending, writer); 
			writer.append(currentNode.toString() + "\n");   
			System.out.print(currentNode.toString() + "\n");
			writeSorted(currentNode.getRight(), byAscending, writer); 
		}
		
		if(byAscending == false)
		{
			if (currentNode == null)
			{
				return;
			}                  
			
			writeSorted(currentNode.getRight(), byAscending, writer); 
			writer.append(currentNode.toString() + "\n");                   
			writeSorted(currentNode.getLeft(), byAscending, writer); 
		}

	}
	
	public void getSortedAsList(Node<T> currentRoot, boolean byAscending, ArrayList<Node<T>> theList)
	{
		Node<T> currentNode = currentRoot;
		
		if(byAscending == true)
		{
			if (currentNode == null)
			{
				return;
			}                  
			
			getSortedAsList(currentNode.getLeft(), byAscending, theList); 
			theList.add(currentNode);   
			getSortedAsList(currentNode.getRight(), byAscending, theList); 
		}
		
		if(byAscending == false)
		{
			if (currentNode == null)
			{
				return;
			}                  
			
			getSortedAsList(currentNode.getRight(), byAscending, theList); 
			theList.add(currentNode);                   
			getSortedAsList(currentNode.getLeft(), byAscending, theList); 
		}
	}
	
	// Helper function: Recursively appends each node in the tree to the builder provided by toString()
	private void adder(Node<T> currentRoot, StringBuilder builder)
	{		
		Node<T> currentNode = currentRoot;
		
		if (currentNode == null)
		{
			return;
		} 
		
		adder(currentNode.getLeft(), builder); 
		builder.append(currentNode.toString() + "\n");                   
		adder(currentNode.getRight(), builder); 
	}
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		
		adder(root, result);
		
		return result.toString();
	}
	
	
	
}
