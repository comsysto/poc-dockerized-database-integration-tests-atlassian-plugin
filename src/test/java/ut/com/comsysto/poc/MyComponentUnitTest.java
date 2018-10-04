package ut.com.comsysto.poc;

import org.junit.Test;
import com.comsysto.poc.api.MyPluginComponent;
import com.comsysto.poc.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}