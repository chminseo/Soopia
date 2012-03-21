package soopia.hwp.hexdump.view;

import java.awt.Dimension;
import javax.swing.JTextArea;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
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
