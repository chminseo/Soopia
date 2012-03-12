package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.Converter;

/**
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

	private IStreamStruct baseStruct ;
	protected int offset ;
	
	private RecordHeader header ;
	private ByteBuffer src;
	protected AbstractRecord(RecordHeader header, IStreamStruct ds, int offset){
		this.baseStruct = ds;
		this.offset = offset;
		this.header = header;
		this.src = cloneBuffer(ds.getBuffer());
	}
	
	private ByteBuffer cloneBuffer( ByteBuffer buf ){
		byte [] b = new byte [(int)header.getDataSize()];
		buf.position(header.getHeaderSize());
		buf.get(b);
		return ByteBuffer.wrap(b);
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
		return this.offset;
	}

	/**
	 * 현재의 레코드 데이터를 나타내는 바이트값을 ByteBuffer로 감싸서 반환한다. 
	 */
	@Override
	public ByteBuffer getBuffer() {
		byte [] b = new byte [header.getHeaderSize() + this.src.capacity()];
		int pos = header.get(b);
		this.src.get(b, pos, b.length - pos);
		return ByteBuffer.wrap(b);
	}
	@Override
	public byte[] getBytes() {
		byte [] data = new byte[(int)getLength()];
		int offset = header.get(data);
		this.src.clear();
		this.src.get(data, offset, data.length-offset);
		
		return data;
	}
	/*----------- IRecordStructure ----------- */
	@Override
	public Integer getHeaderLength() {
		return (int) header.getHeaderSize(); // 4 or 8 bytes
	}
	@Override
	public ByteBuffer getHeaderBuffer() {
//		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented");
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
