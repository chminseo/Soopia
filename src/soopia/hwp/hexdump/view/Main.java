package soopia.hwp.hexdump.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
