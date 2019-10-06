package redis.in.action.learning;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.Jedis;

/**
 * Unit test for simple App.
 */
public class AppTest2 extends TestCase {
	private Jedis conn;
	private Chapter02 c02;
	private String token;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest2(String testName) {
		super(testName);
		conn = new Jedis("192.168.99.100");
		c02 = new Chapter02();
		conn.select(14);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest2.class);
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws InterruptedException
	 */
	public void testAppChapter02Login() throws InterruptedException {
		for (int i = 0; i < 30; i++) {
			c02.testLoginCookies(conn);
		}
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws InterruptedException
	 */
	public void testAppChapter02Shopping() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			c02.testShopppingCartCookies(conn);
		}
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws InterruptedException
	 */
	public void testAppChapter02RowCached() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			c02.testCacheRows(conn);
		}
	}

	public void testCacheRequest() throws InterruptedException {
		c02.testCacheRequest(conn);
	}

}
