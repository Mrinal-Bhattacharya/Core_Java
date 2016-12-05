package com.ds.tree;

import junit.framework.Assert;

import org.junit.Test;

public class BinaryTreeTest {

	@Test	
	public void testCreatingRootNode(){
		BinaryTree tree=new BinaryTree();
		tree.insert(4);
		Assert.assertEquals(4,tree.getRoot().data);
	}
	
	@Test	
	public void testCreatingLeftNodeFromRoot(){
		BinaryTree tree=new BinaryTree();
		tree.insert(4);
		tree.insert(2);
		
		Assert.assertEquals(2,tree.getRoot().left.data);
	}
	
	@Test	
	public void testCreatingRightNodeInsideLeftNodeFromRoot(){
		BinaryTree tree=new BinaryTree();
		tree.insert(4);
		tree.insert(2);
		tree.insert(3);
		Assert.assertEquals(3,tree.getRoot().left.right.data);
	}
}
