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
	 *  header1에서 20~31 bit값과 확장 헤더가 존재할 경우 이를 더한 값
	 * @return
	 */
	public long getDataSize() {
		long length = IRecordStructure.BIT_MASK_12 & (baseHeader.getValue().intValue()>>20);
		if ( extHeader != null ){
			length += extHeader.getValue().longValue();
		}
		return length ; 
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
		return header.length;
	}
}