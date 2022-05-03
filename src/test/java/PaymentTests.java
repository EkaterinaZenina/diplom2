import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentTests {
    private SelenideElement nameField = $(".form-field:nth-child(3) .input-group__input-case:first-child .input__control");
    private SelenideElement cvcField = $(".form-field:nth-child(3) .input-group__input-case:last-child .input__control");
    DataHelper data = new DataHelper();

    @BeforeAll
    static void setupAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
        @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        data.clickPaymentButton();
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @Test
    public void shouldPayByApprovedCard() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
        assertNotNull(DBHelper.paymentStatus());
    }



    @Test
    public void shouldNameTwoLetters() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(1);
        nameField.setValue("A B");
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
        assertNotNull(DBHelper.paymentStatus());
    }

    @Test
    public void shouldWithNewCard() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(1);
        data.getValidName();
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
        assertNotNull(DBHelper.paymentStatus());
    }

    @Test
    public void shouldOldCard() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(0);
        data.getValidName();
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
        assertNotNull(DBHelper.paymentStatus());
    }

    @Test
    public void shouldFourNumbersCVCForm() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        cvcField.setValue("0000");
        data.clickContinueButton();
        data.getSuccessNotification();
        assertNotNull(DBHelper.paymentStatus());
    }
    
    @Test
    public void NameWithCyrillic() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(1);
        nameField.setValue("Евгения Пономарева");
        data.getValidCVC();
        data.clickContinueButton();
        data.getNameErrorNotification();
    }

    @Test
    public void NumbersInTheNameField() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(2);
        nameField.setValue("12345");
        data.getValidCVC();
        data.clickPaymentButton();
        data.getNameErrorNotification();
    }

    @Test
    public void DeclinedCard() {
        data.getDeclinedCard();
        data.getMonth(0);
        data.getYear(2);
        data.getValidName();
        data.getValidCVC();
        data.clickContinueButton();
        data.getErrorNotification();
        assertNotNull(DBHelper.paymentStatus());
    }


}
