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
import com.dromedian.comunico.bgpoint.cse.entity.SmartDesk;
import com.dromedian.comunico.bgpoint.cse.service.SmartDeskService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/dromedian")
public class SmartDeskController {
	private static final Logger LOG = LoggerFactory.getLogger(SmartDeskController.class);
	@Autowired
	private SmartDeskService smartDeskService;

	// Save and update SmartDesk
	@PostMapping("/save/smartdesk")
	public ResponseEntity<ResponseModel> saveSmartDesk(@RequestBody SmartDesk smartdesks) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside saveSmartDesk Of Controller with request parameter ", smartdesks);
		try {
			if(smartdesks!=null && smartdesks.getId()!=null && smartdesks.getId()>0 ) {
				responseModel = smartDeskService.updateSmartDesk(smartdesks);
			}else {
				responseModel = smartDeskService.saveSmartDesk(smartdesks);
			}
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info("SmartDesk  created Successfully!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Create SmartDesk");
			e.printStackTrace();
		}
		LOG.info("Exiting saveSmartDesk Of Controller with response ", responseEntity);
		return responseEntity;
	}

	//	// Get List of SmartDesk In Bankbranch
	//	@GetMapping("/find/smartdesks/branchid/{id}")
	//	public ResponseEntity<ResponseModel> getSmartDeskByBranchId(@PathVariable("id") Long SmartDeskId){
	//		ResponseEntity<ResponseModel> responseEntity = null;
	//		ResponseModel responseModel = null;
	//		LOG.info("Inside findByBankBranchIdAndIsDeleted Of Controller with request parameter ");
	//		try {
	//			responseModel = smartDeskService.getSmartDeskByBranchId(SmartDeskId);
	//			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	//			LOG.info(" Getting SmartDesk Data by findByBankBranchIdAndIsDeleted Successfully!");
	//		} catch (Exception e) {
	//			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
	//			LOG.info("Failed to Getting SmartDesk Data by findByBankBranchIdAndIsDeleted");
	//			e.printStackTrace();
	//		}
	//		LOG.info("Exiting findByBankBranchIdAndIsDeleted Of Controller with response ", responseEntity);
	//		return responseEntity;
	//	}

	// Get List of SmartDesk In Bankbranch
	@GetMapping("/find/smartdesks/{entity}/{entityId}")
	public ResponseEntity<ResponseModel> getSmartDeskByEntityId(@PathVariable("entity") String entity,  @PathVariable("entityId") Long entityId){
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside getSmartDeskByEntityId Of Controller with request parameter ",entity, entityId);
		try {
			responseModel = smartDeskService.getSmartDeskByEntityId(entity, entityId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting List of SmartDesk Successfully!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get list of SmartDesk!");
			e.printStackTrace();
		}
		LOG.info("Exiting getSmartDeskByEntityId Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Find SmartDesk By Id
	@GetMapping("/find/smartdesk/byid/{id}")
	public ResponseEntity<ResponseModel> findSmartDeskById(@PathVariable("id") Long smartDeskId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findSmartDeskById Of Controller with request parameter ", smartDeskId);
		try {
			responseModel = smartDeskService.findSmartDeskById(smartDeskId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting List of SmartDesk Successfully By SmartDeskId");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get List of SmartDesk By SmartDeskId");
			e.printStackTrace();
		}
		LOG.info("Exiting findSmartDeskById Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Find SmartDesk By Id
	@GetMapping("/search/smartdesks/{searchKeyword}")
	public ResponseEntity<ResponseModel> searchSmartDesk(@PathVariable("searchKeyword") String searchKeyword) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside searchSmartDesk Of Controller with request parameter ");
		try {
			responseModel = smartDeskService.searchSmartDesk(searchKeyword);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting SmartDesks Successfully! By searchKeyword ");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get SmartDesks Successfully! By searchKeyword");
			e.printStackTrace();
		}
		LOG.info("Exiting searchSmartDesk Of Controller with response ", responseEntity);
		return responseEntity;
	}
	
	// FindAll SmartDesk
	@GetMapping("/find/smartdesk")
	public ResponseEntity<ResponseModel> findAllSmartDesk() {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findAllSmartDesk Of Controller with request parameter ");
		try {
			responseModel = smartDeskService.findAllSmartDesk();
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetching All Records of SmartDesk Successfully");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Fetch all records of SmartDesk ");
			e.printStackTrace();
		}
		LOG.info("Exiting findAllSmartDesk Of Controller with response ", responseEntity);
		return responseEntity;
	}
	// Delete SmartDesk By Id
	@DeleteMapping("/delete/smartdesk/{smartDeskId}") 
	public ResponseEntity<ResponseModel> deleteSmartDeskById(@PathVariable("smartDeskId") Long smartDeskId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside deleteSmartDeskById Of Controller with request parameter ", smartDeskId);
		try {
			responseModel = smartDeskService.deleteSmartDeskById(smartDeskId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Deleted Successfully SmartDesk Record By SmartDeskId");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Delete SmartDesk Record By SmartDeskId");
			e.printStackTrace();
		}
		LOG.info("Exiting deleteSmartDeskById Of Controller with response ", responseEntity);
		return responseEntity;
	}
}
