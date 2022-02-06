package tests.demoqa;

import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaFormTests extends TestBase {
    @Test
    void fillDataFormTest(){
        open("/automation-practice-form");
        $("#firstName").setValue("Lizaveta");
        $("#lastName").setValue("Popova");
        $("#userEmail").setValue("petro@mail.ru");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("89009000505");
        $("#dateOfBirthInput").click();
        $("[class*='month-select']").selectOptionByValue("10");
        $("[class*='year-select']").selectOptionByValue("1988");
        $("[class*='datepicker__day--010']").click();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("[for*='hobbies-checkbox-2']").click();
        //File file = new File("/Users/admin/Documents/study_projects/qaguru_hw9/src/test/resources/b2b.jpeg");
        //$("[class*='form-control-file']").uploadFile(file);
        $("#currentAddress").setValue("Tomsk, Lenina 56-2");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").scrollIntoView(true).click();;

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name"))
                .parent().shouldHave(text("Lizaveta Popova"));
        $("#closeLargeModal").should(visible);
    }
}
