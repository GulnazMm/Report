package ru.netology.patterns.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.patterns.RegistrationInfo;

import javax.xml.namespace.QName;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
    private static Faker faker;


    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
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

    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planDate);
        $("[name= 'name']").setValue("Иванова-Петрова Мария");
        $("[name='phone']").setValue("+79174203535");
        $x("// *[@class='checkbox__box']").click();
        $x("// *[text()='Запланировать']").click();
        $("[data-test-id= replan-notification]");
        $x("// *[text () = 'Перепланировать']").click();
        $("[data-test-id=success-notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + planDate), Duration.ofSeconds(10))
                .shouldBe(visible);


    }


}



