package soopia.hwp.hexdump.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.WeakHashMap;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.StreamStructureFactory;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class FileModel {

	private WeakHashMap<String, ByteBuffer> activeFileMap ;
	
	private ArrayList<FileModelListener> listener ;
	public FileModel(){
		activeFileMap = new WeakHashMap<>();
		listener = new ArrayList<>();
	}
	
	public void addFileModelListener(FileModelListener l){
		if ( ! listener.contains(l)){
			listener.add(l);
		}
	}
	public void addFile (File file) throws IOException {
		if ( ! file.exists()){
			throw new FileNotFoundException("no such file : " + file.getPath());
		}
		String fileMapKey = file.getAbsolutePath().toLowerCase();
		StreamStructureFactory dsf = new StreamStructureFactory();
//		List<IDataType> dsList = dsf.createDataStructure(file);
		HwpContext ctx = dsf.createHwpContext(file);
		
		fireEvent("add", new FileModelEvent(fileMapKey, ctx));
	}
	protected void fireEvent(String eventType, FileModelEvent evt ){
		if ( eventType.equals ("add")){
			for ( FileModelListener l : listener ){
				l.fileAdded(evt);
			}
		}
	}
}
