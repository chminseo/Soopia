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
 *    Size = 0xFFF 이면 header 바로 뒤에 DWORD 자료형이 하나 더 추가되어서 DATA부분의 길이를 나타낸다.
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
			// TODO 아직 어떤 형태로 DATA 길이가 구성되는지 알 수 없으므로 발견 시 구현하도록 함.
			throw new RuntimeException ("데이터 길이가 4095보다 큰 레코드 발견. 구현해야함");
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
	 * 문서에서 Tag ID 로 언급되는 문자열을 반환.
	 * 
	 */
	@Override
	public String getTagName() {
		return Constant.TAGNAMES[this.getTagValue()- 0x10];
//		return "HWPTAG_DOCUMENT_PROPERTIES";
	}
	/**
	 * Tag value 반환 ( HWP 5.0 포맷 문서에서는 HWPTAG_BEGIN + n 으로 언급됨)
	 */
	@Override
	public Integer getTagValue() {
		return BIT_MASK_10 & header.getValue().intValue();
	}
	
	
}
