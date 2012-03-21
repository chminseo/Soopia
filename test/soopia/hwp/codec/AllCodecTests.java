package soopia.hwp.codec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBinDataRecordDecoder.class
	,TestIdMappingRecordDecoder.class
	,TestFontFaceNameDecoder.class
	,TestBorderFillRecordDecoder.class
	/*,TestParaTextDecoder.class*/ }
)
public class AllCodecTests {

}
