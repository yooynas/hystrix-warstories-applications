package com.codecentric.hystrix.warstories;

import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.codecentric.hystrix.warstories.shared.configuration.ArchaiusTestConfiguration;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ArchaiusTestConfiguration.class)
@WebAppConfiguration
public class ConnoteServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void getConfigValue() throws Exception {

        DynamicBooleanProperty archaiusTestProperty =
            DynamicPropertyFactory.getInstance().getBooleanProperty("archaius.test.property", false);

        // if context loads correct, property value = true
        assertTrue(archaiusTestProperty.get());

    }

}
