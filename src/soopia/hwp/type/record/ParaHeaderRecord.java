package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.Dword;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.RecordHeader;

public class ParaHeaderRecord extends AbstractRecord {

	public ParaHeaderRecord(RecordHeader header, IStreamStruct ds, int offset) {
		super(header, ds, offset);
	}

}
