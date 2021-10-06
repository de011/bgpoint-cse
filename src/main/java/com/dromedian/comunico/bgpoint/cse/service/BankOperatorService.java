package com.dromedian.comunico.bgpoint.cse.service;

import org.springframework.stereotype.Service;

import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.BankOperator;

@Service
public interface BankOperatorService {
	public ResponseModel updateBankOperator(BankOperator bankoperators);
	public ResponseModel saveBankOperator(BankOperator bankoperators);
	public ResponseModel findAllBankOperator();
	public ResponseModel findBankOperatorId(Long bankoperatorId);
	public ResponseModel deleteBankOperatorById(Long bankoperatorId);
	public boolean existsById(Long id);
	
}
