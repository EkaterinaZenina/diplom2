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
        return "4444 3333 2222 11111";
    }

    public String generateDate(int plusMonth, String formatPattern) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public String getValidDate() {
        int randomNumber = ThreadLocalRandom.current().nextInt(1,60);
        String date = generateDate(randomNumber, "MM.yy");
        return date;
    }

    public String generateName() {
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public String generateCVC() {
        return (faker.numerify("###"));
    }


    public String generateInvalidCVC() {
        return (faker.numerify("####"));
    }

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public CardInfo getValidCard() {
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
}