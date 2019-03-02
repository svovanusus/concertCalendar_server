package com.ccalendar.server.api.data;

import com.ccalendar.server.db.model.RegionModel;

public class RegisterUserData {
    private String login;
    private String password;
    private String name;
    private RegionModel userRegion;

    /**_CONSTRUCTORS_**/

    public RegisterUserData() {
    }

    public RegisterUserData(String login, String password, String name, RegionModel userRegion) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.userRegion = userRegion;
    }

    /**_GETTERS_**/

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public RegionModel getUserRegion() {
        return userRegion;
    }

    /**_SETTERS_**/

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserRegion(RegionModel userRegion) {
        this.userRegion = userRegion;
    }
}
