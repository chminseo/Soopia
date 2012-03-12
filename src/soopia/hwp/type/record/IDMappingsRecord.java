package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.stream.RecordHeader;

public class IDMappingsRecord extends AbstractRecord {
	private UInt16 f0_numOfBinData ;
	private UInt16 f1_numOfKorFont ;
	private UInt16 f2_numOfEngFont ;
	private UInt16 f3_numOfChnFont ;
	private UInt16 f4_numOfJpnFont ;
	private UInt16 f5_numOfEtcFont ;
	private UInt16 f6_numOfSymbolFont;
	private UInt16 f7_numOfUserFont ;
	private UInt16 f8_numOfBorder ;
	private UInt16 f9_numOfFontShape;
	private UInt16 f10_numOfTab;		/* �� ����    */
	private UInt16 f11_numOfParaNum;	/* ���ܹ�ȣ */
	private UInt16 f12_numOfParaSymbol;	/* �۸Ӹ�ǥ */
	private UInt16 f13_numOfParaShape;	/* ���ܸ��  */
	private UInt16 f14_numOfStyle;		/* ��Ÿ��     */
	private UInt16 f15_numOfMemoShape;	/* �޸� ��� */
	
//	private UInt16 [] mappings = new UInt16[16];
	
	public IDMappingsRecord(RecordHeader header, IStreamStruct ds, int offset) {
		super(header, ds, offset);
	}

}
