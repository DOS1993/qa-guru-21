package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    String additionalUrl = "/automation-practice-form", firstName = "Auto", lastName = "Test",
            email = "example@example.com", gender = "Male", phone = "1111111111", subject = "Computer Science",
            hobbies = "Sports", filePath = "src/test/resources/pic.png", address = "Autotest address";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void studentRegistrationFormTest() {
        open(additionalUrl);
        fillAndSubmitStudentInfo();
        checkStudentInfo();
    }

    private void fillAndSubmitStudentInfo() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select [value=\"2\"]").click();
        $(".react-datepicker__year-select [value=\"1993\"]").click();
        $(".react-datepicker__month [aria-label=\"Choose Thursday, March 25th, 1993\"]").click();
        $("#subjectsWrapper #subjectsContainer #subjectsInput").sendKeys(subject);
        $("#subjectsWrapper #subjectsContainer .subjects-auto-complete__option:first-of-type").click();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        $("#uploadPicture").uploadFile(new File(filePath));
        $("#currentAddress").setValue(address);
        $("#stateCity-wrapper #state").click();
        $("#stateCity-wrapper #state #react-select-3-option-0").click();
        $("#stateCity-wrapper #city").click();
        $("#stateCity-wrapper #city #react-select-4-option-0").click();
        $("#submit").click();
    }

    private void checkStudentInfo() {
        $(".table-responsive tbody tr:nth-of-type(1)").shouldHave(Condition.text(firstName + " " + lastName));
        $(".table-responsive tbody tr:nth-of-type(2)").shouldHave(Condition.text(email));
        $(".table-responsive tbody tr:nth-of-type(3)").shouldHave(Condition.text(gender));
        $(".table-responsive tbody tr:nth-of-type(4)").shouldHave(Condition.text(phone));
        $(".table-responsive tbody tr:nth-of-type(5)").shouldHave(Condition.text("25 March,1993"));
        $(".table-responsive tbody tr:nth-of-type(6)").shouldHave(Condition.text(subject));
        $(".table-responsive tbody tr:nth-of-type(7)").shouldHave(Condition.text(hobbies));
        $(".table-responsive tbody tr:nth-of-type(8)").shouldHave(Condition.text(
                filePath.substring(filePath.lastIndexOf('/') + 1)));
        $(".table-responsive tbody tr:nth-of-type(9)").shouldHave(Condition.text(address));
        $(".table-responsive tbody tr:nth-of-type(10)").shouldHave(Condition.text("NCR Delhi"));
    }
}
