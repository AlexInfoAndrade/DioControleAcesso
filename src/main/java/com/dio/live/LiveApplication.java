package com.dio.live;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiveApplication implements CommandLineRunner {

//    @Autowired
//    private CalendarioService cs;

    public static void main(String[] args) {
        SpringApplication.run(LiveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        cs.carregarFeriados(2021);
//        cs.carregarFeriados(2021, "sp");
    }
}
