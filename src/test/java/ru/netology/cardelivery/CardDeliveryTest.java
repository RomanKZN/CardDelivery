package ru.netology.cardelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void mustSubmitTheForm() {
        Configuration.holdBrowserOpen = true;
        String planningDate = generateDate(5);

        open("http://localhost:9999/");
        $(By.cssSelector("[data-test-id=city] input")).setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("div [data-test-id='date'] input").setValue(planningDate);
        $(By.cssSelector("[data-test-id=name] input")).setValue("Иванов Иван");
        $(By.cssSelector("[data-test-id=phone] input")).setValue("+79999998888");
        $(By.className("checkbox__box")).click();
        $(By.className("button__text")).click();
        $x("//*[contains(text(),'Успешно')]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }


}


