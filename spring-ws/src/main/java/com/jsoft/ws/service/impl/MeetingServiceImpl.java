package com.jsoft.ws.service.impl;

import com.jsoft.ws.param.UserDTO;
import com.jsoft.ws.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.message.MessageInfo;

@Slf4j
@Service
public class MeetingServiceImpl implements MeetingService {
    @Override
    public MessageInfo list(UserDTO userDTO) {
        return null;
    }
}
