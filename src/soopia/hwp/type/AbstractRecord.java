package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.type.stream.RecordHeader;

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
	protected byte [] data ;
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
//		byte [] header = getHeaderBytes();
//		byte [] body = getDataBytes();
//		byte [] b = new byte[header.length + body.length];
//		System.arraycopy(header, 0, b, 0, header.length);
//		System.arraycopy(body, 0, b, header.length, body.length);
//		return b;
		return data;
	}
	/*----------- IRecordStructure ----------- */
	@Override
	public Integer getHeaderLength() {
		return (int) header.getHeaderSize(); // 4 or 8 bytes
	}

	@Override
	public byte[] getHeaderBytes() {
		byte [] b = new byte[getHeaderLength()];
		header.get(b);
		return b;
	}

	@Override
	public Integer getDataLength() {
		return (int) header.getDataSize();
	}
	@Override
	public ByteBuffer getDataBuffer() {
		// FIXME encoder�� �̿��ؼ� �� ��
		throw new RuntimeException("not implemented");
	}

	@Override
	public byte[] getDataBytes() {
		// FIXME encoder�� �� ��. 
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
	}

	@Override
	public HwpContext getHwpContext() {
		return this.baseStruct.getHwpContext();
	}
	@Override
	public IStreamStruct getParentStream() {
		return this.baseStruct;
	}

	/**
	 * ���ڵ� �����͸� ��Ÿ���� ����Ʈ �迭�� �����Ѵ�.
	 * @param data
	 */
	public void setBytes(byte[] data) {
		/* TODO 
		 * ���� �б� �������� ���� ���̱� ������ encoder�� �ۼ��ϴ� ��� ��ü �迭�� �����ϰ� ����.
		 * ���� ���� ����� �߰��Ǹ� �� �޼ҵ带 ���ְ� encoder�� ����Ʈ�迭�� ����, ��ȯ�ϵ��� ��.
		 */
		this.data = data;
	}
	
	@Override
	public String toString(){
		return getStrucureName() + "[tagID=" + getTagValue() +
				", level=" + getLevel() +
				", length=" + getLength() + "]";
	}
}
