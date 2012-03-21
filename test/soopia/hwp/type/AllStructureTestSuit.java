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
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class AllStructureTestSuit {

}
