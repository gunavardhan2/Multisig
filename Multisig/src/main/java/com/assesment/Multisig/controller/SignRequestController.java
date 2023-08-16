package com.assesment.Multisig.controller;


import com.assesment.Multisig.dto.JsonRequest;
import com.assesment.Multisig.dto.SignRequestDto;
import com.assesment.Multisig.dto.SigningRequestDto;
import com.assesment.Multisig.model.Transaction;
import com.assesment.Multisig.service.SignOffService;
import com.assesment.Multisig.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SignRequestController {
    @Autowired
    SignOffService signOffService;
    @Autowired
    TransactionService transactionService;
    @PostMapping("/create_sign_request")
    public void createSignRequest(@RequestBody SignRequestDto signRequestDto)
    {
        System.out.println("fetching all users");
        Long transactionId = transactionService.createTransaction(signRequestDto.getCreatorId());
        System.out.println("transaction inserted");
        signOffService.sendRequest(transactionId,signRequestDto.getCreatorId(),signRequestDto.getUserIds(),signRequestDto.getVisible());


    }

    @PutMapping("/signing_request")
    public void completeSignRequest(@RequestPart("data") JsonRequest json, @RequestPart("file") MultipartFile file) throws IOException {
        System.out.println("signing off");
        if(file.isEmpty())
        {
            System.out.println("image is mandatory");
        }
        else {
            SigningRequestDto signingRequestDto = new SigningRequestDto();
            signingRequestDto.setImage(file);
            signingRequestDto.setTransactionId(json.getTransactionId());
            signingRequestDto.setUserId(json.getUserId());
            signingRequestDto.setComment(json.getComment());
            signingRequestDto.setAccept(json.getAccept());
            signOffService.completeSignRequest(signingRequestDto);
        }
    }
}
