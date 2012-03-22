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
	TestInt8.class,
	TestInt32.class,
	TestWChar.class,
	TestWord.class,
	TestColorRef.class,
	TestHwpUnit.class
})
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class AllStructureTestSuit {

}
