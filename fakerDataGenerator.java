import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class fakerDataGenerator {

    @Test
    public void generateData() {
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String firstname = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().safeEmailAddress();
        System.out.println("full name: " + fullName);
        System.out.println("first name: " + firstname);
        System.out.println("password: " + password);
        System.out.println("email: " + email);
    }
}
