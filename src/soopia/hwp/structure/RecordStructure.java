package soopia.hwp.structure;

import java.nio.ByteBuffer;

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
public class RecordStructure implements IRecordStructure {
	protected static int BIT_MASK_10 = 0x3ff;
	protected static int BIT_MASK_12 = 0xfff;

	private IDataStructure baseStruct ;
	private int offset ;
	
	private Dword header ;
	
	public RecordStructure(IDataStructure ds, int offset){
		this.baseStruct = ds;
		this.offset = offset;
	}
	
	RecordStructure init() {
		header = new Dword(baseStruct.getBuffer(), offset);
		/*check if it's data length is bigger than 4095 */
		if ( getDataLength() >= 0xfff){
			// TODO ���� � ���·� DATA ���̰� �����Ǵ��� �� �� �����Ƿ� �߰� �� �����ϵ��� ��.
			throw new RuntimeException ("������ ���̰� 4095���� ū ���ڵ� �߰�. �����ؾ���");
		}
		return this;
	}
	
	
	@Override
	public String getFilePath() {
		return baseStruct.getFilePath();
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
	
	/**
	 * �������� Tag ID �� ��޵Ǵ� ���ڿ��� ��ȯ.
	 * 
	 */
	@Override
	public String getTagName() {
		int idx = this.getTagValue() - 0x10;
		return Constant.TAGNAMES[idx];
	}
	/**
	 * Tag value ��ȯ ( HWP 5.0 ���� ���������� HWPTAG_BEGIN + n ���� ��޵�)
	 */
	@Override
	public Integer getTagValue() {
		return BIT_MASK_10 & header.getValue().intValue();
	}
	
	
}
