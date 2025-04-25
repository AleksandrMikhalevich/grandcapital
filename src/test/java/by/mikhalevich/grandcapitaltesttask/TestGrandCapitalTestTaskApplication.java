package by.mikhalevich.grandcapitaltesttask;

import org.springframework.boot.SpringApplication;

public class TestGrandCapitalTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.from(GrandCapitalTestTaskApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
