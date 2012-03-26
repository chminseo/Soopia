package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.Converter;

/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
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
	 * HEADER �� DATA �� ������ ��ü ����Ʈ ����
	 */
	@Override
	public long getLength() {
		return this.getHeaderLength() + this.getDataLength();
	}

	@Override
	public int getOffset() {
		throw new RuntimeException("������ �޼ҵ�");
	}

	@Override
	public byte[] getBytes() {
		// TODO header�ϰ� content ���� ����
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
