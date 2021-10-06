package com.dromedian.comunico.bgpoint.cse.service.impl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.SmartDesk;
import com.dromedian.comunico.bgpoint.cse.repository.SmartDeskRepository;
import com.dromedian.comunico.bgpoint.cse.service.SmartDeskService;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;
import com.dromedian.comunico.bgpoint.cse.util.Utility;

@Service
public class SmartDeskServiceImpl implements SmartDeskService, CSEConstants {
	private static final Logger LOG = LoggerFactory.getLogger(SmartDeskServiceImpl.class);
	@Autowired
	private SmartDeskRepository smartdeskrepository;
	@Autowired
	BgpointCseUtility bgpointCseUtility;
	ResponseModel responseModel = null;
	
	
	
	@Override
	@CacheEvict(value="smartdesks",allEntries=true)
	public ResponseModel saveSmartDesk(SmartDesk smartDesk) {
		LOG.info("Inside saveSmartDesk of service with request parameter ", smartDesk);
		SmartDesk saveSmartDesk = null;
		try {
			String errorMessage = Utility.validateSmartDeskRequest(smartDesk);
			if (errorMessage == null) {
				smartDesk.setSmartDeskId(new SimpleDateFormat("ddMMyyyyHHmm").format(Calendar.getInstance().getTime()));
				saveSmartDesk = smartdeskrepository.save(smartDesk);
				if (saveSmartDesk != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.error(" Successfully Created SmartDesk :",saveSmartDesk.toString() );
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.error(" Failed to  Created SmartDesk ",smartDesk);
				}
			} else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}
			LOG.info("Exiting saveSmartDesk of service with response ",saveSmartDesk != null ? saveSmartDesk==null : -1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in saveSmartDesk Save", e);
		}
		return responseModel;
	}

