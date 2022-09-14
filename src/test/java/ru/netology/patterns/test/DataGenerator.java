package ru.netology.patterns.test;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.patterns.RegistrationInfo;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {
    private static Faker faker;

    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }


    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.address().city(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }
    }

    @Test
    public void generateDateWithUtils() {
        RegistrationInfo info = DataGenerator
                .Registration
                .generateByCard("ru");
        System.out.println();
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    String planDate = generateDate(3);

}
