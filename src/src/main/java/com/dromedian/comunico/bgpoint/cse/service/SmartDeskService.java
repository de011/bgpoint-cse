package com.dromedian.comunico.bgpoint.cse.service;
import org.springframework.stereotype.Service;
import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.SmartDesk;

@Service
public interface SmartDeskService {
	public ResponseModel saveSmartDesk(SmartDesk smartDesk);
	public ResponseModel updateSmartDesk(SmartDesk smartDesk);
	public ResponseModel findAllSmartDesk();
	public ResponseModel findSmartDeskById(Long idPostazione);
	public ResponseModel deleteSmartDeskById(Long smartDeskId);
	//public ResponseModel  getSmartDeskByBranchId(Long branchId);
	public boolean existsById(Long id);
	public ResponseModel searchSmartDesk(String searchKeyword);
	
	public ResponseModel getSmartDeskByEntityId(String entity, Long entityId);
	
}