package com.assesment.Multisig.service;

import com.assesment.Multisig.dto.MailRequestDto;
import com.assesment.Multisig.dto.SigningRequestDto;
import com.assesment.Multisig.model.SignRequest;
import com.assesment.Multisig.repository.SignRequestRepository;
import com.assesment.Multisig.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Transactional
@Service
public class SignOffService {
    @Autowired
    SignRequestRepository signRequestRepository;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionService transactionService;

    public void sendRequest(Long transactionId,Long creatorId,List<Long> userIds,List<Boolean> visible) {
        for(int i=0;i<userIds.size();i++)
        {
            SignRequest signRequest = new SignRequest();
            signRequest.setTransactionId(transactionId);
            signRequest.setCreatorId(creatorId);
            signRequest.setUserId(userIds.get(i));
            signRequest.setVisible(visible.get(i));
            //visibility of comments done by prime user or signned user
            signRequestRepository.save(signRequest);
            String mail = userRepository.fetchUsermailById(userIds.get(i));
            System.out.println("line 31 fetch by id done ");
            MailRequestDto mailRequestDto = new MailRequestDto();
            mailRequestDto.setTo(mail);
            mailRequestDto.setSubject("New Request");
            mailRequestDto.setText("Approve Request");
            emailService.sendNotifiedMessage(mailRequestDto.getTo(),mailRequestDto.getSubject(),mailRequestDto.getText());
        }
    }

    public void completeSignRequest(SigningRequestDto signingRequestDto) throws IOException {
        SignRequest signRequest = new SignRequest();
        signRequest = signRequestRepository.fetchSignDetails(signingRequestDto.getUserId(), signingRequestDto.getTransactionId());
        signRequest.setComment(signingRequestDto.getComment());
        signRequest.setApproved(signingRequestDto.getAccept());
        signRequest.setData(signingRequestDto.getImage().getBytes());
        signRequestRepository.save(signRequest);
        String mail = userRepository.fetchUsermailById(signRequest.getCreatorId());
        MailRequestDto mailRequestDto = new MailRequestDto();
        mailRequestDto.setTo(mail);
        mailRequestDto.setSubject("Request completed by");
        mailRequestDto.setText("Request approved by");
        emailService.sendNotifiedMessage(mailRequestDto.getTo(),mailRequestDto.getSubject(),mailRequestDto.getText());
        Boolean allAccepted = checkAllAccepted(signingRequestDto.getTransactionId());
        if(allAccepted)
        {
            System.out.println("all have accepted ");
            transactionService.updateTransaction(signingRequestDto.getTransactionId());
        }


    }

    public Boolean checkAllAccepted(Long transactionId)
    {
        List<SignRequest> signRequestList = signRequestRepository.fetchAllSignRequestById(transactionId);
        for(int i=0;i<signRequestList.size();i++)
        {
            if(signRequestList.get(i).getApproved()==null || signRequestList.get(i).getApproved()==false)
                return false;
        }
        for(int i=0;i<signRequestList.size();i++)
        {
            String mail = userRepository.fetchUsermailById(signRequestList.get(i).getUserId());
            MailRequestDto mailRequestDto = new MailRequestDto();
            mailRequestDto.setTo(mail);
            mailRequestDto.setSubject("all Request done");
            mailRequestDto.setText("all Request approved");
            emailService.sendNotifiedMessage(mailRequestDto.getTo(),mailRequestDto.getSubject(),mailRequestDto.getText());

        }

        return true;
    }
}
