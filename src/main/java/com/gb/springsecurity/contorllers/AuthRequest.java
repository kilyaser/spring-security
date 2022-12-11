package com.gb.springsecurity.contorllers;

import lombok.Getter;

@Getter
public class AuthRequest {
    private String username;
    private String password;
}
