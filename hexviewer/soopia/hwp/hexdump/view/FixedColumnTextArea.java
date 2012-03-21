package soopia.hwp.hexdump.view;

import java.awt.Dimension;
import javax.swing.JTextArea;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class FixedColumnTextArea extends JTextArea {
	
	public FixedColumnTextArea(int col){
		super(0, col);
	}
	
	@Override
	public Dimension getMaximumSize(){
		return this.getPreferredSize();
	}

//	@Override
//	public Dimension getMinimumSize() {
//		return this.getPreferredSize();
//	}
}
