package com.ccalendar.server.api.controllers;

import com.ccalendar.server.api.data.RegisterUserData;
import com.ccalendar.server.api.data.ResultResponse;
import com.ccalendar.server.api.data.UpdateUserData;
import com.ccalendar.server.api.data.UserData;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.exceptions.UserException;
import com.ccalendar.server.domain.model.User;
import com.ccalendar.server.domain.services.user.UserService;
import com.ccalendar.server.domain.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@Controller
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResultResponse<String> addUser(RegisterUserData user, HttpServletResponse httpResponse) {
        try {
            userService.registerUser(user);
            httpResponse.setStatus(HttpServletResponse.SC_CREATED);
            return new ResultResponse<>(ResultResponse.Status.OK, "success");
        } catch (UserException e) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ResultResponse<>(ResultResponse.Status.ERROR, e.getMessage());
        }
    }

    @GetMapping("/user/details")
    @ResponseBody
    public ResultResponse<UserData> getUserDetails(@AuthenticationPrincipal UserModel userModel) {
        User user = UserConverter.convertToUserDomain(userModel);
        return new ResultResponse<>(UserConverter.convertToUserDTO(user));
    }

    @PutMapping("/user/save")
    @ResponseBody
    public ResultResponse<UserData> saveUserDetails(
            UpdateUserData userParams,
            HttpServletResponse httpResponse
    ) {
        try {
            User savedUser = userService.updateUser(userParams);
            httpResponse.setStatus(SC_OK);
            return new ResultResponse<>(UserConverter.convertToUserDTO(savedUser));
        }
        catch (UserException e){
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ResultResponse<>(ResultResponse.Status.ERROR, e.getMessage());
        }
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
