import controllers.UserController;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.AddUserResponse;
import models.GetUserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static testData.TestData.DEFAULT_USER;
import static testData.TestData.INVALID_USER;

public class SmokeApiTests {
    UserController userController = new UserController();

    @Story("API test")
    @Tag("api")
    @Test
    void createUserControllerTest() {
        Response response = userController.createUser(DEFAULT_USER);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }

    @Tag("api")
    @Test
    void updateUserControllerTest(){
        userController.createUser(DEFAULT_USER);
        Response response = userController.updateUser(DEFAULT_USER);
        AddUserResponse updateUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, updateUserResponse.getCode());
        Assertions.assertEquals("unknown", updateUserResponse.getType());
        Assertions.assertFalse(updateUserResponse.getMessage().isEmpty());
    }

    @Tag("api")
    @Test
    void deleteUserControllerTest(){
        userController.createUser(DEFAULT_USER);
        String userName = DEFAULT_USER.getUsername();
        Response response = userController.deleteUser(userName);
        AddUserResponse deleteUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("unknown", deleteUserResponse.getType());
        Assertions.assertEquals(userName, deleteUserResponse.getMessage());

    }

    @Tag("api")
    @Test
    void getUserByNameController(){
        userController.createUser(DEFAULT_USER);
        String userName = DEFAULT_USER.getUsername();

        Response response = userController.getUserByUserName(userName);
        GetUserResponse getByUserNameResponse = response.as(GetUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(userName, getByUserNameResponse.getUsername());
    }

    @Tag("api")
    @Test
    void createInvalidUser(){
        Response response = userController.createUser(INVALID_USER);
        AddUserResponse addInvalidUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("0", addInvalidUserResponse.getMessage());
    }

}

