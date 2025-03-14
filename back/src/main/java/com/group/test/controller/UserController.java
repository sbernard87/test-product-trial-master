package com.group.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.test.api.AccountApi;
import com.group.test.api.TokenApi;
import com.group.test.model.UserAccountDTO;
import com.group.test.model.UserLoginDTO;
import com.group.test.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController implements AccountApi, TokenApi {

    private UserService userService;

    private TokenService tokenService;

    @Override
    @Operation(operationId = "createUserAccount", summary = "Create a new user account", responses = {
            @ApiResponse(responseCode = "201", description = "User account created") })
    @PostMapping(value = "/account", consumes = { "application/json" })
    public ResponseEntity<Void> createUserAccount(
            @Parameter(name = "UserAccountDTO", description = "", required = true) @Valid @RequestBody final UserAccountDTO userAccountDTO) {
        userService.createUser(userAccountDTO);
        return ResponseEntity.status(201).build();
    }

    @Override
    @Operation(operationId = "connectUser", summary = "Authenticate user", responses = {
            @ApiResponse(responseCode = "200", description = "Authentication successful") })
    @PostMapping(value = "/token", consumes = { "application/json" })
    public ResponseEntity<Void> connectUser(
            @Parameter(name = "UserLoginDTO", description = "", required = true) @Valid @RequestBody final UserLoginDTO userLoginDTO) {
        // generate token
        return ResponseEntity.status(200).build();

    }
}