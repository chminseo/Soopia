package soopia.hwp.hexdump.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;
import soopia.hwp.type.StreamStructureFactory;

public class FileModel {
//	private WeakHashMap<String, String> fsMap ;
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
