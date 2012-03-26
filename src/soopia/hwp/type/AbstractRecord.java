package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.Converter;

/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * Record structure consists of HEADER and DATA
 * 
 *    [31        20][19     10][9        0]
 *    +--------+--------+--------+--------+--------------
 *    |SSSSSSSS|SSSSLLLL|LLLLLLTT|TTTTTTTT|       DATA
 *    +--------+--------+--------+--------+--------------
 *    [    SIZE    ][  LEVEL  ][  TAG ID  ]
 *    
 *    Size = 0xFFF 이면 header 바로 뒤에 DWORD 자료형이 하나 더 추가되어서 DATA부분의 길이를 나타낸다.
 *    
 * @author chmin
 * @page p.14
 *
 */
public abstract class AbstractRecord implements IRecordStructure {

	protected IStreamStruct baseStruct ;
	
	protected RecordHeader header ;
	protected AbstractRecord(RecordHeader header, IStreamStruct ds){
		this.baseStruct = ds;
		this.header = header;
	}
			
	@Override
	public String getStrucureName() {
		return this.getTagName();
	}
	/**
	 * HEADER 와 DATA 를 포함한 전체 바이트 갯수
	 */
	@Override
	public long getLength() {
		return this.getHeaderLength() + this.getDataLength();
	}

	@Override
	public int getOffset() {
		throw new RuntimeException("없어질 메소드");
	}

	@Override
	public byte[] getBytes() {
		// TODO header하고 content 같이 복제
		byte [] b = new byte[(int)getLength()];
		int offset = header.get(b);
//		System.arraycopy(data, 0, b, offset, data.length);
		return b;
	}
	/*----------- IRecordStructure ----------- */
	@Override
	public Integer getHeaderLength() {
		return (int) header.getHeaderSize(); // 4 or 8 bytes
	}

	@Override
	public byte[] getHeaderBytes() {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented");
	}

	@Override
	public Integer getDataLength() {
		return (int) header.getDataSize();
	}
	@Override
	public ByteBuffer getDataBuffer() {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented");
	}

	@Override
	public byte[] getDataBytes() {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented");
	}
	
	@Override
	public int getLevel() {
		return header.getLevel();
	};
	
	/**
	 * 문서에서 Tag ID 로 언급되는 문자열을 반환.
	 * 
	 */
	@Override
	public String getTagName() {
		int idx = this.getTagValue() - Constant.HWPTAG_BEGIN;
		return Constant.TAGNAMES[idx];
	}
	/**
	 * Tag value 반환 ( HWP 5.0 포맷 문서에서는 HWPTAG_BEGIN + n 으로 언급됨)
	 */
	@Override
	public Integer getTagValue() {
		return header.getTagValue();
//		return IRecordStructure.BIT_MASK_10 & header.getValue().intValue();
	}

	@Override
	public HwpContext getHwpContext() {
		return this.baseStruct.getHwpContext();
	}
	@Override
	public IStreamStruct getParentStream() {
		return this.baseStruct;
	}
}
