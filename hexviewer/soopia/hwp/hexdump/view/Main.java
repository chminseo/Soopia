package soopia.hwp.hexdump.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class Main extends JFrame{
	private static Dimension dim = null;
	private HexviewPanel hv = new HexviewPanel(16, 14);
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dim = hv.getPreferredSize();
		
		getContentPane().add(hv, BorderLayout.CENTER);
		
//		hv.print(data, 0);
	}

	public static void main(String[] args) {
		Main frame = new Main();
		frame.setSize(dim);
		frame.pack();
		
		frame.setVisible(true);
	}
}
