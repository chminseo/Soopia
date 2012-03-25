package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * ������ ��Ÿ���� 32bit ������ ǥ��.<br/>
 * �������� 0xBBGGRR�� ����.
 * @author chmin
 * @page p.6 (ǥ-1)
 *
 */
public class ColorRef extends PrimitiveType<Integer> {

	public ColorRef(ByteBuffer src){
		this(src, src.position());
	}
	public ColorRef(ByteBuffer src, int offset) {
		super(offset, 4, src);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_COLORREF;
	}

	@Override
	protected void checkValid() {
		
	}

	@Override
	public Integer getValue() {
		return (int) Converter.getInt32(this.getBytes(), 0);
	}

	public Integer getRed() {
		return new Integer ( this.getValue() & 0xFF );
	}
	
	public Integer getGreen() {
		return new Integer ( (this.getValue()>>8) & 0xFF );
	}
	
	public Integer getBlue() {
		return new Integer ( (this.getValue()>>16) & 0xFF );
	}

	static String PATTERN = "00";
	public String toWebColorString() {
		String v ;
		StringBuilder sb = new StringBuilder("#");
		
		v = Integer.toHexString(getRed());
		sb.append(PATTERN.substring(v.length()) + v);
		v = Integer.toHexString(getGreen());
		sb.append(PATTERN.substring(v.length()) + v);
		v = Integer.toHexString(getBlue());
		sb.append(PATTERN.substring(v.length()) + v);
		return sb.toString().toUpperCase();
	}
	@Override
	public String toString(){
		return "ColorRef[" + this.getValue() + ", RGB =" + this.toWebColorString()+ "]";
	}

}
