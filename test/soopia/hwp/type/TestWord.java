package soopia.hwp.type;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import soopia.hwp.type.Word;
import soopia.hwp.util.ByteArraySource;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class TestWord {

	IByteSource src ;
	@Before
	public void setUp() throws Exception {
		src = new ByteArraySource(new byte[]{0x40, 0x00, 0x10, 0x12, 0x20, (byte)0xFD});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Word word = new Word(src.skip(2).consume(2));
		assertEquals (new Integer(0x1210), word.getValue());
		assertEquals (2, word.getLength());
	}

}
