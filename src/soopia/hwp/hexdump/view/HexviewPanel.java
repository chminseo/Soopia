package soopia.hwp.hexdump.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Point;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
//		charArea.setEditable(false);
		
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
	
	final private static String PATTERN_LINENUM = "00000000";
	final private static String NL = System.getProperty("line.separator");
	final private static String BLK = " ";
	public void print ( byte [] data, long offset ){
		printLineNumber(offset + data.length, this.numOfCols );
		printHexString(data, offset);
		this.hexArea.select(0, 20);
		this.hexArea.getCaret().setSelectionVisible(true);
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
			buf.append(BLK + toHexString(data[n]));
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
