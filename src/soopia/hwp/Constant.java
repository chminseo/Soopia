package soopia.hwp;

public class Constant {
	final static public String STRUCTNAME_HWP_BYTE = "HWP BYTE";
	public static final String STRUCTNAME_HWP_WORD = "HWP WORD";
	public static final String STRUCTNAME_HWP_DWORD = "HWP DWORD";
	public static final String STRUCTNAME_HWP_UINT8 = "HWP UINT8";
	public static final String STRUCTNAME_HWP_UINT16 = "HWP UINT16";
	public static final String STRUCTNAME_HWP_UINT32 = "HWP UINT32";
	
	public static final int HWPTAG_BEGIN = 0x10; // p.14
	public static final String [] TAGNAMES = new String [150];
	
	
	public final static String DOCUMENT_PROPERTIES = "HWPTAG_DOCUMENT_PROPERTIES";
	public final static String ID_MAPPINGS = "HWPTAG_ID_MAPPINGS";
	
	public final static String BIN_DATA = "HWPTAG_BIN_DATA";
	public final static String FACE_NAME = "HWPTAG_FACE_NAME";
	public final static String BORDER_FILL = "HWPTAG_BORDER_FILL";
	public final static String CHAR_SHAPE = "HWPTAG_CHAR_SHAPE";
	public final static String TAB_DEF = "HWPTAG_TAB_DEF";
	public final static String NUMBERING = "HWPTAG_NUMBERING";
	public final static String BULLET = "HWPTAG_BULLET";
	public final static String PARASHAPE = "HWPTAG_PARASHAPE";
	public final static String STYLE = "HWPTAG_STYLE";
	public final static String DOC_DATA = "HWPTAG_DOC_DATA";
	public final static String DISTRIBUTE_DOC_DATA = "HWPTAG_DISTRIBUTE_DOC_DATA";
	public final static String RESERVED = "RESERVED";
	public final static String COMPATIBLE_DOCUMENT = "HWPTAG_COMPATIBLE_DOCUMENT";
	public final static String LAYOUT_COMPATIBILITY = "HWPTAG_LAYOUT_COMPATIBILITY";
	public final static String FORBIDDEN_CHAR = "HWPTAG_FORBIDDEN_CHAR";
		
	public final static String PARA_HEADER = "HWPTAG_PARA_HEADER";
	public final static String PARA_TEXT = "HWPTAG_PARA_TEXT";
	public final static String PARA_CHAR_SHAPE = "HWPTAG_PARA_CHAR_SHAPE";
	public final static String PARA_LINE_SEG = "HWPTAG_PARA_LINE_SEG";
	public final static String PARA_RANGE_SEG = "HWPTAG_PARA_RANGE_SEG";
	public final static String CTRL_HEADER = "HWPTAG_CTRL_HEADER";
	public final static String LIST_HEADER = "HWPTAG_LIST_HEADER";
	public final static String PAGE_DEF = "HWPTAG_PAGE_DEF";
	public final static String FOOTNOTE_SHAPE = "HWPTAG_FOOTNOTE_SHAPE";
	public final static String PAGE_BORDER_FILL = "HWPTAG_PAGE_BORDER_FILL";
	
	static {
		
		TAGNAMES[0] = DOCUMENT_PROPERTIES;
		TAGNAMES[1] = ID_MAPPINGS;
		TAGNAMES[2] = BIN_DATA;
		TAGNAMES[3] = FACE_NAME;
		TAGNAMES[4] = BORDER_FILL;
		TAGNAMES[5] = CHAR_SHAPE;
		TAGNAMES[6] = TAB_DEF;
		TAGNAMES[7] = NUMBERING;
		TAGNAMES[8] = BULLET;
		TAGNAMES[9] = PARASHAPE;
		TAGNAMES[10] = STYLE;
		TAGNAMES[11] = DOC_DATA;
		TAGNAMES[12] = DISTRIBUTE_DOC_DATA;
		TAGNAMES[13] = RESERVED;
		TAGNAMES[14] = COMPATIBLE_DOCUMENT;
		TAGNAMES[15] = LAYOUT_COMPATIBILITY;
		TAGNAMES[78] = FORBIDDEN_CHAR;
		
		TAGNAMES[50] = PARA_HEADER;
		TAGNAMES[51] = PARA_TEXT;
		TAGNAMES[52] = PARA_CHAR_SHAPE;
		TAGNAMES[53] = PARA_LINE_SEG;
		TAGNAMES[54] = PARA_RANGE_SEG;
		TAGNAMES[55] = CTRL_HEADER;
		TAGNAMES[56] = LIST_HEADER;
		TAGNAMES[57] = PAGE_DEF;
		TAGNAMES[58] = FOOTNOTE_SHAPE;
		TAGNAMES[59] = PAGE_BORDER_FILL;
		
	};
}
