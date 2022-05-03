import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {

    private Faker faker = new Faker();

    private String getValidCardNumber() {
        return "1111 2222 3333 4444";
    }

    private String getInvalidCardNumber() {
        return "5555 6666 7777 8888";
    }

    public String generateName() {
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public String generateDate(int plusMonth, String formatPattern) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public String getValidDate() {
        int randomNumber = ThreadLocalRandom.current().nextInt(1,60);
        String date = generateDate(randomNumber, "MM.yy");
        return date;
    }

    public String generateCVC() {
        return (faker.numerify("###"));
    }



    public String generateCVC4Numbers() {
        return (faker.numerify("####"));
    }

    public String generateCVC2Numbers() {
        return (faker.numerify("##"));
    }
    public String generateCVCWithoutNumbers() {
        return (faker.numerify(""));
    }

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public CardInfo getValidCardInfo() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateName(), generateCVC());
    }

    public CardInfo getInfoEmptyYear() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "", generateName(), generateCVC());
    }

    public CardInfo getInfoEmptyMonth() {
        return new CardInfo(getValidCardNumber(), "", getValidDate().substring(3), generateName(), generateCVC());
    }

    public CardInfo getInfoEmptyName() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "", generateCVC());
    }

    public CardInfo getInfoEmptyCVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateName(), "");
    }


    public CardInfo getInfoNameInCyrillic() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), "Иванов Иван", generateCVC());
    }

    public CardInfo getInfoCurrentMonth() {
        return new CardInfo(getValidCardNumber(), generateDate(0, "MM"), generateDate(0, "yy"), generateName(), generateCVC());
    }

    public CardInfo getInfoNegativeNumberYear() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), "-" + getValidDate().substring(3), generateName(), generateCVC());
    }

    public CardInfo getInfoWith4CVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateName(), generateCVC4Numbers());
    }
    public CardInfo getInfoWith2CVC() {
        return new CardInfo(getValidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateName(), generateCVC2Numbers());
    }

    public CardInfo getInvalidCardInfo() {
        return new CardInfo(getInvalidCardNumber(), getValidDate().substring(0, 2), getValidDate().substring(3), generateName(), generateCVC());
    }


}
