package org.oci.task.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @brief Application initializer
 * @author rajeshkurup@live.com
 */
@Component
public class OciTaskServInitializer implements ApplicationListener<ApplicationReadyEvent> {

    public static Logger logger = LoggerFactory.getLogger(OciTaskServInitializer.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        logger.info("OCI Task Service has been initialized...");
    }

}
