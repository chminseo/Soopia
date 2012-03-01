package soopia.hwp.structure;

public interface Constant {
	final static public String STRUCTNAME_HWP_BYTE = "HWP BYTE";
	public static final String STRUCTNAME_HWP_WORD = "HWP WORD";
	public static final String STRUCTNAME_HWP_DWORD = "HWP DWORD";
	public static final String STRUCTNAME_HWP_UINT16 = "HWP UINT16";
	public static final String STRUCTNAME_HWP_UINT32 = "HWP UINT32";
	
	public static final int HWPTAG_BEGIN = 0x10; // p.14
	public static final String [] TAGNAMES ={
		"HWPTAG_DOCUMENT_PROPERTIES",
		"HWPTAG_ID_MAPPINGS",
		"HWPTAG_BIN_DATA"
	};
}
