package soopia.hwp.hexdump.structure;

import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.structure.DataStructureFactory;
import soopia.hwp.structure.IDataStructure;

public class TestDataStructureFactory {
	DataStructureFactory factory ;
	static String fName = "sample.hwp";
	static File hwpFile = null;
//	InputStream in;
	
	@Before
	public void setUp() throws Exception {
		factory = new DataStructureFactory();
		URL url = this.getClass().getClassLoader().getResource(fName);
		hwpFile = new File ( url.toURI() );
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateDataStructure() {
		
		List<IDataStructure> list = factory.createDataStructure(hwpFile);
		assertEquals (9, list.size());
//		for( IDataStructure ds : list){
//			System.out.println(ds.getStrucureName());
//		}
	}

}
