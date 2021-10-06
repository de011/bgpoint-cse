package com.dromedian.comunico.bgpoint.cse.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.BankBranch;
import com.dromedian.comunico.bgpoint.cse.service.BankBranchService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/dromedian")
public class BankBranchController {
	private static final Logger LOG = LoggerFactory.getLogger(SmartDeskController.class);
	@Autowired
	private BankBranchService bankBranchService;

	// Save and update bankBranch
	@PostMapping("/save/bankbranch")
	public ResponseEntity<ResponseModel> saveBankBranch(@RequestBody BankBranch bankbranchs) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside saveBankBranch Of Controller with request parameter ", bankbranchs);
		try {
			if(bankbranchs!=null &&  bankbranchs.getId()!=null && bankbranchs.getId()>0) {
				responseModel = bankBranchService.updateBankBranch(bankbranchs);
			}else {
				responseModel = bankBranchService.saveBankBranch(bankbranchs);
			}
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info("BankBranch  created Successfully!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to created  BankBranch");
			e.printStackTrace();
		}
		LOG.info("Exiting saveBankBranch Of Controller with response ", responseEntity);
		return responseEntity;
	}
	
	// Get AllBankBranch By Id
	@GetMapping("/find/bankbranch/byid/{id}")
	public ResponseEntity<ResponseModel> findBankBranchById(@PathVariable("id") Long bankbranchId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findBankBranchById Of Controller with request parameter ");
		try {
			responseModel = bankBranchService.findBankBranchById(bankbranchId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting BankBranch Record Successfully by BankBranchById ");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get BankBranch Record by BankBranchById");
			e.printStackTrace();
		}
		LOG.info("Exiting findBankBranchById Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Get All BankBranch

	@GetMapping("/find/bankbranch")
	public ResponseEntity<ResponseModel> findAllBankBranch() {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findAllBankBranch Of Controller with request parameter ");
		try {
			responseModel = bankBranchService.findAllBankBranch();
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetching All BankBranch Records Successfully!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Fetch BankBranch Records");
			e.printStackTrace();
		}
		LOG.info("Exiting findAllBankBranch Of Controller with response ", responseEntity);
		return responseEntity;
	}


	// BankBranch Search 
	@GetMapping("/search/bankbranch/{searchKeyword}")
	public ResponseEntity<ResponseModel> searchBankBranch(@PathVariable("searchKeyword") String searchKeyword) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside searchBankBranch Of Controller with request parameter ");
		try {
			responseModel = bankBranchService.searchBankBranch(searchKeyword);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting BankBranch Records Successfully! By SearchBankBranch");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get BankBranch Records By SearchBankBranch");
			e.printStackTrace();
		}
		LOG.info("Exiting searchBankBranch Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Delete BankBranch By Id
	@DeleteMapping("/delete/bankbranch/{bankbranchId}")
	public ResponseEntity<ResponseModel> deleteBankBranchById(@PathVariable("bankbranchId") Long bankbranchId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside deleteBankBranchById Of Controller with request parameter ", bankbranchId);
		try {
			responseModel = bankBranchService.deleteBankBranchById(bankbranchId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Deleted BankBranch Record Successfully By BankBranchId!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Delete BankBranch Record By BankBranchId");
			e.printStackTrace();
		}
		LOG.info("Exiting deleteBankBranchById Of Controller with response ", responseEntity);
		return responseEntity;
	}
}
