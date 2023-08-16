package com.assesment.Multisig;

//import controller.userController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@ComponentScan(basePackageClasses = userController.class)
public class MultisigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultisigApplication.class, args);
	}

}
