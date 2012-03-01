package soopia.hwp.structure;

public class Constant {
	final static public String STRUCTNAME_HWP_BYTE = "HWP BYTE";
	public static final String STRUCTNAME_HWP_WORD = "HWP WORD";
	public static final String STRUCTNAME_HWP_DWORD = "HWP DWORD";
	public static final String STRUCTNAME_HWP_UINT16 = "HWP UINT16";
	public static final String STRUCTNAME_HWP_UINT32 = "HWP UINT32";
	
	public static final int HWPTAG_BEGIN = 0x10; // p.14
	public static final String [] TAGNAMES = new String [150];
	
	static {
		
		TAGNAMES[0] = "HWPTAG_DOCUMENT_PROPERTIES";
		TAGNAMES[1] = "HWPTAG_ID_MAPPINGS";
		TAGNAMES[2] = "HWPTAG_BIN_DATA";
		TAGNAMES[3] = "HWPTAG_FACE_NAME";
		TAGNAMES[4] = "HWPTAG_BORDER_FILL";
		TAGNAMES[5] = "HWPTAG_CHAR_SHAPE";
		TAGNAMES[6] = "HWPTAG_TAB_DEF";
		TAGNAMES[7] = "HWPTAG_NUMBERING";
		TAGNAMES[8] = "HWPTAG_BULLET";
		TAGNAMES[9] = "HWPTAG_PARASHAPE";
		TAGNAMES[10] = "HWPTAG_STYLE";
		TAGNAMES[11] = "HWPTAG_DOC_DATA";
		TAGNAMES[12] = "HWPTAG_DISTRIBUTE_DOC_DATA";
		TAGNAMES[13] = "RESERVED";
		TAGNAMES[14] = "HWPTAG_COMPATIBLE_DOCUMENT";
		TAGNAMES[15] = "HWPTAG_LAYOUT_COMPATIBILITY";
		TAGNAMES[78] = "HWPTAG_FORBIDDEN_CHAR";
	};
}
