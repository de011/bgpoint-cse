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
import com.dromedian.comunico.bgpoint.cse.entity.BankOperator;
import com.dromedian.comunico.bgpoint.cse.service.BankOperatorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/dromedian")
public class BankOperatorController {
	private static final Logger LOG = LoggerFactory.getLogger(SmartDeskController.class);

	@Autowired
	private BankOperatorService bankOperatorService;

	// Save and update BankOperator
	@PostMapping("/save/bankoperator")
	public ResponseEntity<ResponseModel> saveBankOperator(@RequestBody BankOperator bankoperators) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside saveBankOperator Of Controller with request parameter ", bankoperators);
		try {
			if (bankoperators != null && bankoperators.getId() != null && bankoperators.getId() > 0) {
				responseModel = bankOperatorService.updateBankOperator(bankoperators);
			} else {
				responseModel = bankOperatorService.saveBankOperator(bankoperators);
			}
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info("BankOperator  created Successfully!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Save the BankOperator");
			e.printStackTrace();
		}
		LOG.info("Exiting saveBankOperator Of Controller with response ", responseEntity);
		return responseEntity;

	}

	// Find BankOperator By Id
	@GetMapping("/find/bankoperator/byid/{id}")
	public ResponseEntity<ResponseModel> findBankOperatorById(@PathVariable("id") Long bankoperatorId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findBankOperatorById Of Controller with request parameter ");
		try {
			responseModel = bankOperatorService.findBankOperatorId(bankoperatorId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting BankOperator Successfully by findBankOperatorById ");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get BankOperator by findBankOperatorById");
			e.printStackTrace();
		}
		LOG.info("Exiting findBankOperatorById Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// find All BankOperator
	@GetMapping("/find/bankoperator")
	public ResponseEntity<ResponseModel> findAllBankOperator() {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findAllBankOperator Of Controller with request parameter ");
		try {
			responseModel = bankOperatorService.findAllBankOperator();
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetching All BankOperators Successfully!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Fetch BankOperator ");
			e.printStackTrace();
		}
		LOG.info("Exiting findAllBankOperator Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Delete BankOperator By Id
	@DeleteMapping("/delete/bankoperator/{bankoperatorId}")
	public ResponseEntity<ResponseModel> deleteBankOperatorById(@PathVariable("bankoperatorId") Long bankoperatorId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside deleteBankOperatorById Of Controller with request parameter ", bankoperatorId);
		try {
			responseModel = bankOperatorService.deleteBankOperatorById(bankoperatorId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Deleted BankOperator Successfully  By Using id ");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Delete BankOperator by using id");
			e.printStackTrace();
		}
		LOG.info("Exiting deleteBankOperatorById s Of Controller with response ", responseEntity);
		return responseEntity;
	}
}
