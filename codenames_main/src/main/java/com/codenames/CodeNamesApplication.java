package com.codenames;

import com.jjerome.annotations.EnableSockets;
import com.jjerome.annotations.SocketComponentsScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SocketComponentsScan(packages = {"com.codenames"})
@EnableSockets
public class CodeNamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeNamesApplication.class, args);
    }
}
