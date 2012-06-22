package soopia.hwp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import soopia.hwp.util.AllUtilTests;

@RunWith(Suite.class)
@SuiteClasses({
	soopia.hwp.codec.AllCodecTests.class
	,soopia.hwp.type.AllStructureTestSuit.class
	,AllUtilTests.class
	
})
public class AllHwpTests {

}
