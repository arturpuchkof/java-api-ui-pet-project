import controllers.UserController;
import io.restassured.response.Response;
import models.AddUserResponse;
import models.GetUserResponse;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SmokeApiTests {
    UserController userController = new UserController();

    @Test
    void createUserControllerTest() {
        User userBuilder = User.builder()
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phone("password")
                .userStatus(0)
                .build();

        Response response = userController.createUser(userBuilder);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }

    @Test
    void updateUserControllerTest(){
       User userBuilder = User.builder()
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phone("password")
                .userStatus(0)
                .build();

        userController.createUser(userBuilder);
        Response response = userController.updateUser(userBuilder);
        AddUserResponse updateUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, updateUserResponse.getCode());
        Assertions.assertEquals("unknown", updateUserResponse.getType());
        Assertions.assertFalse(updateUserResponse.getMessage().isEmpty());
    }

    @Test
    void deleteUserControllerTest(){
        User userBuilder = User.builder()
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phone("password")
                .userStatus(0)
                .build();

        userController.createUser(userBuilder);
        String userName = userBuilder.getUsername();
        Response response = userController.deleteUser(userName);
        AddUserResponse deleteUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("unknown", deleteUserResponse.getType());
        Assertions.assertEquals(userName, deleteUserResponse.getMessage());

    }

    @Test
    void getUserByNameController(){
        User userBuilder = User.builder()
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phone("password")
                .userStatus(0)
                .build();

        userController.createUser(userBuilder);
        String userName = userBuilder.getUsername();

        Response response = userController.getUserByUserName(userName);
        GetUserResponse getByUserNameResponse = response.as(GetUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(userName, getByUserNameResponse.getUsername());
    }

}

