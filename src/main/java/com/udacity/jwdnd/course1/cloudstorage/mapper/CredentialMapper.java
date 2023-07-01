package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS (url, username, credential_key, credential_password, userid) " +
            "VALUES(#{url}, #{username}, #{credential_Key}, #{credential_Password}, #{userId})")
    int addCredential(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    Credential[] getCredentials(Integer userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    int deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, credential_password = #{credential_Password} WHERE credentialid = #{credentialId}")
    int updateCredential(Credential credential);

    @Select("SELECT credential_key FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    String getKey(Integer credentialId);
}
