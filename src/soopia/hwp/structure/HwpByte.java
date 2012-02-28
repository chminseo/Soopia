package soopia.hwp.structure;

import java.nio.ByteBuffer;

import soopia.hwp.util.Converter;
/**
 * ��ȣ���� 1 ����Ʈ (0~255, p6)
 * @author yeori
 *
 */
public class HwpByte extends AbstractType<Integer> {
	/**
	 * �־��� ���� src ���� offset ��ġ���� HwpByte �ν��Ͻ��� �����Ѵ�.
	 * (��ȣ���� 1 ����Ʈ, 0~255)
	 * @param offset
	 * @param src
	 */
	public HwpByte(ByteBuffer src, int offset) {
		super(offset, 1, src);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_BYTE;
	}
	
	@Override
	public byte [] getBytes(){
		byte [] data = new byte[1];
		this.src.rewind();
		this.src.get(data);
		return data;
	}
	
	@Override
	protected void checkValid() {
		src.rewind();
		int val = src.get();
		if ( val < 0 || val >= 256 ){
			throw new IllegalArgumentException(" value should be between 0 and 255, but " + val);
		}

	}

	@Override
	public Integer getValue() {
		return (int) this.getBytes()[0];
	}
	
}
