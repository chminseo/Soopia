package soopia.hwp.hexdump;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import soopia.hwp.hexdump.view.DataStructureTreeNode;
import soopia.hwp.structure.IDataStructure;

public class HexviewTreeRenderer extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		DataStructureTreeNode node = (DataStructureTreeNode) value;
		
		if ( node.getUserObject() instanceof IDataStructure){
			IDataStructure ds = (IDataStructure) node.getUserObject();
			this.setText(ds.getStrucureName());
		}
		
		
		return this;
	}

}
