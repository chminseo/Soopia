package soopia.hwp.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestByteSource.class, 
	TestConverter.class })
public class AllUtilTests {

}
