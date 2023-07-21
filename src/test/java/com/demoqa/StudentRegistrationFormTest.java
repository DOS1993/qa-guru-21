package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTest {

    String additionalUrl = "/automation-practice-form", firstName = "Auto", lastName = "Test",
            email = "example@example.com", gender = "Male", phone = "1111111111", subject = "Computer Science",
            hobbies = "Sports", fileName = "pic.png", address = "Autotest address", birthMonth = "March",
            birthYear = "1993", birthDay = "25", state = "NCR", city = "Delhi";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void studentRegistrationFormTest() {
        open(additionalUrl);
        fillStudentInfo();
        checkStudentInfo();
    }

    private void fillStudentInfo() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__day--0" + birthDay + ":not(.react-datepicker__day--outside-month)").click();
        $("#subjectsWrapper #subjectsContainer #subjectsInput").sendKeys(subject);
        $("#subjectsWrapper #subjectsContainer .subjects-auto-complete__option:first-of-type").click();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        $("#uploadPicture").uploadFromClasspath(fileName);
        $("#currentAddress").setValue(address);
        $("#stateCity-wrapper #state").click();
        $("#stateCity-wrapper #state ").$(byText(state)).click();
        $("#stateCity-wrapper #city").click();
        $("#stateCity-wrapper #city").$(byText(city)).click();
        $("#submit").click();
    }

    private void checkStudentInfo() {
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(Condition.text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(Condition.text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(Condition.text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(Condition.text(phone));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(Condition.text(birthDay + " " + birthMonth + "," + birthYear));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(Condition.text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(Condition.text(hobbies));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(Condition.text(fileName));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(Condition.text(address));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(Condition.text(state + " " + city));
    }

}
