/*
 * (c) Midland Software Limited 2025
 * Name     : User.java
 * Author   : RoodbarakyK
 * Date     : 21 May 2025
 */

public class User {
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private final String email;
    @StrongPassword
    private String password;

    public User(String Email, String Password) {
        email = Email;
        password = Password;
    }

}
