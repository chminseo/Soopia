package soopia.hwp.type.stream;

import soopia.hwp.type.Dword;
import soopia.hwp.type.IRecordStructure;

public class RecordHeader {
	public Dword baseHeader;
	public Dword extHeader;
	
	public int getTagID(){
		return IRecordStructure.BIT_MASK_10 & baseHeader.getValue().intValue();
	}
	/**
	 *  헤더 부분을 제외한 레코드 데이터의 크기
	 * @return
	 */
	public long getDataSize() {
		return (extHeader == null) ? 
				IRecordStructure.BIT_MASK_12 & (baseHeader.getValue().intValue()>>20)
				: extHeader.getValue().longValue();
	}
	public int getHeaderSize(){
		return extHeader == null ? 4 : 8 ;
	}
	public int getLevel() {
		int val = (int) (baseHeader.getValue() >> 10);/* skip [0-9] for tagID */
		return ( val & IRecordStructure.BIT_MASK_10);
	}
	public int getTagValue(){
		return (int) (IRecordStructure.BIT_MASK_10 & baseHeader.getValue());
	}
	/**
	 * HEADER 정보를 주어진 data 바이트에 써넣는다.
	 * @param data
	 * @return
	 */
	public int get(byte[] data) {
		byte [] header = baseHeader.getBytes();
		System.arraycopy(header, 0, data, 0, header.length);
		if ( extHeader != null ){
			header = extHeader.getBytes();
			System.arraycopy(header, 0, data,
				(int)baseHeader.getLength(), header.length);
		}
		return this.getHeaderSize();
	}
}