package soopia.hwp.type.stream;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class PreviewTextInfo extends AbstactStream {

	public PreviewTextInfo(HwpContext context, String structureName, byte [] data) {
		super(context, structureName, data);
	}
}
