package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 *  16 ��Ʈ �����Ϸ����� unsigned int �� �ش�(0~65535, p6) 
 *  
 * @author chmin
 *
 */
public class Word extends PrimitiveType<Integer> {

	public Word(byte [] data ){
		super( data);
	}
	@Override
	protected void checkValid() {
		int val = this.getValue();
		if ( val < 0 || val > 0xffff  ){
			throw new IllegalArgumentException(" value should be between 0 and 65535, but " + val);
		}
		if ( this.data.length != 2 ){
			throw new IllegalArgumentException("Word should be 2 bytes-length, but " + this.data.length );
		}
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_WORD;
	}
	
	@Override
	public Integer getValue() {
		return Converter.getWord(this.getBytes(), 0);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("WORD [" + getValue());
		sb.append(", bytes [");
		byte [] b = getBytes();
		for (int i = 0; i < b.length; i++) {
			sb.append(Integer.toHexString(0xFF&b[i]) + ", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("]]");
		return sb.toString();
	}
}
