package soopia.hwp.hexdump.view;

import javax.swing.tree.DefaultMutableTreeNode;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class DSTreeNode extends DefaultMutableTreeNode {
	final public static int TYPE_ROOT = 0;
	final public static int TYPE_TOP_LEVEL = 2 ; // "STROAGE TOP LEVEL";
	final public static int TYPE_STREAM = 4 ; // "STROAGE TAGE ID";
	public static final int TYPE_RECORD = 8;
	
	private int nodeType ;
	
	public DSTreeNode(Object userObject, boolean allowsChildren, int type) {
		super(userObject, allowsChildren);
		this.nodeType = type;
	}
	
}
