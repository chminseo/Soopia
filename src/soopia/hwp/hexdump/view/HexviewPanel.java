package soopia.hwp.hexdump.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class HexviewPanel extends JPanel {
	
	private int numOfCols;
	
	private FixedColumnTextArea lineNumArea;
	private FixedColumnTextArea hexArea;
	private FixedColumnTextArea charArea;
	
	private JLabel northLabel;
	
	private Font defaultFont ;
	
	/**
	 * Create the panel.
	 */
	public HexviewPanel() {
		this(16, 12);
	}
	
	final private static String PATTERN_HEX = "0123456789ABCDEF";
	final private static String PATTERN_LABEL = "         0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F  ";
	
	public HexviewPanel(int numOfCols, int fontSize ){
		this.numOfCols = numOfCols;
		this.lineNumArea = new FixedColumnTextArea(8);
		this.hexArea = new FixedColumnTextArea(numOfCols*3);
		this.charArea = new FixedColumnTextArea(numOfCols);
		this.northLabel = new JLabel();
		defaultFont = new Font("Courier New", Font.PLAIN, fontSize);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(255, 255, 255));
		
		northLabel.setText(PATTERN_LABEL.substring(0, 
				this.lineNumArea.getColumns() + numOfCols*3));
		northLabel.setFont(new Font("Courier New", Font.PLAIN, fontSize));
		northLabel.setBorder(new UnderLineBorder(Color.BLACK));
		northLabel.setPreferredSize(new Dimension(
				this.lineNumArea.getPreferredSize().width
				+ this.hexArea.getPreferredSize().width
				+ this.charArea.getPreferredSize().width, 
				this.northLabel.getPreferredSize().height+4));
		
		JScrollPane scrollPane = new JScrollPane(initCenterPanel());
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setColumnHeaderView(northLabel);
		
		add(scrollPane);
		

		initComponent();
	}
	
	
	private JPanel initCenterPanel( ){
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		
		setBackground(new Color(255, 255, 255));
		GridBagLayout gbl = new GridBagLayout();

		panel.setLayout(gbl);
		GridBagConstraints bgc_lineNumArea = new GridBagConstraints();
		bgc_lineNumArea.weighty = 1.0;
		bgc_lineNumArea.fill = GridBagConstraints.VERTICAL;
		bgc_lineNumArea.gridx = 0;
		bgc_lineNumArea.gridy = 0;
		lineNumArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lineNumArea.setEditable(false);
		lineNumArea.setBackground(new Color(255, 255, 204));
		panel.add(lineNumArea, bgc_lineNumArea);
		hexArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		hexArea.setName("HexArea");
		hexArea.setEditable(false);
		GridBagConstraints gbc_hexArea = new GridBagConstraints();
		gbc_hexArea.insets = new Insets(0, 0, 0, 4);
		gbc_hexArea.anchor = GridBagConstraints.EAST;
		gbc_hexArea.weighty = 1.0;
		gbc_hexArea.fill = GridBagConstraints.VERTICAL;
		gbc_hexArea.gridx = 1;
		gbc_hexArea.gridy = 0;
		panel.add(hexArea, gbc_hexArea);
		charArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		charArea.setName("CharArea");
		charArea.setEditable(false);		
		charArea.setBackground(new Color(204, 255, 153));
		charArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_charArea = new GridBagConstraints();
		gbc_charArea.anchor = GridBagConstraints.EAST;
		gbc_charArea.weighty = 1.0;
		gbc_charArea.fill = GridBagConstraints.VERTICAL;
		gbc_charArea.gridx = 2;
		gbc_charArea.gridy = 0;
		panel.add(charArea, gbc_charArea);
		
		JPanel dummyPanel = new JPanel();
		dummyPanel.setPreferredSize(new Dimension());
		dummyPanel.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weighty = 1.0;
		gbc_panel_1.weightx = 1.0;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 0;
		panel.add(dummyPanel, gbc_panel_1);
		
		return panel;
	}
	
	@Override
	public Dimension getMinimumSize() {
		 return this.getPreferredSize();
	}
	
	private final void initComponent(){
		lineNumArea.setFont(defaultFont);
		hexArea.setFont(defaultFont);
		charArea.setFont(defaultFont);
	}
	
	
	class CaretHandler implements ChangeListener {
		private JTextComponent target ;
		
		public CaretHandler ( JTextComponent targetComp){
			this.target = targetComp;
		}
		@Override
		public void stateChanged(ChangeEvent e) {
			Caret caret = (Caret)e.getSource();
			
			int min = caret.getMark(), max = caret.getDot();
			if ( min == max) return ;
			if ( min > max ){
				min ^= max;
				max ^= min;
				min ^= max;
			}
			int min2 = 3 * min;
			int max2 = 3 * max;
			// 줄바꿈 문자가 포함된 경우 위치 보정 필요함.
			min2 -= (min / (HexviewPanel.this.numOfCols + NL.length())) * 2;
			max2 -= (max / (HexviewPanel.this.numOfCols + NL.length())) * 2;
			
			target.select(min2, max2);
			target.getCaret().setSelectionVisible(true);
		}
	}
	
	final private static String PATTERN_LINENUM = "00000000";
	final private static String NL = "\n"; // System.getProperty("line.separator");
	final private static String BLK = " ";
	public void print ( byte [] data, long offset ){
		printLineNumber(offset + data.length, this.numOfCols );
		printHexString(data, offset);
		
		CaretHandler ch1 = new CaretHandler(hexArea);
		charArea.getCaret().addChangeListener(ch1);
		
	}
	
	/**
	 * 주어진 범위의 텍스트를 선택한다.
	 * @param start
	 * @param end
	 */
	public void selectText(int start, int end){
		// TODO 구현해야함.
	}
	/**
	 *  주어진 범위의 hex 텍스트를 선택한다.
	 * @param start
	 * @param end
	 */
	public void selectByte(int start, int end){
		// TODO 구현해야함.
	}
	private void printHexString (byte [] data, long offset){
		StringBuffer buf = new StringBuffer();
		StringBuffer charBuf = new StringBuffer();
		int cntCol = 0;
		char ch ;
		offset %= this.numOfCols;
		for ( int i = 0 ; i < offset ; i++){
			buf.append(BLK + BLK + BLK);
			charBuf.append(BLK);
			cntCol++;
		}
		
		for( int n = 0 ; n < data.length ; n++){
			buf.append(toHexString(data[n]) + BLK);
			ch = (char)data[n];
			charBuf.append(Character.isWhitespace(ch) ? BLK : ch );
			
			if ( ++cntCol == this.numOfCols){
				buf.append(NL);
				charBuf.append(NL);
				cntCol = 0;
			}
		}
		hexArea.setText(buf.toString());
		charArea.setText(charBuf.toString());
	}
	
	private String toHexString(byte b) {
		int val = 0xff & b;
		return (val < 0x10 ? "0": "") + Integer.toHexString(val).toUpperCase(); 
	}
	
	private void printLineNumber(long numOfBytes, int col ){
		StringBuffer lineNum = new StringBuffer();
		String val ;
		int row = (int)(numOfBytes / col);
		
		for ( int i = 0 ; i <= row ; i++){
			val = Integer.toHexString(col* i).toUpperCase();
			lineNum.append(PATTERN_LINENUM.substring(val.length()));
			lineNum.append(val + NL);
		}
		
		lineNumArea.setText("");
		lineNumArea.append ( lineNum.toString() );
	}

	private String byteToChars(byte b) {
		int val = 0xff & b;
		return "" + (char)(0xff & b) ;
	}
}
