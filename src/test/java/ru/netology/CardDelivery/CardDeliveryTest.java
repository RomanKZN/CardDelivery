package ru.netology.CardDelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    @Test
    void mustSubmitTheForm() {
        Configuration.holdBrowserOpen = true;
        Date currentDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 3);
        String newCurrentDate = ft.format(c.getTime());

        open("http://localhost:9999/");
        $(By.cssSelector("[data-test-id=city] input")).setValue("Казань");
        $(By.cssSelector("[data-test-id=date] input")).sendKeys(Keys.BACK_SPACE);
        $("div [data-test-id='date'] input").setValue(newCurrentDate);
        $(By.cssSelector("[data-test-id=name] input")).setValue("Иванов Иван");
        $(By.cssSelector("[data-test-id=phone] input")).setValue("+79999998888");
        $(By.className("checkbox__box")).click();
        $(By.className("button__text")).click();
        $x("//*[contains(text(),'Успешно')]").shouldBe(Condition.visible, Duration.ofSeconds(14));
    }
}
