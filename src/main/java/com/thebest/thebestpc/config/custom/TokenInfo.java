package com.thebest.thebestpc.config.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Pipeline;

import java.io.Serializable;
import java.security.Principal;

@Getter
@Setter
@AllArgsConstructor
public class TokenInfo implements Serializable, Principal {
    String username;
    String name;

}
