package soopia.hwp.type.stream;

import java.util.Iterator;
import java.util.List;

import soopia.hwp.Constant;
import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.record.StyleRecord;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class DocInfoStream extends AbstactStream {
	
	public DocInfoStream(HwpContext context, byte [] data) {
		super(context, "DocInfo", data);
	}
	
	public void addRecord(IRecordStructure record){
		this.records.add(record);
	}

	public Iterator<IRecordStructure> recordIterator() {
		return records.iterator();
	}

	public StyleRecord getStyleAt(int index) {
		// TODO index ���� üũ
		int current = 0;
		List<StyleRecord> styles = (List<StyleRecord>) getRecord(Constant.STYLE);
		Iterator<StyleRecord> it = styles.iterator();
		IRecordStructure rs = null;
		while ( it.hasNext() ) {
			rs = it.next();
			if ( current  == index ){
				break;
			}
			current ++ ;
		}
		return (StyleRecord) rs;
	}
}