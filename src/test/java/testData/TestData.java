package testData;
import com.github.javafaker.Faker;
import models.User;

import java.util.Random;

public class TestData {
    static Faker faker = new Faker();
    static Random ran = new Random();
    static int randomId = ran.nextInt(999999999);
    static int randomUserStatus = ran.nextInt(3);
    public static final User DEFAULT_USER = User.builder()
            .id(randomId)
            .username(faker.name().firstName())
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .email(faker.internet().emailAddress())
            .phone(faker.phoneNumber().phoneNumber())
            .password(faker.internet().password())
            .userStatus(randomUserStatus)
            .build();


    public static final User INVALID_USER = User.builder().build();
}
