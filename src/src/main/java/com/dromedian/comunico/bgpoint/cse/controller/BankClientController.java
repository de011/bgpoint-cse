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
import com.dromedian.comunico.bgpoint.cse.entity.BankClient;
import com.dromedian.comunico.bgpoint.cse.service.BankClientService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/dromedian")
public class BankClientController {
	private static final Logger LOG = LoggerFactory.getLogger(BankClientController.class);	
	@Autowired
	private BankClientService bankClientService;
	
	// Save and update BankClient
	@PostMapping("/save/bankclient")
		public ResponseEntity<ResponseModel> saveBankClient(@RequestBody BankClient bankclients) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
			LOG.info("Inside saveBankClient Of Controller with request parameter ", bankclients);
			try {
				if(bankclients!=null && bankclients.getId()!=null &&  bankclients.getId()>0) {
					responseModel = bankClientService.updateBankClient(bankclients);
				}else {
					responseModel = bankClientService.saveBankClient(bankclients);
				}
				responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
				LOG.info("BankClient  created Successfully!");
			} catch (Exception e) {
				responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
				LOG.info("Failed to Save the BankClient");
				e.printStackTrace();
			}
			LOG.info("Exiting saveBankClient Of Controller with response ", responseEntity);
			return responseEntity;
		}

	// Find BankClient By Id
		@GetMapping("/find/bankclient/byid/{id}")
		public ResponseEntity<ResponseModel> findBankclientById(@PathVariable("id") Long userId) {
			ResponseEntity<ResponseModel> responseEntity = null;
			ResponseModel responseModel = null;
			LOG.info("Inside findBankclientById Of Controller with request parameter ");
			try {
				responseModel = bankClientService.findBankclientById(userId);
				responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
				LOG.info(" Getting BankClient Records Successfully!");
			} catch (Exception e) {
				responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
				LOG.info("Failed to Get BankClient Record");
				e.printStackTrace();
			}
			LOG.info("Exiting findBankclientById Of Controller with response ", responseEntity);
			return responseEntity;
		}
		
		
		// find All BankClinet
		@GetMapping("/find/bankclient")
		public ResponseEntity<ResponseModel> findAllBankClient() {
			ResponseEntity<ResponseModel> responseEntity = null;
			ResponseModel responseModel = null;
			LOG.info("Inside findAllBankClient Of Controller with request parameter ");
			try {
				responseModel = bankClientService.findAllBankClient();
				responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
				LOG.info(" Fetching All BankClient Records Successfully!");
			} catch (Exception e) {
				responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
				LOG.info("Failed to Fetch BankClient Records ");
				e.printStackTrace();
			}
			LOG.info("Exiting findAllBankClient Of Controller with response ", responseEntity);
			return responseEntity;
		}

		// Delete BankClinet By Id
		@DeleteMapping("/delete/bankclinet/{userId}")
		public ResponseEntity<ResponseModel> deleteBankClientById(@PathVariable("userId") Long userId) {
			ResponseEntity<ResponseModel> responseEntity = null;
			ResponseModel responseModel = null;
			LOG.info("Inside deleteBankClientById Of Controller with request parameter ", userId);
			try {
				responseModel = bankClientService.deleteBankClientById(userId);
				responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
				LOG.info(" Deleted BankClient Record Successfully!");
			} catch (Exception e) {
				responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
				LOG.info("Failed to Delete BankClient Record");
				e.printStackTrace();
			}
			LOG.info("Exiting deleteBankClientById s Of Controller with response ", responseEntity);
			return responseEntity;
		}
}
