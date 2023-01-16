package org.oci.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @brief Entry point for ocitaskserv (OCI Task Service).
 *      A springboot java service that hosts REST APIs to manage and persist Tasks.
 * @author rajeshkurup@live.com
 */
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class OciTaskServ {

    /**
     * @brief Start springboot application.
     * @param args Commandline arguments
     */
    public static void main(String[] args) {

        SpringApplication.run(OciTaskServ.class, args);

    }

}
