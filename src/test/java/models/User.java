package models;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
