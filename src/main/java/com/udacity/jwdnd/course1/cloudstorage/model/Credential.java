package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String credential_Key;
    private String credential_Password;
    private Integer userId;
}
