package soopia.hwp.hexdump;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import soopia.hwp.hexdump.view.DSTreeNode;
import soopia.hwp.type.IDataType;

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
