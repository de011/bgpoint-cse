package com.dromedian.comunico.bgpoint.cse.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.BankClient;
import com.dromedian.comunico.bgpoint.cse.entity.BankOperator;
import com.dromedian.comunico.bgpoint.cse.repository.BankOperatorRepository;
import com.dromedian.comunico.bgpoint.cse.service.BankOperatorService;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;
import com.dromedian.comunico.bgpoint.cse.util.Utility;

@Service
public class BankOperatorServiceImpl implements BankOperatorService, CSEConstants {

	private static final Logger LOG = LoggerFactory.getLogger(BankOperatorServiceImpl.class);
	@Autowired
	private BankOperatorRepository bankoperatorrepository;
	@Autowired
	BgpointCseUtility bgpointCseUtility;

	@Override
	@Transactional
	public ResponseModel saveBankOperator(BankOperator bankoperator) {
		LOG.info("Inside saveBankOperator of service with request parameter ", bankoperator);
		ResponseModel responseModel = null;
		BankOperator saveBankOperator = null;
		try {
			String errorMessage = Utility.validateBankOperatorRequest(bankoperator);
			if (errorMessage == null) {
				saveBankOperator = bankoperatorrepository.save(bankoperator);
				if (saveBankOperator != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info(" Successfully Created BankOperator ",saveBankOperator.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info("Failed To Create BankOperator ", bankoperator);
				}
			} else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}
			LOG.info("Exiting saveBankOperator of service with response ",saveBankOperator != null ? saveBankOperator==null : -1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankOperator Save / Update", e);
		}
		return responseModel;

	}
	
	@Override
	@Transactional
	public ResponseModel updateBankOperator(BankOperator bankoperator) {
		LOG.info("Inside updateBankOperator of service with request parameter ", bankoperator);
		ResponseModel responseModel = null;
		BankOperator updateBankOperator = null;
		try {
			Optional<BankOperator> findBankOperator = bankoperatorrepository.findById(bankoperator.getId());
			if(findBankOperator.isPresent()) {
				BankOperator BankOperatorToUpdate = findBankOperator.get();
				BeanUtils.copyProperties(bankoperator, BankOperatorToUpdate, Utility.getNullPropertyNames(bankoperator));
				updateBankOperator = bankoperatorrepository.save(bankoperator);
				if (updateBankOperator != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info("Successfully Updated BankOperator", updateBankOperator.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info("Failed to Update BankOperator", bankoperator);
				}

			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}

			LOG.info("Exiting updateBankOperator of service with response ",updateBankOperator != null ? updateBankOperator==null : -1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankOperator Update", e);
		}
		return responseModel;

	}

	@Override
	@Transactional
	public ResponseModel findAllBankOperator() {
		LOG.info("Inside findAllBankOperator Without request Parameters");
		ResponseModel responseModel = null;
		try {
			List<BankOperator> bankoperators = bankoperatorrepository.findByIsDeleted(false);
			if (bankoperators.size() != 0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, bankoperators);
				LOG.info(" Successfully Getting All BankOperator :", bankoperators.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" failed Get All BankOperator :", bankoperators);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in  findAllBankOperator :", e);
		}
		return responseModel;
	}	
	@Override
	@Transactional
	public ResponseModel findBankOperatorId(Long bankoperatorId) {
		LOG.info("Inside findAllBankOperator With request Parameters bankoperatorId{}. :",bankoperatorId);
		ResponseModel responseModel = null;
		try {
			Optional<BankOperator> results = bankoperatorrepository.findByIdAndIsDeleted(bankoperatorId, false);
			if (results.isPresent()) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results.get());
				LOG.info(" Successfully Getting All BankOperator :", results.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" failed Get All BankOperator By findBankOperatorId:", bankoperatorId);
			}
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in  findBankOperatorId :", e);

		}
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel deleteBankOperatorById(Long bankoperatorId) {
		LOG.info("Inside deleteBankOperatorById With request Parameters bankoperatorId{}. :",bankoperatorId);
		ResponseModel responseModel = null;
		BankOperator bankoperatordata = null;
		try {
			Optional<BankOperator> bankoperator = bankoperatorrepository.findByIdAndIsDeleted(bankoperatorId, false);
			if (bankoperator.isPresent()) {
				bankoperatordata = bankoperator.get();
				bankoperatordata.setIsDeleted(true);
				BankOperator isDeleted = bankoperatorrepository.save(bankoperatordata);
				if (isDeleted.getIsDeleted()) {
					responseModel = Utility.getResponse(true, RECORD_DELETE_STATUS_SUCCESS, null);
					LOG.info(" Successfully Deleted BankOperator By bankoperatorId :", isDeleted.toString());

				} else {
					responseModel = Utility.getResponse(false, RECORD_DELETE_STATUS_FAILED, null);
					LOG.info(" failed to Deleted BankOperator By bankoperatorId :", bankoperatorId);
				}

			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}

		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in deleteBankOperatorById", e);
		}
		return responseModel;
	}

	@Override
	public boolean existsById(Long id) {
		return bankoperatorrepository.existsById(id);

	}

}
