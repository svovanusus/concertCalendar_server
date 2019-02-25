package com.ccalendar.server.api.data;

public class LoginUserData {
    private String login;
    private String password;

    /**_CONSTRUCTORS_**/

    public LoginUserData() {
    }

    public LoginUserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**_GETTERS_**/

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    /**_SETTERS_**/

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
