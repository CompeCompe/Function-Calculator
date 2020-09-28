package ru.eremenko.FuncCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FuncCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuncCalculatorApplication.class, args);
		BrowserOpener opener = new BrowserOpener();
		opener.openLinkInBrowser("http://localhost:8080/calc");
	}

}
