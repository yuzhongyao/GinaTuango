package com.example.ginatuango;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Theme("my-theme")
@SpringBootApplication
//@ComponentScan(basePackages = "com.example.ginatuango")
public class GinaTuangoApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(GinaTuangoApplication.class, args);
	}

}
