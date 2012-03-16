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
 *    Size = 0xFFF �̸� header �ٷ� �ڿ� DWORD �ڷ����� �ϳ� �� �߰��Ǿ DATA�κ��� ���̸� ��Ÿ����.
 *    
 * @author chmin
 * @page p.14
 *
 */
public abstract class AbstractRecord implements IRecordStructure {

	protected IStreamStruct baseStruct ;
	protected int offset ;
	
	protected RecordHeader header ;
	protected ByteBuffer src;
	protected AbstractRecord(RecordHeader header, IStreamStruct ds, int offset){
		this.baseStruct = ds;
		this.offset = offset;
		this.header = header;
		this.src = cloneBuffer(ds.getBuffer());
	}
	
	private ByteBuffer cloneBuffer( ByteBuffer buf ){
		byte [] b = new byte [(int)header.getDataSize()];
		buf.position(this.offset + header.getHeaderSize());
		buf.get(b);
		return ByteBuffer.wrap(b);
	}
		
	@Override
	public String getStrucureName() {
		return this.getTagName();
	}
	/**
	 * HEADER �� DATA �� ������ ��ü ����Ʈ ����
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
	 * ������ ���ڵ� �����͸� ��Ÿ���� ����Ʈ���� ByteBuffer�� ���μ� ��ȯ�Ѵ�. 
	 */
	@Override
	public ByteBuffer getBuffer() {
		byte [] b = new byte [header.getHeaderSize() + this.src.capacity()];
		int pos = header.get(b);
		this.src.clear();
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
	 * �������� Tag ID �� ��޵Ǵ� ���ڿ��� ��ȯ.
	 * 
	 */
	@Override
	public String getTagName() {
		int idx = this.getTagValue() - Constant.HWPTAG_BEGIN;
		return Constant.TAGNAMES[idx];
	}
	/**
	 * Tag value ��ȯ ( HWP 5.0 ���� ���������� HWPTAG_BEGIN + n ���� ��޵�)
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
