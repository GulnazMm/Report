package ru.netology.patterns.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.patterns.RegistrationInfo;

import javax.xml.crypto.Data;
import javax.xml.namespace.QName;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.patterns.test.DataGenerator.generateDate;
import static ru.netology.patterns.test.DataGenerator.planDate;

public class CardOrderTest {
    private final int daysToAddForFirstMeeting = 4;
    private final String firstMeetingDate = generateDate(daysToAddForFirstMeeting);

    private static Faker faker;


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
                .shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(10))
                .shouldBe(visible);

    }


}



