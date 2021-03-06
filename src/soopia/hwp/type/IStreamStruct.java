package soopia.hwp.type;

import java.util.List;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public interface IStreamStruct extends IDataType {

	public abstract List<? extends IRecordStructure> getRecord(String recordType);

}
