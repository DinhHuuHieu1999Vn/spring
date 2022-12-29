package com.vn.spring.config;

import com.vn.spring.model.Users;
import com.vn.spring.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
        return args -> {
            Users users1 = new Users(
                "Terry",
                "Medhurst",
                "male",
                "atuny0@sohu.com",
                "atuny0",
                "9uQFF1Lh",
                LocalDate.of(2000, DECEMBER, 25),
                "https://robohash.org/hicveldicta.png",
                "117.29.86.254",
                "13:69:BA:56:A3:74",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/12.0.702.0 Safari/534.24"
            );
            Users users2 = new Users(
                "Sheldon",
                "Quigley",
                "male",
                "hbingley1@plala.or.jp",
                "hbingley1",
                "CQutx25i8r",
                LocalDate.of(2003, AUGUST, 02),
                "https://robohash.org/doloremquesintcorrupti.png",
                "253.240.20.181",
                "13:F1:00:DA:A4:12",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/534.30 (KHTML, like Gecko) Ubuntu/11.04 Chromium/12.0.742.112 Chrome/12.0.742.112 Safari/534.30"
            );
            usersRepository.saveAll(List.of(
                users1, users2
            ));
        };
    }
}
