package ru.netology.patterns.test;

import lombok.experimental.UtilityClass;
import ru.netology.patterns.RegistrationInfo;
import com.github.javafaker.Faker;

import java.util.Locale;

@UtilityClass
public class DataGenerator {


    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.address().city(),
                    faker.date().toString(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}
