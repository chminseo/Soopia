package soopia.hwp.hexdump.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HexviewPanel extends JPanel {
	
	private static final long serialVersionUID = 4007798758322581210L;

	private int numOfCols;
	
	private FixedColumnTextArea lineNumArea;
	private FixedColumnTextArea hexArea;
	private FixedColumnTextArea charArea;
	
	private JLabel northLabel;
	
	private Font defaultFont ;
	
	private PopupHandler popupHandler = new PopupHandler();
	/**
	 * Create the panel.
	 */
	public HexviewPanel() {
		this(16, 12);
	}
	
	final private static String PATTERN_LABEL = "         0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F  ";
	
	public HexviewPanel(int numOfCols, int fontSize ){
		this.numOfCols = numOfCols;
		this.lineNumArea = new FixedColumnTextArea(8);
		this.hexArea = new FixedColumnTextArea(numOfCols*3);
		this.charArea = new FixedColumnTextArea(numOfCols);
		this.northLabel = new JLabel();
		defaultFont = new Font("Courier New", Font.PLAIN, fontSize);
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
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
		scrollPane.setAlignmentY(0.0f);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setColumnHeaderView(northLabel);
		
		add(scrollPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setAlignmentY(0.0f);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		Font labelFont = new Font(defaultFont.getFontName(), Font.BOLD, defaultFont.getSize()+2);
		lblOffset = new JLabel("OFFSET");
		lblOffset.setFont(labelFont);
		GridBagConstraints gbc_lblOffset = new GridBagConstraints();
		gbc_lblOffset.insets = new Insets(0, 5, 0, 5);
		gbc_lblOffset.gridx = 0;
		gbc_lblOffset.gridy = 0;
		panel_1.add(lblOffset, gbc_lblOffset);
		
		offsetLabel = new JLabel("0 bytes");
		GridBagConstraints gbc_offsetLabel = new GridBagConstraints();
		gbc_offsetLabel.insets = new Insets(0, 0, 0, 10);
		gbc_offsetLabel.gridx = 1;
		gbc_offsetLabel.gridy = 0;
		panel_1.add(offsetLabel, gbc_offsetLabel);
		
		lblLength = new JLabel("LENGTH");
		lblLength.setFont(labelFont);
		GridBagConstraints gbc_lblLength = new GridBagConstraints();
		gbc_lblLength.insets = new Insets(0, 0, 0, 5);
		gbc_lblLength.gridx = 2;
		gbc_lblLength.gridy = 0;
		panel_1.add(lblLength, gbc_lblLength);
		
		lengthLabel = new JLabel("23 bytes");
		GridBagConstraints gbc_lengthLabel = new GridBagConstraints();
		gbc_lengthLabel.anchor = GridBagConstraints.WEST;
		gbc_lengthLabel.gridx = 3;
		gbc_lengthLabel.gridy = 0;
		panel_1.add(lengthLabel, gbc_lengthLabel);

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
		
		hexArea.addMouseListener(popupHandler);
	}
	
	void asByteArrayFormat(String text){
		StringBuilder buf = new StringBuilder("new byte[]{");
		String [] lines = text.split(NL);
		int tmp;
		for( int i = 0 ;i < lines.length ; i++){
			String [] token = lines[i].split(" ");
			for ( int k = 0 ; k < token.length ; k++){
//				if ( "".equals(token[k].trim()) ) continue;
				System.out.println("[" + k + ":"+ token[k] + "]");
				tmp = Integer.parseInt(token[k], 16);
				if ( tmp > 127 ) buf.append("(byte)");
				buf.append("0x" + token[k] + ", ");
			}
			buf.append(NL);
		}
		buf.delete(buf.length()-3, buf.length()); // ", " + NL
		buf.append("};");
		System.out.println(buf.toString());
	}
	class PopupHandler extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if ( SwingUtilities.isLeftMouseButton(e)){
				return ;
			}
			JTextComponent comp = (JTextComponent) e.getSource();
			Caret caret = comp.getCaret();
			int min = Math.min(caret.getDot(), caret.getMark());
			int max = Math.max(caret.getDot(), caret.getMark());
			
			if ( min == max) return ;
			try {
				asByteArrayFormat ( comp.getText(min, max-min) );
				
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			
		}
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
	private JPanel panel_1;
	private JLabel lblOffset;
	private JLabel offsetLabel;
	private JLabel lblLength;
	private JLabel lengthLabel;
	public void print ( byte [] data, long offset ){
		printLineNumber(offset, offset + data.length, this.numOfCols );
		printHexString(data, offset);
		
		hexArea.select(0, 0);
		charArea.select(0, 0);
		CaretHandler ch1 = new CaretHandler(hexArea);
		charArea.getCaret().addChangeListener(ch1);
		
		hexArea.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				Caret c = ((JTextComponent)e.getSource()).getCaret();
				int p0 = Math.min(c.getDot(), c.getMark());
				int p1 = Math.max(c.getDot(), c.getMark());
				
				updateByteInfo((JTextArea)e.getSource(), p0, p1);
//				System.out.println("[" + p0 + ", " + p1 + "]");
				
			}
		});
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
	
	private void updateByteInfo (JTextArea comp , int p0, int p1){
		int colSize = comp.getColumns();
		int offset = p0 - p0 / (colSize+NL.length());
		int end = p1 - p1/(colSize+NL.length());
		
		if ( offset % 3 == 1){offset -= 1;}
		else if ( offset % 3 == 2){offset += 1;}
		
		if ( end % 3 > 0 ){ end += 1;}
		
		offset /= 3;
		end /= 3;
		offsetLabel.setText(offset + " bytes");
		lengthLabel.setText( (end - offset) + " bytes");
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
	
	private void printLineNumber(long offset, long numOfBytes, int col ){
		StringBuffer lineNum = new StringBuffer();
		String val ;
		int starting = (int) offset/col;
		int row = (int)(numOfBytes / col);
		
		for ( int i = starting ; i <= row ; i++){
			val = Integer.toHexString(col* i).toUpperCase();
			lineNum.append(PATTERN_LINENUM.substring(val.length()));
			lineNum.append(val + NL);
		}
		
		lineNumArea.setText("");
		lineNumArea.append ( lineNum.toString() );
	}
}
