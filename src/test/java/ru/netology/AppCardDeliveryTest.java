package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }
    private String orderDate(int daysToWait){
        return LocalDate.now().plusDays(daysToWait).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldSendFormTest() {
        $("[data-test-id=city] input").setValue("Ек");
        $$(".menu-item").find(exactText("Екатеринбург")).click();
        $("[data-test-id=date] input").setValue(orderDate(3));
        $("[data-test-id=name] input").setValue("Иванов Андрей");
        $("[data-test-id=phone] input").setValue("+79200000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
