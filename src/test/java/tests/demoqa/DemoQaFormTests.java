package tests.demoqa;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("owner")
public class DemoQaFormTests extends TestBase {

    @Test
    @Owner("Elizaveta Azovtseva")
    @Feature("Forms")
    @Story("Заполнение формы регистрации отдельными шагами")
    @Feature("Регистрация")
    @DisplayName("Регистрация Пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "demoqa", url = "https://demoqa.com/")
    void fillRegistrationFormTest(){
        step("Открываем страницу регистрации", () -> {
            open("https://demoqa.com/automation-practice-form");
        });
        step("Заполняем имя и фамилию", () -> {
                    $("#firstName").setValue("Lizaveta");
                    $("#lastName").setValue("Popova");
        });
        step("Заполняем email", () -> {
                    $("#userEmail").setValue("petro@mail.ru");
        });
        step("Выбираем пол", () -> {
        $("[for='gender-radio-1']").click();
         });
        step("Заполняем номер телефона", () -> {
        $("#userNumber").setValue("89009000505");
        });
        step("Выбираем дату рождения", () -> {
                    $("#dateOfBirthInput").click();
                    $("[class*='month-select']").selectOptionByValue("10");
                    $("[class*='year-select']").selectOptionByValue("1988");
                    $("[class*='datepicker__day--010']").click();
                });
        step("Заполняем хобби", () -> {
                    $("#subjectsInput").setValue("Arts").pressEnter();
                    $("[for*='hobbies-checkbox-2']").click();
                });
        step("Заполняем адрес", () -> {
                    $("#currentAddress").setValue("Tomsk, Lenina 56-2");
                    $("#react-select-3-input").setValue("Haryana").pressEnter();
                    $("#react-select-4-input").setValue("Karnal").pressEnter();
                });
        step("Отправляем форму", () -> {
                    $("#submit").scrollIntoView(true).click();
                });
        // Assertion
        step("Проверяем отображения модальной формы", () -> {
                    $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
                });

        step("Проверяем правильность заполнения формы", () -> {
            $(".table-responsive").$(byText("Student Name"))
                    .parent().shouldHave(text("Lizaveta Popova"));
            $("#closeLargeModal").should(visible);
        });
    }
}
