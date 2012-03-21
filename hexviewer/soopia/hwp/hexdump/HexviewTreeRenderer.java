package soopia.hwp.hexdump;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import soopia.hwp.hexdump.view.DSTreeNode;
import soopia.hwp.type.IDataType;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class HexviewTreeRenderer extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		DSTreeNode node = (DSTreeNode) value;
		
		if ( node.getUserObject() instanceof IDataType){
			IDataType ds = (IDataType) node.getUserObject();
			this.setText(ds.getStrucureName());
		}
		
		
		return this;
	}

}
