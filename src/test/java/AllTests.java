import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class AllTests {
    private SelenideElement nameField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvcForm = $(".form-field:nth-child(3) .input-group__input-case:last-child .input__control");

    DataHelper data = new DataHelper();

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
        data.clickCreditButton();
    }


    @org.junit.jupiter.api.Test
    public void shouldStart() {
        data.getValidName();
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(5);
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldCheckValidName() {
        nameField.setValue("Elena Dolgova");
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(5);
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
    }
    @Test
    public void shouldCheckInvalidName() {
        nameField.setValue("123 1234");
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(5);
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
    }


    @Test
    public void shouldCheckApprovedCard() {
        data.getApprovedCard();
        data.getMonth(0);
        data.getYear(5);
        data.getValidName();
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
    }

    @Test
    public void shouldCheckDeclinedCard() {
        data.getDeclinedCard();
        data.getMonth(0);
        data.getYear(0);
        data.getValidName();
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
    }
    @Test
    public void shouldValidCVC() {
        data.getDeclinedCard();
        data.getMonth(0);
        data.getYear(0);
        data.getValidName();
        data.getValidCVC();
        data.clickContinueButton();
        data.getSuccessNotification();
    }
    @Test
    public void shouldInvalidCVC() {
        data.getDeclinedCard();
        data.getMonth(0);
        data.getYear(0);
        data.getValidName();
        cvcForm.setValue("1234");
        data.clickContinueButton();
        data.getSuccessNotification();
    }
        @Test
        public void ShouldSpaceInCVC() {
            data.getApprovedCard();
            data.getMonth(0);
            data.getYear(1);
            data.getValidName();
            cvcForm.setValue(" ");
            data.clickContinueButton();
            data.getCVCErrorNotification();
        }

        @Test
        public void shouldEmptyForm() {
            data.clickContinueButton();
            data.getCardErrorNotification();
            data.getMonthFormatErrorNotification();
            data.getYearFormatErrorNotification();
            data.getNameErrorNotification();
            data.getCVCErrorNotification();
        }
    }

