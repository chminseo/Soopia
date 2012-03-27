package soopia.hwp.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestByteSource {

	IByteSource bs ;
	@Before
	public void setUp() throws Exception {
		bs = new ByteArraySource(randomBytes(20));
	}

	private byte [] randomBytes(final int size){
		byte [] data = new byte[size];
		for (int i = 0; i < size; i++) {
			data[i] = (byte)(i*2);
		}
		return data;
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPosition(){
		assertEquals ( 0, bs.position());
	}
	@Test
	public void test_consume() {
		assertEquals(4, bs.consume(4).length );
		
		try {
			bs.consume(-1);
			fail("NegativeArraySizeException not thrown");
		} catch (NegativeArraySizeException e){}
		
		try {
			bs.consume(25);
			fail("IndexOutofBoundsException not thrown");
		} catch(IndexOutOfBoundsException e){}
	}
	
	@Test
	public void test_consume_insufficient_remaining(){
		bs.consume(18);
		assertEquals(2, bs.remaining());
		
		try {
			assertEquals(2, bs.consume(4).length);
		} catch (IndexOutOfBoundsException e){}
	}

	@Test
	public void testConsume_with_manual_moving() {
		try {
			bs.consume(-1);
			fail("NegativeArraySizeException not thrown");
		} catch (NegativeArraySizeException e){}
		
		bs.consume(10, 5);
		System.out.println(bs);
		assertEquals(5, bs.position());
		assertEquals(15, bs.remaining());
	}

	@Test
	public void testPeep() {
		bs.consume(3);
		assertArrayEquals(new byte[]{6, 8}, bs.peep(2));
		assertEquals(3, bs.position());
		try {
			bs.peep(-3);
			fail("NegativeArraySizeException not thrown");
		} catch (NegativeArraySizeException e) {}
	}
	
	@Test
	public void test_mark_and_rollback() {
		try {
			bs.rollback();
			fail("IllegalStateException not thrown");
		} catch (IllegalStateException e){}
		
		bs.consume(3);
		bs.mark().consume(5);
		assertEquals(3, bs.rollback().position());
	}

	@Test
	public void testBack() {
		bs.consume(5);
		assertEquals(3, bs.back(2).position());
		
		try {
			bs.back(5); // out of bound
			fail("IndexOutOfBoundsException not thrown");
		} catch (IndexOutOfBoundsException e){}
		
		try {
			bs.back(-1); // negative value
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e){}
		
	}

	@Test
	public void testSkip() {
		try {
			bs.skip(-3);
			fail("NegativeArraySizeException not thrown");
		} catch (IllegalArgumentException e) {}
		
		try {
			bs.skip(30);// out of bound
			fail("IndexOutOfBoundsException not thrown");
		} catch (IndexOutOfBoundsException e) {}
	}


	@Test
	public void testJump() {
		try {
			bs.jump(-1);
			fail("IndexOutOfBoundsException not thrown");
		} catch (IndexOutOfBoundsException e) {}
		assertEquals(15, bs.jump(15).position());
	}
//
//	@Test
//	public void testCapacity() {
//		fail("Not yet implemented");
//	}

}
