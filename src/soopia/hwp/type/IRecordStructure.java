package soopia.hwp.type;

import java.nio.ByteBuffer;

/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * HWP 5.0 ���� �������� "������ ���ڵ�"�� ���� �ڷᱸ��
 * 
 * @author chmin
 * @page p.14
 */
public interface IRecordStructure extends IDataType {
	public static final int BIT_MASK_10 = 0x3ff;
	public static final int BIT_MASK_12 = 0xfff;

	/**
	 * ������ ���ڵ忡�� HEADER ����
	 * @return
	 */
	public Integer getHeaderLength();
	public byte [] getHeaderBytes();
	/**
	 * ������ ���ڵ忡�� DATA ����
	 * @return
	 */
	public Integer getDataLength();
	public ByteBuffer getDataBuffer();
	public byte [] getDataBytes();
	
	public int getLevel();
	/**
	 * HWP 5.0 ���� �������� Tag ID �� ��޵Ǵ� ���ڿ��� ��ȯ.
	 * 
	 */
	public String getTagName();
	/**
	 * HWP 5.0 ���� �������� HWPTAG_BEGIN + n ���� ��޵Ǵ� �������� ��ȯ.
	 */
	public Integer getTagValue();
	
	public IStreamStruct getParentStream();
}