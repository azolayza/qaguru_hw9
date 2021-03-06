package tests.demoqa;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class DemoQaFormTests extends TestBase {

    @Owner("AzovcevaE")
    @Feature("Registration")
    @Story("Регистрация")
    @DisplayName("Регистрация Пользователя")
    @Test
    void fillDataFormTest(){
        step("Открываем страницу регистрации", () -> {
            open("/automation-practice-form");
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
        //File file = new File("/Users/admin/Documents/study_projects/qaguru_hw9/src/test/resources/b2b.jpeg");
        //$("[class*='form-control-file']").uploadFile(file);
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
