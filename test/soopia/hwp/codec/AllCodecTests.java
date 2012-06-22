package soopia.hwp.codec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBinDataRecordDecoder.class
	,TestIdMappingRecordDecoder.class
	,TestFontFaceNameDecoder.class
	,TestBorderFillRecordDecoder.class
	,TestCharShapeRecordDecoder.class
	,TestTabRecordDecoder.class
	,TestNumberingRecordDecoder.class
	,TestParaShapeRecordDecorder.class
	,TestStyleRecordDecoder.class
	/*,TestParaTextDecoder.class*/ }
)
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class AllCodecTests {

}
