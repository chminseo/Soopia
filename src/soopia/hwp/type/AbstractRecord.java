package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;

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

	protected static final int BIT_MASK_10 = 0x3ff;
	protected static final int BIT_MASK_12 = 0xfff;

	private IStreamStruct baseStruct ;
	protected int offset ;
	
	private Dword header ;
	public AbstractRecord(IStreamStruct ds, int offset){
		this.baseStruct = ds;
		this.offset = offset;
	}
	
	AbstractRecord init() {
		header = new Dword(baseStruct.getBuffer(), offset);
		/*check if it's data length is bigger than 4095 */
		if ( getDataLength() >= 0xfff){
			// TODO ���� � ���·� DATA ���̰� �����Ǵ��� �� �� �����Ƿ� �߰� �� �����ϵ��� ��.
			throw new RuntimeException ("������ ���̰� 4095���� ū ���ڵ� �߰�. �����ؾ���");
		}
		return this;
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
		ByteBuffer buf = ((ByteBuffer) baseStruct
				.getBuffer()
				.limit(offset + (int)getLength())
				.position(offset)
			).slice().asReadOnlyBuffer();
		return buf;
	}
	@Override
	public byte[] getBytes() {
		byte [] data = new byte[(int)getLength()];
		ByteBuffer buf = this.getBuffer();
		buf.flip();
		baseStruct.getBuffer().get(data, 0, data.length);
		return data;
	}
	/*----------- IRecordStructure ----------- */
	@Override
	public Integer getHeaderLength() {
		return (int) header.getLength(); // 4 bytes
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
		long val = header.getValue() >> 20 ;
		return (int) (val & BIT_MASK_12);
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
		int val = (int) (header.getValue() >> 10);
		return (int) ( val & BIT_MASK_10);
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
		return BIT_MASK_10 & header.getValue().intValue();
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
