package Entity;

import junit.framework.TestCase;
import entity.Subscription;

public class SubscriptionTest extends TestCase {

    private Subscription subscription;


    public void setUp() {
        subscription = new Subscription("Basic", 3600); // 1 hour in seconds
    }


    public void testGetLevel() {
        assertEquals("Basic", subscription.getLevel());
    }


    public void testGetTime() {
        assertEquals(3600, subscription.getTime());
    }


    public void testSetLevel() {
        subscription.setLevel("Premium");
        assertEquals("Premium", subscription.getLevel());
    }

    public void testSetTime() {
        subscription.setTime(7200); // 2 hours in seconds
        assertEquals(7200, subscription.getTime());
    }
}