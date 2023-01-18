package org.oci.task;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @brief Entry point for ocitaskserv (OCI Task Service).
 *      A springboot java service that hosts REST APIs to manage and persist Tasks.
 * @author rajeshkurup@live.com
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "OCI Task Service APIs", version = "1.0", description = "OCI Task Persistence and Management"))
public class OciTaskServ {

    /**
     * @brief Start Spring Boot application.
     * @param args Commandline arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(OciTaskServ.class, args);
    }

}
