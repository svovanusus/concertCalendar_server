package com.ccalendar.server.api.controllers;

import com.ccalendar.server.api.data.ResultResponse;
import com.ccalendar.server.db.model.UserModel;
import com.ccalendar.server.domain.exceptions.UserException;
import com.ccalendar.server.domain.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResultResponse<String> addUser(UserModel userModel, HttpServletResponse httpResponse){
        try {
            userService.saveUser(userModel, true);
            httpResponse.setStatus(HttpServletResponse.SC_CREATED);
            return new ResultResponse<>(ResultResponse.Status.OK, "success");
        } catch (UserException e) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ResultResponse<>(ResultResponse.Status.ERROR, e.getMessage());
        }
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
