package com.sb.talktalk.frontend;

import com.vaadin.flow.component.page.Push;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Main extends SpringBootServletInitializer{
    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
