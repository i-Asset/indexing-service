package at.srfg.iot.indexing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { 
		SecurityAutoConfiguration.class , 
		ManagementWebSecurityAutoConfiguration.class
		})
@EnableDiscoveryClient
@ComponentScan(basePackages = {
		// scan for components in the following sub-tree
		"at.srfg.iot.indexing",
		// core components (to be reused)
		"at.srfg.iot.common.solr.indexing.core",

		
	})
@RestController
@EnableSwagger2
@EnableAsync
@EnableFeignClients
public class AssetIndexingApplication {
	public static void main(String[] args) {
		SpringApplication.run(AssetIndexingApplication.class, args);
	}

}
