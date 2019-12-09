package redis.in.action.learning;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.Jedis;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAppChapter01Vote()
    {
    	Chapter01 c01 = new Chapter01();
    	Jedis conn = new Jedis("192.168.99.100");
        conn.select(15);
        String user = RandomStringUtils.randomAlphabetic(8);
        
        c01.articleVote(conn, user, "article:" + "1");
        c01.articleVote(conn, user, "article:" + "3");
        c01.articleVote(conn, user, "article:" + "7");
    }
    
    public void testAppChapter01GetArticle()
    {
    	Chapter01 c01 = new Chapter01();
    	Jedis conn = new Jedis("192.168.99.100");
        conn.select(15);
        List<Map<String,String>> articles = c01.getArticles(conn, 1);
        c01.printArticles(articles);
        
    }
}
