package com.dromedian.comunico.bgpoint.cse.service;
import org.springframework.stereotype.Service;
import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.BankBranch;

@Service
public interface BankBranchService {
	public ResponseModel saveBankBranch(BankBranch bankbranch);
	public ResponseModel updateBankBranch(BankBranch bankbranch);
	public ResponseModel findAllBankBranch();
	public ResponseModel findBankBranchById(Long bankbranchId);
	public ResponseModel deleteBankBranchById(Long bankbranchId);
	public boolean existsById(Long id);
	public ResponseModel searchBankBranch(String searchKeyword);
}
