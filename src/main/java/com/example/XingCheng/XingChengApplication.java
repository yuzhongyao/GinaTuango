package com.example.XingCheng;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Theme("my-theme")
@SpringBootApplication
@ComponentScan(basePackages = "com.example.XingCheng")
public class XingChengApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(XingChengApplication.class, args);
	}

}
