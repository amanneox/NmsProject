package com.project.nms.controller;


import com.project.nms.exception.ResourceNotFoundException;
import com.project.nms.model.UserModel;
import com.project.nms.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.util.Date;

import java.util.Optional;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    protected UserRepo userRepo;

    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
    public JSONObject login(@RequestBody UserModel login) throws ServletException {

        String jwtToken;

        if (login.getemployeeID() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        String eid = login.getemployeeID();
        String password = login.getPassword();

        UserModel user = userRepo.findByEmployeeID(eid);

        if (user == null) {
            throw new ServletException("User eid not found.");
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }

        jwtToken = Jwts.builder().setSubject(eid).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        JSONObject obj = new JSONObject();
        obj.put("token",jwtToken);
        obj.put("eid",user.getemployeeID());
        obj.put("username",user.getUsername());
        obj.put("name",user.getName());

        return obj;
    }
    @GetMapping("/user")
    public Page<UserModel> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }


    @GetMapping("/user/{userId}")
    public Optional<UserModel> getInvoiceById(@PathVariable Long userId) {
        return userRepo.findById(userId);

    }

    @PostMapping("/user")
    public UserModel createUser(@Valid @RequestBody UserModel user) {
        return userRepo.save(user);
    }

    @PostMapping("/user/update/{userId}")
    public UserModel updateUser(@PathVariable Long userId,
                                @Valid @RequestBody UserModel userRequest) {
        return userRepo.findById(userId)
                .map(user -> {
                    user.setName(userRequest.getName());
                    user.setPassword(userRequest.getPassword());
                    user.setemployeeID(userRequest.getemployeeID());
                    return userRepo.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }


    @PostMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepo.findById(userId)
                .map(user -> {
                    userRepo.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }
}