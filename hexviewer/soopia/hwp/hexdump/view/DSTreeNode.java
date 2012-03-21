package soopia.hwp.hexdump.view;

import javax.swing.tree.DefaultMutableTreeNode;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
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
