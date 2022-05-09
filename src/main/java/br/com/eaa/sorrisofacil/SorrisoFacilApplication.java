package br.com.eaa.sorrisofacil;

import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SorrisoFacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(SorrisoFacilApplication.class, args);
	}

}
