package soopia.hwp.type;

import java.util.List;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public interface IStreamStruct extends IDataType {

	public abstract List<? extends IRecordStructure> getRecord(String recordType);

}