	@Override
	@CachePut(value = "cache", key="#id")
	public ResponseModel updateSmartDesk(SmartDesk smartDesk) {
		LOG.info("Inside updateSmartDesk of service with request parameter smartDesk{}. :", smartDesk);
		SmartDesk UpadateSmartDesk = null;
		try {
			Optional<SmartDesk> findSmartDesk = smartdeskrepository.findById(smartDesk.getId());
			if(findSmartDesk.isPresent()) {
				SmartDesk smartDeskToUpdate = findSmartDesk.get();
				BeanUtils.copyProperties(smartDesk, smartDeskToUpdate, Utility.getNullPropertyNames(smartDesk));
				UpadateSmartDesk = smartdeskrepository.save(smartDeskToUpdate);
				if (UpadateSmartDesk != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.error(" SUccessfully Updated SmartDesk :",UpadateSmartDesk.toString() );
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.error(" Failed to Updated SmartDesk ",smartDesk);
				}
			}else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Exiting updateSmartDesk of service with response ",UpadateSmartDesk != null ? UpadateSmartDesk==null : -1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in updateSmartDesk Update", e);
		}
		return responseModel;
	}

	@Override
	//@Cacheable(value="smartdesks")
	public ResponseModel findAllSmartDesk() {
		LOG.info("Inside findAllSmartDesk of service with request parameter ");
		try {
			//List<SmartDesk> smartdesks = smartdeskrepository.findByIsDeleted(false);
			List<SmartDesk> smartdesks = smartdeskrepository.findAllSmartDesk();
			if (smartdesks!= null) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, smartdesks);
				LOG.info(" Successfully Fetched All SmartDesk Records :",smartdesks.size()-1);
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" Failed to  Fetch All SmartDesk Records ",smartdesks);
			}
			LOG.info("Exiting findAllSmartDesk of service with response ",smartdesks);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in findAllSmartDesk ", e);
		}
		return responseModel;
	}
	
	@Override
	@Cacheable(value="smartdesks")
	public ResponseModel findSmartDeskById(Long smartDeskId) {
		LOG.info("Inside findSmartDeskById of service with request parameter smartDeskId{}. :",smartDeskId);
		try {
			Optional<SmartDesk> results = smartdeskrepository.findByIdAndIsDeleted(smartDeskId, false);
			if (results.isPresent()) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results.get());
				LOG.info(" Successfully Fetched SmartDesk Record By SmartDeskId ",results.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" failed to Fetched SmartDesk Record By SmartDeskId ", smartDeskId);

			}
			LOG.info("Exiting findSmartDeskById of service with response ",smartDeskId);
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in findAllSmartDesk ", e);
		}
		return responseModel;
	}

	@Override
	@CacheEvict(value = "employees",allEntries = true)
	public ResponseModel deleteSmartDeskById(Long smartDeskId) {
		LOG.info("Inside deleteSmartDeskById of service with request parameter ",smartDeskId);
		ResponseModel responseModel = null;
		SmartDesk smartDeskData = null;
		try {
			Optional<SmartDesk> smartDesk = smartdeskrepository.findByIdAndIsDeleted(smartDeskId, false);
			if (smartDesk.isPresent()) {
				smartDeskData = smartDesk.get();
				smartDeskData.setIsDeleted(true);
				SmartDesk isDeleted = smartdeskrepository.save(smartDeskData);

				if (isDeleted.getIsDeleted()) {
					responseModel = Utility.getResponse(true, RECORD_DELETE_STATUS_SUCCESS, null);
					LOG.info(" Successfully Deleted SmartDesk Record By SmartDeskId ",isDeleted.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_DELETE_STATUS_FAILED, null);
					LOG.info(" failed to Delete SmartDesk Record By SmartDeskId ",smartDeskId);
				}

			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Exiting deleteSmartDeskById of service with response ",smartDeskId);
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in deleteSmartDeskById ", e);
		}
		return responseModel;
	}

	@Override
	public boolean existsById(Long id) {
		return smartdeskrepository.existsById(id);
	}
	
	@Override
	public ResponseModel getSmartDeskByEntityId(String entity, Long entityId) {
		LOG.info("Inside getSmartDeskByEntityId of service with request parameter entity{}. entityId{} :",entity,entityId );
		ResponseModel responseModel = null;
		List<SmartDesk> smartDesks = null;
		try {
			//these are changable method
			switch (entity) {
			case BANK_BRANCH:
				smartDesks = smartdeskrepository.getSmartDeskByBankBranchId(entityId);
				LOG.info(" Calling the BANK_BRANCH Records By getSmartDeskByBankBranchId Method :", smartDesks!=null? smartDesks.size():-1 );
				break;	
			case CHEQUE_BOOK:
				smartDesks = smartdeskrepository.getSmartDeskByChequeBookId(entityId);
				LOG.info("Calling the CHEQUE_BOOK  getSmartDeskByChequeBookId Method", smartDesks!=null? smartDesks.size():-1);

				break;

			case CREDIT_CARD:
				smartDesks = smartdeskrepository.getSmartDeskByCreditCard(entityId);
				LOG.info("Calling the CREDIT_CARD  getSmartDeskByCreditCard Method", smartDesks!=null?smartDesks.size():-1);
				break;
				
			default:
				break;
			}
			
			if (smartDesks!=null && smartDesks.size()>0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, smartDesks);
				LOG.info(" Successfully getting SmartDesk by BankBranchId / ChequeBookId / CreditCardId ");

			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" failed to get SmartDesk by BankBranchId / ChequeBookId / CreditCardId ");
			}
			LOG.info("Exiting getSmartDeskByEntityId of service with response ");
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info("Exception in getSmartDeskByEntityId ",e);
		}
		return responseModel;
	}
	
	
	// Search SmartDesk 
	@Override
	public ResponseModel searchSmartDesk(String searchKeyword) {
		LOG.info("Inside searchSmartDesk of service with request parameter ",searchKeyword);
		ResponseModel responseModel = null;
		try {
			List<SmartDesk> results = smartdeskrepository.searchSmartDesk(searchKeyword);
			if (results!=null && results.size()>0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results);
				LOG.info(" Successfully Search SmartDesk by SearchKeyword ", results.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" Failed to Search SmartDesk by SearchKeyword ", searchKeyword);
			}
			LOG.info("Exiting searchSmartDesk of service with response ",searchKeyword);

		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in searchSmartDesk ", e);
		}
		return responseModel;
	}
}