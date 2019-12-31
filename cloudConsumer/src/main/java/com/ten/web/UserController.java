package com.ten.web;

import com.ten.client.UserClient;
import com.ten.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result queryUserById(@PathVariable String id) {
        Result result = this.userClient.findById(id);
        return result;
    }
}
