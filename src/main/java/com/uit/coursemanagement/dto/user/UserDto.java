package com.uit.coursemanagement.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.coursemanagement.dto.response.FileCaption;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    private String phone;

    private FileCaption avatar;

    public UserDto(Long id, String username, FileCaption avatar, String email) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
    }
}
