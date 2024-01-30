package com.jsoft.ws.service;

import com.jsoft.ws.param.UserDTO;

import javax.security.auth.message.MessageInfo;

public interface MeetingService {


    MessageInfo list(UserDTO userDTO);
}
