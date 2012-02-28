package soopia.hwp.structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.IOUtils;

public class StreamStructureFactory {
	
	public List<IDataStructure> createDataStructure ( final File hwpFile) {
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(hwpFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		POIFSReader poiReader = new POIFSReader();
		POIListener listener = new POIListener(hwpFile);
		poiReader.registerListener(listener);
		try {
			poiReader.read(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listener.dsList;
	}
	
	public static class POIListener implements POIFSReaderListener{
		File hwpFile ;
		List<IDataStructure> dsList ;
		public POIListener(File hwpFile){
			this.hwpFile = hwpFile;
			this.dsList = new ArrayList<>();
		}
		@Override
		public void processPOIFSReaderEvent(POIFSReaderEvent event) {
			DocumentInputStream dis = event.getStream();
			ByteBuffer buf = null;
			String path = hwpFile.getAbsolutePath();
			try {
				buf = ByteBuffer.wrap(IOUtils.toByteArray(dis));
				dsList.add(new StreamStructrue(path, event.getName(), buf));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
