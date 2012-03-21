package soopia.hwp.type;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
	TestDword.class,
	TestHwpByte.class,
	TestRecordStructure.class,
	TestStreamStructureFactory.class,
	TestUInt8.class,
	TestUInt16.class,
	TestUInt32.class,
	TestInt32.class,
	TestWChar.class,
	TestWord.class,
	TestColorRef.class
})
public class AllStructureTestSuit {

}
