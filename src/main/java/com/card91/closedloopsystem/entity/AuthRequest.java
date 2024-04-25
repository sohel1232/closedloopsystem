package com.card91.closedloopsystem.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthRequest implements Serializable {
    private String otp;
    private String phoneNo;
}
