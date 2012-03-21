package soopia.hwp.hexdump.model;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
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
