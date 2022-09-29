package ru.netology.patterns.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.patterns.test.DataGenerator.generateDate;



public class CardOrderTest {
    private final int daysToAddForFirstMeeting = 4;
    private final String firstMeetingDate = generateDate(daysToAddForFirstMeeting);
    RegistrationInfo registrationInfo = DataGenerator.Registration.generateByCard("ru");




    @Test
    public void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue(registrationInfo.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[name= 'name']").setValue(registrationInfo.getName());
        $("[name='phone']").setValue(registrationInfo.getPhone());
        $x("// *[@class='checkbox__box']").click();
        $x("// *[text()='Запланировать']").click();
        $("[data-test-id ='success-notification']").
                shouldHave(Condition.text("Успешно! Встреча успешно запланирована на" + firstMeetingDate),
                Duration.ofSeconds(15)).shouldBe(visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $x("// *[text()='Запланировать']").click();
        $("[data-test-id= 'replan-notification']").shouldHave(Condition.text("Необходимо подтверждение У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(byText("Перепланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.text("Успешно! Встреча успешно запланирована на" + firstMeetingDate),
                        Duration.ofSeconds(15)).shouldBe(visible);

    }


}



