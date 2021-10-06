package com.dromedian.comunico.bgpoint.cse.service;
import org.springframework.stereotype.Service;

import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.BankClient;


@Service
public interface BankClientService {
	public ResponseModel saveBankClient(BankClient bankclients);
	public ResponseModel updateBankClient(BankClient bankclients);
	public ResponseModel findAllBankClient();
	public ResponseModel findBankclientById(Long userId);
	public ResponseModel deleteBankClientById(Long userId);
	public boolean existsById(Long id);

}
