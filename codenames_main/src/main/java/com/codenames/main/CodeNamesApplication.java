package com.codenames.main;

import com.jjerome.annotations.SocketComponentsScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SocketComponentsScan(packages = {"com.codenames"})
public class CodeNamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeNamesApplication.class, args);
    }
}
