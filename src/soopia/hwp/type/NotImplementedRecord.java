package soopia.hwp.type;

import soopia.hwp.type.stream.RecordHeader;

public class NotImplementedRecord extends AbstractRecord {

	public NotImplementedRecord(RecordHeader header, IStreamStruct ds,
			int offset) {
		super(header, ds, offset);
	}

	@Override
	public String getTagName() {
		return "[NI] " + super.getTagName();
	}
}
