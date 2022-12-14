package com.project.pt.dto.usersync;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserSyncDTO implements Serializable {

    private List<UserSyncInfoDTO> data;

}
