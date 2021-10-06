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
import com.dromedian.comunico.bgpoint.cse.entity.BankBranch;
import com.dromedian.comunico.bgpoint.cse.repository.BankBranchRepository;
import com.dromedian.comunico.bgpoint.cse.service.BankBranchService;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;
import com.dromedian.comunico.bgpoint.cse.util.Utility;

@Service
public class BankBranchServiceImpl implements BankBranchService, CSEConstants {
	private static final Logger LOG = LoggerFactory.getLogger(BankBranchServiceImpl.class);
	@Autowired
	private BankBranchRepository bankbranchrepository;
	@Autowired
	BgpointCseUtility bgpointCseUtility;

	@Override
	@Transactional
	public ResponseModel saveBankBranch(BankBranch bankbranch) {
		LOG.info("Inside saveOrUpdate of service with request parameter bankbranch{}. :", bankbranch);
		ResponseModel responseModel = null;
		BankBranch saveBankBranch = null;
		try {
			String errorMessage = Utility.validateBankBranchRequest(bankbranch);
			if (errorMessage == null) {
				saveBankBranch = bankbranchrepository.save(bankbranch);
				if (saveBankBranch != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info(" BankBranch Save Successfully! ", saveBankBranch.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info(" Failed to Save BankBranch", bankbranch);
				}

			} else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}

			LOG.info("Exiting saveOrUpdate of service with response ", saveBankBranch != null ? saveBankBranch : -1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankBranch save", e);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel updateBankBranch(BankBranch bankbranch) {
		LOG.info("Inside updateBankBranch of service with request parameter ", bankbranch);
		ResponseModel responseModel = null;
		BankBranch updateBankBranch = null;
		try {
			Optional<BankBranch> findBankBranch = bankbranchrepository.findById(bankbranch.getId());
			if (findBankBranch.isPresent()) {
				BankBranch BankBranchToUpdate = findBankBranch.get();
				BeanUtils.copyProperties(bankbranch, BankBranchToUpdate, Utility.getNullPropertyNames(bankbranch));
				updateBankBranch = bankbranchrepository.save(bankbranch);
				if (updateBankBranch != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info(" BankBranch Updated successfully! ", updateBankBranch.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info(" Failed to Update BankBrancht", bankbranch);
				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Exiting updateBankBranch of service with response ",
					updateBankBranch != null ? updateBankBranch : -1);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankBranch Update", e);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel findAllBankBranch() {
		LOG.info("Inside findAllBankBranch of service with request parameter ");
		ResponseModel responseModel = null;
		try {
			// List<BankBranch> bankbranchs =
			// bankbranchrepository.findByIsDeleted(false);
			List<BankBranch> bankbranchs = bankbranchrepository.findAllBankBranch();
			/*
			 * if (bankbranchs.size() != 0)
			 */
			if (bankbranchs != null) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, bankbranchs);
				LOG.info(" Fetched All BankBranch Records successfully! ", bankbranchs.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" Failed to Fetch All BankBranch Records", bankbranchs);

			}
			LOG.info("Exiting findAllBankBranch of service with response ",
					bankbranchs != null ? bankbranchs.size() : -1);
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankBranch FindAllBankBranch", e);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel findBankBranchById(Long bankbranchId) {
		LOG.info("Inside findBankBranchById of service with request parameter bankbranchId{}. :", bankbranchId);

		ResponseModel responseModel = null;
		try {
			Optional<BankBranch> results = bankbranchrepository.findByIdAndIsDeleted(bankbranchId, false);
			if (results.isPresent()) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results.get());
				LOG.info(" Fetched BankBranch Record Sucessfully By BankBranchId ", results.toString());

			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info("Failed to Fetched BankBranch by BankBranchId", bankbranchId);

			}
			LOG.info("Exiting findBankBranchById of service with response ");

		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankBranch FindBankBranchById", e);
		}
		return responseModel;
	}

	@Override
	@Transactional
	public ResponseModel deleteBankBranchById(Long bankbranchId) {
		LOG.info("Inside deleteBankBranchById of service with request parameter ");
		ResponseModel responseModel = null;
		BankBranch bankbranchdata = null;
		try {
			Optional<BankBranch> bankbranch = bankbranchrepository.findByIdAndIsDeleted(bankbranchId, false);
			if (bankbranch.isPresent()) {
				bankbranchdata = bankbranch.get();
				bankbranchdata.setIsDeleted(true);
				BankBranch isDeleted = bankbranchrepository.save(bankbranchdata);
				if (isDeleted.getIsDeleted()) {
					responseModel = Utility.getResponse(true, RECORD_DELETE_STATUS_SUCCESS, null);
					LOG.info(" BankBranch Record Deleted Successfully by BankBranchId", isDeleted.toString());

				} else {
					responseModel = Utility.getResponse(false, RECORD_DELETE_STATUS_FAILED, null);
					LOG.info("Failed to Delete BankBranch Record by BankBranchId", bankbranchId);
				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Exiting deleteBankBranchById of service with response ", bankbranchId);
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankBranch deleteBankBranchById", e);
		}
		return responseModel;
	}

	@Override
	public boolean existsById(Long id) {
		return bankbranchrepository.existsById(id);

	}

	@Override
	@Transactional
	public ResponseModel searchBankBranch(String searchKeyword) {
		LOG.info("Inside searchBankBranch of service with request parameter  searchKeyword{}. :", searchKeyword);
		ResponseModel responseModel = null;
		try {
			List<BankBranch> results = bankbranchrepository.searchBankBranch(searchKeyword);
			if (results != null && results.size() > 0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results);
				LOG.info(" Searched BankBranch Record  Successfully by searchKeyword ", results.toString());

			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info("Failed to Searched BankBranch Record by searchKeyword", searchKeyword);

			}
			LOG.info("Exiting searchBankBranch of service with response ", searchKeyword);

		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in BankBranch searchBankBranch", e);
		}
		return responseModel;
	}
}