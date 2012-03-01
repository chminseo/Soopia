package soopia.hwp.structure;

import java.nio.ByteBuffer;

/**
 * HWP 5.0 ���� �������� "������ ���ڵ�"�� ���� �ڷᱸ��
 * 
 * @author chmin
 * @page p.14
 */
public interface IRecordStructure extends IDataStructure {
	/**
	 * ������ ���ڵ忡�� HEADER ����
	 * @return
	 */
	public Integer getHeaderLength();
	public ByteBuffer getHeaderBuffer();
	public byte [] getHeaderBytes();
	/**
	 * ������ ���ڵ忡�� DATA ����
	 * @return
	 */
	public Integer getDataLength();
	public ByteBuffer getDataBuffer();
	public byte [] getDataBytes();
	/**
	 * HWP 5.0 ���� �������� Tag ID �� ��޵Ǵ� ���ڿ��� ��ȯ.
	 * 
	 */
	public String getTagName();
	/**
	 * HWP 5.0 ���� �������� HWPTAG_BEGIN + n ���� ��޵Ǵ� �������� ��ȯ.
	 */
	public Integer getTagValue();
}