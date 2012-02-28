package soopia.hwp.hexdump.view;

import javax.swing.tree.DefaultMutableTreeNode;

public class DataStructureTreeNode extends DefaultMutableTreeNode {
	final public static int TYPE_ROOT = 0;
	final public static int TYPE_TOP_LEVEL = 2 ; // "STROAGE TOP LEVEL";
	final public static int TYPE_TAG_ID = 4 ; // "STROAGE TAGE ID";
	
	private int nodeType ;
	
	public DataStructureTreeNode(Object userObject, boolean allowsChildren, int type) {
		super(userObject, allowsChildren);
		this.nodeType = type;
	}
	
}
