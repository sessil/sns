package com.fastcampus.snsproject.controller.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinRequest {

    private String name;
    private String password;
}
