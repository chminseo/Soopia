package soopia.hwp.type;

import java.util.List;

public interface IStreamStruct extends IDataType {

	public abstract List<? extends IRecordStructure> getRecord(String recordType);

}
