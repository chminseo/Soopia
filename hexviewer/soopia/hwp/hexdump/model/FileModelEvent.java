package soopia.hwp.hexdump.model;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class FileModelEvent {
	private String filePath ;
//	private List<IDataType> ds;
	private Object obj;
	public FileModelEvent(String filePath, Object obj) {
		this.filePath = filePath;
		this.obj = obj;
	}
	public String getFilePath() {
		return filePath;
	}
//	public List<IDataType> getDataStructureList() {
//		return ds;
//	}
	public Object getObject(){
		return this.obj;
	}
	
}
