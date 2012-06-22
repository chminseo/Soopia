package soopia.hwp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	soopia.hwp.codec.AllCodecTests.class
	,soopia.hwp.type.AllStructureTestSuit.class
	,soopia.hwp.util.AllUtilTests.class
	
})
public class AllHwpTests {

}
