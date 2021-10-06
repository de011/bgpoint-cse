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
import com.dromedian.comunico.bgpoint.cse.repository.BankClientRepository;
import com.dromedian.comunico.bgpoint.cse.service.BankClientService;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;
import com.dromedian.comunico.bgpoint.cse.util.Utility;

@Service
public class BankClientServiceImpl implements BankClientService, CSEConstants {
	private static final Logger LOG = LoggerFactory.getLogger(BankClientServiceImpl.class);

	@Autowired
	private BankClientRepository bankclientRepository;

	@Autowired
	BgpointCseUtility bgpointCseUtility;

	@Override
	@Transactional
	public ResponseModel saveBankClient(BankClient bankclient) {
		LOG.info("Inside saveBankClient of service with request parameter ", bankclient);
		ResponseModel responseModel = null;
		BankClient savebankclient = null;
		try {
			String errorMessage = Utility.validateBankClientRequest(bankclient);
			if (errorMessage == null) {
				savebankclient = bankclientRepository.save(bankclient);
				if (savebankclient != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info("Successfully Save BankClient ", savebankclient.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info("Failed to Save BankClient Record ",bankclient);
				}
			} else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}
			LOG.info("Exiting saveBankClient of service with response ",savebankclient != null ? savebankclient == null : -1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankClient Save", e);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel updateBankClient(BankClient bankclient) {
		LOG.info("Inside updateBankClient of service with request parameter ", bankclient);
		ResponseModel responseModel = null;
		BankClient updatebankclient = null;
		try {
			Optional<BankClient> findBankClient = bankclientRepository.findById(bankclient.getId());
			if (findBankClient.isPresent()) {
				BankClient BankClientToUpdate = findBankClient.get();
				BeanUtils.copyProperties(bankclient, BankClientToUpdate, Utility.getNullPropertyNames(bankclient));
				updatebankclient = bankclientRepository.save(BankClientToUpdate);
				if (updatebankclient != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info("Successfully Updated BankClient ",updatebankclient.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info("Failed to Update BankClient ",bankclient);
				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Exiting updateBankClient of service with response",updatebankclient!=null?updatebankclient.toString():-1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankClient Update", e);
		}
		return responseModel;
	}
	
	@Override
	public ResponseModel findAllBankClient() {
		LOG.info("Inside findAllBankClient of service with request parameter ");
		ResponseModel responseModel = null;
		try {
			List<BankClient> bankclients = bankclientRepository.findByIsDeleted(false);
			if (bankclients.size() != 0) {

				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, bankclients);
				LOG.info("Successfully Fetched BankClient Record  ",bankclients.toString());

			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info("Failed to Fetch BankClient Record ",bankclients);
			}
			LOG.info("Exiting findAllBankClient of service with response", bankclients!=null? bankclients.size():-1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in findAllBankClient", e);
		}
		return responseModel;
	}
	
	@Override
	@Transactional
	public ResponseModel findBankclientById(Long userId) {
		LOG.info("Inside findBankclientById of service with request parameter userId{}. :",userId);
		ResponseModel responseModel = null;
		try {
			Optional<BankClient> results = bankclientRepository.findByIdAndIsDeleted(userId, false);
			if (results.isPresent()) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results.get());
				LOG.info("Fetched BankClient Record Successfully By BankClient_Id ", results.toString());

			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" Failed to Fetch BankClient Record Successfully By BankClient_Id ", userId);
			}
			LOG.info("Exiting findBankclientById of service with response :", userId );
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in findBankclientById", e);

		}
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel deleteBankClientById(Long userId) {
		LOG.info("Inside deleteBankClientById of service with request parameter userId{}. :",userId);
		ResponseModel responseModel = null;
		BankClient bankclientdata = null;
		try {
			Optional<BankClient> bankclient = bankclientRepository.findByIdAndIsDeleted(userId, false);
			if (bankclient.isPresent()) {
				bankclientdata = bankclient.get();
				bankclientdata.setIsDeleted(true);
				BankClient isDeleted = bankclientRepository.save(bankclientdata);
				if (isDeleted.getIsDeleted()) {
					responseModel = Utility.getResponse(true, RECORD_DELETE_STATUS_SUCCESS, null);
					LOG.info(" Successfully Deleted BankClient Record By BankClientById :",isDeleted.toString());

				} else {
					responseModel = Utility.getResponse(false, RECORD_DELETE_STATUS_FAILED, null);
					LOG.info(" Failed Deleted BankClient Record By BankClientById",userId );
				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Exiting deleteBankClientById of service with response",userId);
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in findBankclientById", e);
		}
		return responseModel;
	}

	@Override
	public boolean existsById(Long id) {
		return bankclientRepository.existsById(id);
	}
}
