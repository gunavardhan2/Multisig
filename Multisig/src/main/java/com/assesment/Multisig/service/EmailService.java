package com.assesment.Multisig.service;

import org.springframework.stereotype.Service;

public interface EmailService {
    public void sendNotifiedMessage(String to,String subject,String text);
}
