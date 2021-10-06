package com.dromedian.comunico.bgpoint.cse.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dromedian.bgpoint.hb.EsecuzioneOperazioneOutputData;
import com.dromedian.bgpoint.hb.MsgT;
import com.dromedian.comunico.bgpoint.cse.dto.ChequeBookDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.dto.CreditCardDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.BankOperator;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBooksDeliveryTransaction;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBooksIngestionTransaction;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBooksLoadingTransaction;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCard;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCardDeliveryTransaction;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCardIngestionTransaction;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCardLoadingTransaction;
import com.dromedian.comunico.bgpoint.cse.entity.SmartDesk;
import com.dromedian.comunico.bgpoint.cse.repository.ChequeBookIngestionTransactionRepository;
import com.dromedian.comunico.bgpoint.cse.repository.ChequeBookRepository;
import com.dromedian.comunico.bgpoint.cse.repository.ChequeBooksDeliveryTransactionRepository;
import com.dromedian.comunico.bgpoint.cse.repository.ChequeBooksLoadingTransactionRepository;
import com.dromedian.comunico.bgpoint.cse.repository.CreditCardDeliveryTransactionRepository;
import com.dromedian.comunico.bgpoint.cse.repository.CreditCardIngestionTransactionRepository;
import com.dromedian.comunico.bgpoint.cse.repository.CreditCardLoadingTransactionRepository;
import com.dromedian.comunico.bgpoint.cse.repository.CreditCardRepository;
import com.dromedian.comunico.bgpoint.cse.service.ChequeBookAndCreditCardService;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;
import com.dromedian.comunico.bgpoint.cse.util.Utility;

/**
 *
 * @author Waseem
 */

@Service
public class ChequeBookAndCreditCardServiceImpl implements ChequeBookAndCreditCardService, CSEConstants {
	private static final Logger LOG = LoggerFactory.getLogger(ChequeBookAndCreditCardServiceImpl.class);

	@Autowired
	private ChequeBookRepository chequeBookRepository;

	@Autowired
	private ChequeBookIngestionTransactionRepository chequeBooksIngestionTransactionRepository;

	@Autowired
	private ChequeBooksLoadingTransactionRepository chequeBooksLoadingTransactionRepository;

	@Autowired
	private ChequeBooksDeliveryTransactionRepository chequeBooksDeliveryTransactionRepository;

	@Autowired
	private CreditCardIngestionTransactionRepository creditCardIngestionTransactionRepository;

	@Autowired
	private CreditCardLoadingTransactionRepository creditCardLoadingTransactionRepository;

	@Autowired
	private CreditCardDeliveryTransactionRepository creditCardDeliveryTransactionRepository;

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	BgpointCseUtility bgpointCseUtility;

	@Autowired
	Utility utility;

	/*@Override
	public ResponseModel chequeBooksIngestion(List<ChequeBook> chequeBooks)  {
		ResponseModel responseModel = null;
		List<ChequeBook> saveAllChequeBooks = null;
		LOG.info("Inside chequeBooksIngesion of service with request parameter ", chequeBooks);
		try {
			String errorMessage = utility.validatechequeBooksIngesionRequest(chequeBooks);
			if (errorMessage == null) {
				LOG.info("Inserting ChequeBooks ", chequeBooks);
				try {
					ChequeBooksIngestionTransaction chequeBookIngestionTransaction = null;
					for (ChequeBook cheque : chequeBooks) {
						chequeBookIngestionTransaction = new ChequeBooksIngestionTransaction();
						BankOperator bankOperator = new BankOperator();
						//bankOperator.setId(cheque.getOperatorId());
						chequeBookIngestionTransaction.setBankOperator(bankOperator);
						//cheque.setCbIngestionTransaction(chequeBookIngestionTransaction);
					}
					saveAllChequeBooks = saveChequeBoos(chequeBooks);
					if (saveAllChequeBooks != null && saveAllChequeBooks.size() > 0) {
						LOG.info("Inserted Sucessfully Because Of Successful response from WS");
						responseModel = Utility.getResponse(true, saveAllChequeBooks.size() + " " + RECORD_INGESTION_SUCCESS, null);
					} else {
						LOG.info(" Failed to Insert due to unseccessful response from WS");
						responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					}
				} catch (DataIntegrityViolationException | ConstraintViolationException ex) {
					responseModel = Utility.getResponse(false, RECORD_DUPLICATE, null);
				}

			} else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in ChequeBooks ", e);
		}
		return responseModel;
	}


	//  Chequebooks loading 

	@Override
	public ResponseModel chequeBooksLoading(List<ChequeBook> chequeBooks)  {
		ResponseModel responseModel = null;
		List<ChequeBook> loadAllChequeBooks = null;
		List<ChequeBook> updateChequeBook = null;
		ChequeBook findChequeBook = null;
		Boolean isSuccess = false;
		EsecuzioneOperazioneOutputData esecuzioneOperazione = null;
		LOG.info("Inside chequeBooksLoading of service with request parameter ", chequeBooks);
		try {
			String errorMessage = Utility.validateChequeBooksLoadingRequest(chequeBooks);
			if (errorMessage == null) {
				LOG.info("Getting ChequeBook by CodiceUnivoco and Updating ChequeBooks ", chequeBooks);
				updateChequeBook = new ArrayList<>();
				for (ChequeBook chequebook : chequeBooks) {
					if (!StringUtils.isEmpty(chequebook.getCodiceUnivoco())) {
						// call CSE WS second scenario
						Optional<ChequeBook> result = chequeBookRepository.findChequeBookByCodiceUnivoco(chequebook.getCodiceUnivoco());
						if (result.isPresent()) {
							findChequeBook = result.get();
							esecuzioneOperazione = bgpointCseUtility.esecuzioneOperazioneOutputData2(findChequeBook);
							if (esecuzioneOperazione.isSuccess()) {
								Integer cabAssegno = esecuzioneOperazione.getDettOperazione().getAzioneModuliInBiancoOut().getRilascioCarnet().getCabAssegno();
								Integer cinPrimoAssegno = esecuzioneOperazione.getDettOperazione().getAzioneModuliInBiancoOut().getRilascioCarnet().getCinPrimoAssegno();
								findChequeBook.setCabAssegno(cabAssegno);
								findChequeBook.setCinPrimoAssegno(cinPrimoAssegno);
								ChequeBooksLoadingTransaction chequeBooksLoadingTransaction = new ChequeBooksLoadingTransaction();
								BankOperator bankOperator = new BankOperator();
								bankOperator.setOperatorId(String.valueOf(findChequeBook.getId()));
								chequeBooksLoadingTransaction.setBankOperator(bankOperator);
								//findChequeBook.setChequebookLoadinTransaction(chequeBooksLoadingTransaction);
								updateChequeBook.add(findChequeBook);
								isSuccess = true;

							} else {
								//through API error
							}
						}
						findChequeBook = null;
						LOG.info("WS returns unseccessful response");
					}
				}
				if (!StringUtils.isEmpty(updateChequeBook) && isSuccess) {
					LOG.info("Updating ChequeBooks After getting the ChequeBook by CodiceUnivoco ", chequeBooks);
					loadAllChequeBooks = saveChequeBoos(loadAllChequeBooks);
					if (loadAllChequeBooks != null && loadAllChequeBooks.size() > 0) {
						LOG.info("Loading SucessFully Because Of Successful response from WS");
						responseModel = Utility.getResponse(true, loadAllChequeBooks.size() + " " + RECORD_LOADING_STATUS_SUCCESS, null);
					} else {
						LOG.info("Updating failed due to unseccessful response from WS");
						responseModel = Utility.getResponse(false, RECORD_LOADING_STATUS_FAILED, null);
					}
				} else {

					if(esecuzioneOperazione!=null && esecuzioneOperazione.getMessages()!=null&& esecuzioneOperazione.getMessages().size()>0) {
						MsgT messages = esecuzioneOperazione.getMessages().get(0);
						LOG.info("Loading failed due to unseccessful response from WS");
						responseModel = Utility.getResponse(false, messages.getDesc(), null);
					} else {
						LOG.info("Loading failed due to unseccessful response from WS");
						responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);	
					}

				}

			}else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}

		} catch (Exception e) {
			LOG.error("Exception in Chequebook Loading", e);
			return responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
		}
		System.out.println(responseModel.getMessage());
		return responseModel;
	}
	 */
	/*	@Transactional
	private List<ChequeBook> saveChequeBoos(List<ChequeBook> chequebooks) {
		return chequeBookRepository.save(chequebooks);
	}
	 */
	@Transactional
	@Override
	public ResponseModel findAllChequeBook() {
		LOG.info("Inside findAllChequeBook Method  Without Parameters ");
		ResponseModel responseModel = null;
		try {
			List<ChequeBook> chequeBooks = chequeBookRepository.findAllChequeBooks();
			if (chequeBooks.size() != 0) {
				responseModel = Utility.getResponse(true, null, chequeBooks);
				LOG.info(" Successfully Getting ChequeBooks Records",chequeBooks.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
				LOG.info("failed to Get ChequeBook Record ");
			}
			LOG.info("Exiting  findAllChequeBook Method :",chequeBooks);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info("Exception in findAllChequeBook Method ", e);
		}
		return responseModel;
	}

	@Override
	public ResponseModel findAllCreditCard() {
		LOG.info("Inside findAllCreditCard Method  with Request parameters");
		ResponseModel responseModel = new ResponseModel();
		try {
			List<CreditCard> creditcards = creditCardRepository.findAllCreditCard();
			if (creditcards.size() != 0) {
				responseModel = Utility.getResponse(true, null, creditcards);
				LOG.info(" Successfully Getting Creditcards Records :",creditcards.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
				LOG.info("failed to Get CreditCards Record :", creditcards );
			}
			LOG.info("Exiting  findAllCreditCard Method ");
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info("Exception in findAllCreditCard Method ", e);

		}
		return responseModel;
	}

	/*	@Override
	public Optional<ChequeBook> findChequeBookByCodiceUnivoco(Long codiceUnivoco) {
		Optional<ChequeBook> result = chequeBookRepository.findChequeBookByCodiceUnivoco(codiceUnivoco);
		return result;
	}
	 */
	@Override
	public boolean isChequeBookExist(Long id) {
		return chequeBookRepository.existsById(id);
	}


	@Override
	public ResponseModel chequeBookDelivery(ChequeBookDeliveryDTO chequeBookDelivery) {
		LOG.info("Inside thechequeBookDelivery with request parameters chequeBookDelivery{}. ", chequeBookDelivery);
		ResponseModel responseModel = null;
		ChequeBook chequeBook = null;
		ChequeBook chequebookDelivery = null;

		try {
			if (chequeBookDelivery.getCodiceUnivoco() != null) {
				LOG.info("Fetching ChequeBook with codiceUnivoco : ", chequeBookDelivery.getCodiceUnivoco());
				Optional<ChequeBook> chequebook = chequeBookRepository
						.findChequeBookByCodiceUnivoco(chequeBookDelivery.getCodiceUnivoco());
				if (chequebook.isPresent()) {
					chequeBook = chequebook.get();
					// if (chequeBook.getChequebookDeliveryTransaction() ==
					// null) {
					if (true) {

						LOG.info("Making call to CSE with parameter : ", chequeBook);
						// call CSE WS for third scenario

						EsecuzioneOperazioneOutputData esecuzioneOperazione = bgpointCseUtility
								.esecuzioneOperazioneOutputData3(chequeBook);
						LOG.info("Returned from esecuzioneOperazione of CSE with response : ", esecuzioneOperazione);
						if (esecuzioneOperazione.isSuccess()) {
							Integer cabAssegno = esecuzioneOperazione.getDettOperazione().getAzioneModuliInBiancoOut()
									.getRilascioCarnet().getCabAssegno();
							Integer cinPrimoAssegno = esecuzioneOperazione.getDettOperazione()
									.getAzioneModuliInBiancoOut().getRilascioCarnet().getCinPrimoAssegno();
							chequeBook.setCabAssegno(cabAssegno);
							chequeBook.setCinPrimoAssegno(cinPrimoAssegno);
							ChequeBooksDeliveryTransaction chequeBooksDeliveryTransaction = new ChequeBooksDeliveryTransaction();

							BankOperator bankOperator = new BankOperator();
							bankOperator.setOperatorId(String.valueOf(chequeBookDelivery.getId()));
							chequeBooksDeliveryTransaction.setBankOperator(bankOperator);
							// chequeBook.setChequebookDeliveryTransaction(chequeBooksDeliveryTransaction);
							chequebookDelivery = chequeBookRepository.save(chequeBook);
						} else {

							if (esecuzioneOperazione != null && esecuzioneOperazione.getMessages() != null
									&& esecuzioneOperazione.getMessages().size() > 0) {
								MsgT messages = esecuzioneOperazione.getMessages().get(0);
								LOG.info("Deliver Sucessfully Because WS returns Successful response");
								responseModel = Utility.getResponse(false, messages != null ? messages.getDesc() : null,
										chequebook);
							} else {
								LOG.info("Deliver Sucessfully Because WS returns Successful response");
								responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
							}
						}
					}
				} else {
					LOG.info("Failed to Deliver due to unseccessful response from WS");
					responseModel = Utility.getResponse(false, RECORD_DELIVERY_STATUS_FAILED, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in chequebookDelivery : ", e);
		}
		LOG.info("Exiting chequebookDelivery of service with response : ", chequebookDelivery);
		return responseModel;
	}

	@Override
	public ResponseModel creditCardDelivery(CreditCardDeliveryDTO creditcardDelivery) {
		LOG.info("Inside CreditCardDelivery of service with request parameter  creditcardDelivery{}. : ", creditcardDelivery);
		ResponseModel responseModel = null;
		CreditCard creditcard = null;
		CreditCard creditCardDelivery = null;
		try {
			if (creditcardDelivery.getCodicePan() != null) {
				LOG.info("Fetching creditcard with numCard : ", creditcardDelivery.getCodicePan());
				Optional<CreditCard> creditCard = creditCardRepository
						.findByCodicePan(creditcardDelivery.getCodicePan());
				if (creditCard.isPresent()) {
					creditcard = creditCard.get();
					// if (creditcard.getCreditCardDeliveryTransaction() ==
					// null) {
					if (true) {

						LOG.info("Making call to CSE with parameter : ", creditcard);

						// call CSE WS for third scenario

						EsecuzioneOperazioneOutputData esecuzioneOperazione = bgpointCseUtility
								.esecuzioneOperazione3(creditcard);
						LOG.info("Returned from esecuzioneOperazione of CSE with response : ", esecuzioneOperazione);
						if (esecuzioneOperazione.isSuccess()) {
							Integer idAzione = esecuzioneOperazione.getDettOperazione().getAzioneModuliInBiancoOut()
									.getRilascioCarnet().getCabAssegno();
							Integer cardNum = esecuzioneOperazione.getDettOperazione().getAzioneModuliInBiancoOut()
									.getRilascioCarnet().getCinPrimoAssegno();
							creditcard.setCodicePan(Long.valueOf(cardNum));
							CreditCardDeliveryTransaction creditCardDeliveryTransaction = new CreditCardDeliveryTransaction();
							BankOperator bankOperator = new BankOperator();
							bankOperator.setOperatorId(String.valueOf(creditcardDelivery.getId()));
							creditCardDeliveryTransaction.setBankOperator(bankOperator);

							// creditcard.setCreditCardDeliveryTransaction(creditCardDeliveryTransaction);
							creditCardDelivery = creditCardRepository.save(creditcard);
						} else {
							responseModel = Utility.getResponse(true, RECORD_DELIVERY_STATUS_SUCCESS, creditCard);
							LOG.info("Deliver Sucessfully Because WS returns Successful response");
						}
					}
				} else {
					LOG.info("Failed to Deliver due to unseccessful response from WS");
					responseModel = Utility.getResponse(false, RECORD_DELIVERY_STATUS_FAILED, null);
				}
			}
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in CreditCardDelivery : ", e);
		}
		LOG.info("Exiting CreditcardDelivery of service with response : ", creditCardDelivery);
		return responseModel;

	}



	@Override
	public ResponseModel findAllChequeBookTransaction(String type) {
		LOG.info("Inside findAllChequeBookTransaction of service with  parameter  type{}. : ", type);
		ResponseModel responseModel = null;
		List<ChequeBooksIngestionTransaction> chequebooksIngestionTransactions = null;
		List<ChequeBooksLoadingTransaction> chequebooksLoadingTransactions = null;
		List<ChequeBooksDeliveryTransaction> chequebooksDeliveryTransactions = null;
		try {

			if (type.equalsIgnoreCase("ingestion")) {
				chequebooksIngestionTransactions = chequeBooksIngestionTransactionRepository.findAll();
			} else if (type.equalsIgnoreCase("loading")) {
				chequebooksLoadingTransactions = chequeBooksLoadingTransactionRepository.findAll();
			} else if (type.equalsIgnoreCase("delivery")) {
				chequebooksDeliveryTransactions = chequeBooksDeliveryTransactionRepository.findAll();
			}
			if (chequebooksIngestionTransactions != null || chequebooksLoadingTransactions != null
					|| chequebooksDeliveryTransactions != null) {

				if (chequebooksIngestionTransactions != null) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS,chequebooksIngestionTransactions);
					LOG.info("Getting  ChequeBooksIngestionTransaction :", chequebooksIngestionTransactions.size());
				}
				if (chequebooksLoadingTransactions != null) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS,chequebooksLoadingTransactions);
					LOG.info("Getting  ChequeBooksLoadingTransaction By Type Loading :",chequebooksLoadingTransactions.size() );
				}
				if (chequebooksDeliveryTransactions != null) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS,chequebooksDeliveryTransactions);
					LOG.info("Getting ChequeBooksDeliveryTransaction By Type Delivery :",chequebooksDeliveryTransactions.size());
				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" Failed to Get ChequeBook Transactions Reords By Type Injestion / loading / Delivery");

			}
			LOG.info("Exiting findAllChequeBookTransaction of service with  parameter : ", type);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info("Exception in findAllChequeBookTransaction of service with  parameter : ", e);
		}

		return responseModel;
	}

	@Override
	public ResponseModel findAllCredidCardTransaction(String type) {
		LOG.info("Inside findAllChequeBookTransaction of service with  parameter : type{}. :", type);
		ResponseModel responseModel = null;
		List<CreditCardIngestionTransaction> creditCardIngestionTransaction = null;
		List<CreditCardLoadingTransaction> creditCardLoadingTransaction = null;
		List<CreditCardDeliveryTransaction> CreditCardDeliveryTransaction = null;
		try {
			if (type.equalsIgnoreCase("ingestion")) {
				creditCardIngestionTransaction = creditCardIngestionTransactionRepository.findAll();
			} else if (type.equalsIgnoreCase("loading")) {
				creditCardLoadingTransaction = creditCardLoadingTransactionRepository.findAll();
			} else if (type.equalsIgnoreCase("delivery")) {
				CreditCardDeliveryTransaction = creditCardDeliveryTransactionRepository.findAll();
			}
			if (creditCardIngestionTransaction != null || creditCardLoadingTransaction != null
					|| CreditCardDeliveryTransaction != null) {

				if (creditCardIngestionTransaction != null) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS,creditCardIngestionTransaction);
					LOG.info("Getting CreditCardsIngestionTransaction : ", creditCardIngestionTransaction.size());
				}
				if (creditCardLoadingTransaction != null) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, creditCardLoadingTransaction);
					LOG.info("Getting CreditCardsLoadingTransaction :", creditCardLoadingTransaction.size());

				}
				if (CreditCardDeliveryTransaction != null) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, CreditCardDeliveryTransaction);
					LOG.info("Getting CreditCardsDeliveryTransaction :",CreditCardDeliveryTransaction.size() );

				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" Failed to Get CreditCatrds Transactions Reords By Type Injestion / Loading / Delivery");

			}
			LOG.info("Exiting findAllChequeBookTransaction of service with  parameter : ", type);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info("Exception in findAllChequeBookTransaction : ",e);

		}
		return responseModel;
	}



	@Override
	public ResponseModel findChequeBookById(Long chequebookId) {
		LOG.info("Inside updateChequeBook of service with request parameter chequebookId{}. :", chequebookId);
		ResponseModel responseModel = null;
		try {
			Optional<ChequeBook> results = chequeBookRepository.findChequeBookById(chequebookId);
			if (results.isPresent()) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results.get());
				LOG.info(" Successfully find ChequeBook by chequebookId :",results.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" failed to find ChequeBook by chequebookId :",chequebookId);
			}
			LOG.info("Exiting updateChequeBook of service with request parameter chequebookId{}. :", chequebookId);
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in updateChequeBook :", e);
		}
		return responseModel;
	}

	@Override
	public ResponseModel findCreditCardById(Long creditcardId) {
		LOG.info("Inside updateChequeBook of service with request parameter creditcardId{}. :", creditcardId);
		ResponseModel responseModel = null;
		try {
			Optional<CreditCard> results = creditCardRepository.findCreditCardById(creditcardId);
			if (results.isPresent()) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results.get());
				LOG.info(" Successfully find creditCards by creditcardId :",results.toString());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" failed to find creditCards by creditcardId :",creditcardId);

			}
			LOG.info("Inside updateChequeBook of service with request parameter creditcardId{}. :", creditcardId);
		} catch (Exception e) {
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info("Exception in findCreditCardById: ",e);
		}
		return responseModel;
	}
	@Override
	public ResponseModel updateChequeBook(ChequeBook chequebook) {
		LOG.info("Inside updateChequeBook of service with request parameter chequebook{}. :", chequebook);
		ResponseModel responseModel = null;
		ChequeBook saveChequeBook = null;
		List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactionList = new ArrayList<>();
		try {
			Optional<ChequeBook> findChequeBook = chequeBookRepository.findById(chequebook.getId());
			if (findChequeBook.isPresent()) {
				ChequeBook ChequeBookToUpdate = findChequeBook.get();
				BeanUtils.copyProperties(chequebook, ChequeBookToUpdate, Utility.getNullPropertyNames(chequebook));

				// maintain Transaction
				ChequeBooksLoadingTransaction booksLoadingTransaction = new ChequeBooksLoadingTransaction();
				BankOperator bankOperator = new BankOperator();
				//for now its hardcoded, will remove once will get the value from header
				bankOperator.setId(1L);
				booksLoadingTransaction.setBankOperator(bankOperator);

				SmartDesk smartDesk = new SmartDesk();
				//for now its hardcoded, will remove once will get the value from header
				smartDesk.setId(1L);
				booksLoadingTransaction.setSmartDesk(smartDesk);
				chequeBooksLoadingTransactionList.add(booksLoadingTransaction);
				chequebook.setChequeBooksLoadingTransactions(chequeBooksLoadingTransactionList);

				saveChequeBook = chequeBookRepository.save(chequebook);
				if (saveChequeBook != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info(" successfully updated the ChequeBook :", saveChequeBook.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info(" Failed to updateChequeBook :",chequebook);
				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Exiting updateChequeBook of service with request parameter chequebook{}. :", chequebook);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in ChequeBook Update", e);
		}
		return responseModel;
	}

	@Override
	public ResponseModel updateCreditCard(CreditCard creditcard) {
		LOG.info("Inside updateChequeBook of service with request parameter creditcard{}. :", creditcard);
		ResponseModel responseModel = null;
		CreditCard saveCreditCard = null;
		List<CreditCardLoadingTransaction> creditCardLoadingTransactionsList = new ArrayList<>();
		try {
			Optional<CreditCard> findCreditcard = creditCardRepository.findById(creditcard.getId());
			if (findCreditcard.isPresent()) {
				CreditCard creditcardToUpdate = findCreditcard.get();
				BeanUtils.copyProperties(creditcard, creditcardToUpdate, Utility.getNullPropertyNames(creditcard));

				// maintain Transaction
				CreditCardLoadingTransaction cardLoadingTransaction = new CreditCardLoadingTransaction();
				BankOperator bankOperator = new BankOperator();
				bankOperator.setId(1L);
				cardLoadingTransaction.setBankOperator(bankOperator);

				SmartDesk smartDesk = new SmartDesk();
				smartDesk.setId(1L);
				cardLoadingTransaction.setSmartDesk(smartDesk);

				creditCardLoadingTransactionsList.add(cardLoadingTransaction);
				creditcard.setCreditCardLoadingTransactions(creditCardLoadingTransactionsList);

				saveCreditCard = creditCardRepository.save(creditcard);
				if (saveCreditCard != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info(" successfully Upadted Creditcards :",saveCreditCard.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info(" Failed to Update Creditcards :", creditcard);
				}
			} else {
				responseModel = Utility.getResponse(false, RECORD_NOT_FOUND, null);
			}
			LOG.info("Existing updateChequeBook of service with request parameter creditcard{}. :", creditcard);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in CreditCards Update", e);
		}
		return responseModel;
	}



	@Override
	public ResponseModel saveChequeBook(ChequeBook chequebook) {
		LOG.info("Inside saveChequeBook of service with request parameter chequebook{}. :", chequebook);
		ResponseModel responseModel = null;
		ChequeBook saveChequebook = null;
		try {
			String errorMessage = utility.validatechequeBooksIngesionRequest(chequebook);
			if (errorMessage == null) {

				// maintain Transaction
				List<ChequeBooksIngestionTransaction> booksIngestionTransactionsList = new ArrayList<>();
				ChequeBooksIngestionTransaction booksIngestionTransaction = new ChequeBooksIngestionTransaction();
				BankOperator bankOperator = new BankOperator();
				bankOperator.setId(1L);
				booksIngestionTransaction.setBankOperator(bankOperator);
				booksIngestionTransactionsList.add(booksIngestionTransaction);
				chequebook.setChequeBooksIngestionTransactions(booksIngestionTransactionsList);

				saveChequebook = chequeBookRepository.save(chequebook);
				if (saveChequebook != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info(" successfully save ChequeBook :", saveChequebook.toString());
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info(" Failed to Save ChequeBook :",chequebook);
				}

			} else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}
			LOG.info("Exiting saveChequeBook of service with request parameter chequebook{}. :", chequebook);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in ChequeBook Save", e);
		}
		return responseModel;
	}

	@Override
	public ResponseModel saveCreditCard(CreditCard creditcard) {
		LOG.info("Inside saveCreditCard of service with request parameter  creditcard{}. :", creditcard);
		ResponseModel responseModel = null;
		CreditCard saveCreditCard = null;
		try {
			String errorMessage = utility.validateCreditCradsIngesionRequest(creditcard);
			if (errorMessage == null) {

				// maintain Transaction
				List<CreditCardIngestionTransaction> creditCardIngestionTransactionsList = new ArrayList<>();
				CreditCardIngestionTransaction creditCardIngestionTransactions = new CreditCardIngestionTransaction();
				BankOperator bankOperator = new BankOperator();
				bankOperator.setId(1L);
				creditCardIngestionTransactions.setBankOperator(bankOperator);
				creditCardIngestionTransactionsList.add(creditCardIngestionTransactions);
				creditcard.setCreditCardIngestionTransactions(creditCardIngestionTransactionsList);

				saveCreditCard = creditCardRepository.save(creditcard);
				if (saveCreditCard != null) {
					responseModel = Utility.getResponse(true, RECORD_INGESTION_SUCCESS, null);
					LOG.info(" successfully save Creditcards :",saveCreditCard.toString() );
				} else {
					responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
					LOG.info(" Failed to save Due to incorrect input", saveCreditCard);
				}

			} else {
				responseModel = Utility.getResponse(false, errorMessage, null);
			}

			LOG.info("Exiting saveOrUpdate of service with response ",creditcard);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.error("Exception in Creditcard Save", e);
		}
		return responseModel;
	}


	@Override
	public ResponseModel searchChequeBook(String searchKeyword) {
		LOG.info("Inside saveCreditCard of service with request parameter  searchKeyword{}. :", searchKeyword);
		ResponseModel responseModel = null;
		try {
			List<ChequeBook> results = chequeBookRepository.searchChequeBook(searchKeyword);
			if (results != null && results.size() > 0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results);
				LOG.info("ChequeBook Searched Successfully :",results.size());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" failed to Searched  ChequeBook:", searchKeyword);
			}
			LOG.info("Exiting saveCreditCard of service with request parameter  searchKeyword{}. :", searchKeyword);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in searchChequeBook ", e);
		}
		return responseModel;
	}

	// CreditCards Ingesion

	/*
	  // CreditCards Ingesion

	  @Override
	  public ResponseModel creditCradsIngestion(List < CreditCard > creditCards) {
	    ResponseModel responseModel = null;
	    List < CreditCard > saveAllCreditCards = null;
	    LOG.info("Inside creditCradsIngesion of service with request parameter ", creditCards);
	    try {
	      String errorMessage = utility.validateCreditCradsIngesionRequest(creditCards);
	      if (errorMessage == null) {
	        LOG.info("Inserting CreditCards ", creditCards);
	        try {
	          CreditCardIngestionTransaction creditCardIngestionTransaction = null;
	          for (CreditCard card: creditCards) {
	            creditCardIngestionTransaction = new CreditCardIngestionTransaction();
	            BankOperator bankOperator = new BankOperator();
	            bankOperator.setId(card.getId());
	            creditCardIngestionTransaction.setBankOperator(bankOperator);
	            // card.setCreditcardIngestionTransaction(creditCardIngestionTransaction);
	          }
	          saveAllCreditCards = saveCreditCards(creditCards);
	          if (saveAllCreditCards != null && saveAllCreditCards.size() > 0) {
	            LOG.info("Inserted Sucessfully Because Of Successful response from WS");
	            responseModel = Utility.getResponse(true, saveAllCreditCards.size() + " " + RECORD_INGESTION_SUCCESS, null);
	          } else {
	            LOG.info(" Failed to Insert due to unseccessful response from WS");
	            responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
	          }
	        } catch(DataIntegrityViolationException e) {
	          responseModel = Utility.getResponse(false, RECORD_DUPLICATE, null);
	        }
	        LOG.info("Exiting saveOrUpdate of service with response ", responseModel);

	      } else {
	        responseModel = Utility.getResponse(false, errorMessage, null);
	      }

	    } catch(Exception e) {
	      e.printStackTrace();
	      responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
	      LOG.error("Exception in CreditCard Update", e);
	    }
	    return responseModel;
	  }

	  // CreditCards Loading
	  @Override public ResponseModel creditCardsLoading(List < CreditCard > creditCards) {
	    LOG.info("Inside creditCardsLoading of service with request parameter ", creditCards);
	    ResponseModel responseModel = null;
	    List < CreditCard > loadAllCreditCards = null;
	    List < CreditCard > updateCreditCard = null;
	    CreditCard findCreditCard = null;
	    Boolean isSuccess = false;
	    EsecuzioneOperazioneOutputData esecuzioneOperazione = null;
	    try {
	      String
	      errorMessage = Utility.validatecreditCardsLoadingRequest(creditCards);
	      if (errorMessage == null) {
	        LOG.info("Getting CreditCard by numCard and Updating CreditCard ", creditCards);
	        updateCreditCard = new ArrayList < >();
	        for (CreditCard creditcard: creditCards) {
	          if (!StringUtils.isEmpty(creditcard.getCodicePan())) { // call CSE WS for
	            Second scenario Optional < CreditCard > result = creditCardRepository.findByCodicePan(creditcard.getCodicePan());
	            if (result.isPresent()) {
	              findCreditCard = result.get();
	              esecuzioneOperazione = bgpointCseUtility.esecuzioneOperazione2(findCreditCard);
	              if (esecuzioneOperazione.isSuccess()) {
	                Integer idAzione = esecuzioneOperazione.getDettOperazione().getAzioneModuliInBiancoOut().
	                getRilascioCarnet().getCabAssegno();
	                Integer numCard = esecuzioneOperazione.getDettOperazione().getAzioneModuliInBiancoOut().
	                getRilascioCarnet().getCinPrimoAssegno();
	                findCreditCard.setCodicePan(Long.valueOf(numCard));
	                CreditCardLoadingTransaction creditCardLoadingTransaction = new
	                CreditCardLoadingTransaction();

	                BankOperator bankOperator = new BankOperator();
	                bankOperator.setOperatorId(String.valueOf(findCreditCard.getId()));
	                creditCardLoadingTransaction.setBankOperator(bankOperator);

	                //findCreditCard.setCreditCardloadingTransaction(
	                creditCardLoadingTransaction);
	                updateCreditCard.add(findCreditCard);
	                isSuccess = true;
	              } else { //through API error } }

	                findCreditCard = null;
	                LOG.info("WS returns unseccessful response");
	              }
	            }
	            if (!StringUtils.isEmpty(updateCreditCard) && isSuccess) {
	              LOG.info("Updating Creditcards After getting the creditcard by numCard ", creditCards);
	              loadAllCreditCards = saveCreditCards(loadAllCreditCards);
	              if (loadAllCreditCards != null && loadAllCreditCards.size() > 0) {
	                LOG.info("Updating SucessFully Because Of Successful response from WS");
	                responseModel = Utility.getResponse(true, loadAllCreditCards.size() + " " + RECORD_LOADING_STATUS_SUCCESS, null);
	              } else {
	                LOG.info("Updating failed due to unseccessful response from WS");
	                responseModel = Utility.getResponse(false, RECORD_LOADING_STATUS_FAILED, null);
	              }
	            } else {

	              if (esecuzioneOperazione != null && esecuzioneOperazione.getMessages() != null && esecuzioneOperazione.getMessages().size() > 0) {
	                MsgT
	                messages = esecuzioneOperazione.getMessages().get(0);
	                LOG.info("Loading failed due to unseccessful response from WS");
	                responseModel = Utility.getResponse(false, messages.getDesc(), null);
	              } else {
	                LOG.info("Laoding failed due to unseccessful response from WS");
	                responseModel = Utility.getResponse(false, RECORD_INGESTION_FAILED, null);
	              }
	            }
	          } else {
	            responseModel = Utility.getResponse(false, errorMessage, null);
	          }

	        } catch(Exception e) {
	          LOG.error("Exception in CreditCard Laoding", e);
	          responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
	        }
	        System.out.println(responseModel.getMessage());
	        return responseModel;
	      }
	 */
	/*
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	private List<CreditCard> saveCreditCards(List<CreditCard> creditcards) {
		return creditCardRepository.save(creditcards);
	}
	 */


	@Override
	public Optional<CreditCard> findByCreditCardNumber(Long codicePan) {
		Optional<CreditCard> creditcard = creditCardRepository.findByCodicePan(codicePan);
		return creditcard;
	}


	@Override
	public boolean isCreditCardExist(Long id) {
		return creditCardRepository.existsById(id);
	}

	@Override
	public ResponseModel searchCreditCard(String searchKeyword) {
		LOG.info(" Inside searchCreditCard  with request parameter searchKeyword {}.  ",searchKeyword );
		ResponseModel responseModel = null;
		try {
			List<CreditCard> results = creditCardRepository.searchCreditCard(searchKeyword);
			if (results != null && results.size() > 0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, results);
				LOG.info(" Successfully Searched CreditCards :",results.size());
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
				LOG.info(" Failed to = Searched CreditCards :",searchKeyword);
			}
			LOG.info(" Inside searchCreditCard  with request parameter searchKeyword {}.  ",searchKeyword );
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in searchCreditCard :",e);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getTransactionsByCreditCardIdOrChequeBookId(String entity, String transaction, Long entityId) {
		LOG.info("Inside getTransactionsByCreditCardIdOrChequeBookId with Request parameters  entity{}.  transactiontype{}. and  entityId{}. :", entity, transaction,entityId  );
		ResponseModel responseModel = null;
		List<Transactions> transactions = null;
		try {
			// these are changable method
			if (entity.equalsIgnoreCase(CHEQUE_BOOK)) {
				switch (transaction) {
				case CHEQUE_BOOK_AND_CARD_INGETION:
					transactions = chequeBooksIngestionTransactionRepository.getChequeBookIngetionTransactionsByChequeBookId(entityId);
					LOG.info(" Getting  ChequeBook Ingestion  Transactions :", transactions!=null?transactions.size():-1);

					break;

				case CHEQUE_BOOK_AND_CARD_LOADING:
					transactions = chequeBooksLoadingTransactionRepository.getChequeBookLoadingTransactionsByChequeBookId(entityId);
					LOG.info(" Getting  ChequeBook Loading  Transactions :", transactions!=null?transactions.size():-1);

					break;

				case CHEQUE_BOOK_AND_CARD_DELIVERY:
					transactions = chequeBooksDeliveryTransactionRepository.getChequeBookDeliveryTransactionsByChequeBookId(entityId);
					LOG.info(" Getting  ChequeBook Delivery  Transactions :", transactions!=null?transactions.size():-1);

					break;

				default:
					break;
				}
			} else {
				switch (transaction) {
				case CHEQUE_BOOK_AND_CARD_INGETION:
					transactions = creditCardIngestionTransactionRepository.getCreditCardIngetionsTransactionByCreditCardId(entityId);
					LOG.info(" Getting  CreditCards Ingestion  Transactions :", transactions!=null?transactions.size():-1);
					break;

				case CHEQUE_BOOK_AND_CARD_LOADING:
					transactions = creditCardLoadingTransactionRepository.getCreditCardLoadingTransactionsByCreditCardId(entityId);
					LOG.info(" Getting CreditCards Loading Transaction :", transactions!=null?transactions.size():-1);
					break;

				case CHEQUE_BOOK_AND_CARD_DELIVERY:
					transactions = creditCardDeliveryTransactionRepository.getCreditCardDeliveryTransactionsByCreditCardId(entityId);
					LOG.info(" Getting Creditcards Delivery Transactins :", transactions!=null?transactions.size():-1);
					break;

				default:
					break;
				}
			}

			if (transactions != null && transactions.size() > 0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, transactions);
				LOG.info(" Getting ChequeBookTransactions And CreditCardsTransactions :", transactions.size());

			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
			}
			LOG.info("Inside getTransactionsByCreditCardIdOrChequeBookId with Request parameters  entity{}.  transactiontype{}. and  entityId{}. :", entity, transaction,entityId  );
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in getTransactionsByCreditCardIdOrChequeBookId :", e);
		}
		return responseModel;
	}

	@Override
	public ResponseModel searchCreditCardAndChequeBookTransactions(String entity, String transaction,String searchKeyword) {
		LOG.info("Inside searchCreditCardAndChequeBookTransactions With Request Parameters entity {}. TransactionType {}. and search keword is {}. :",entity, transaction, searchKeyword);
		ResponseModel responseModel = null;
		List<Transactions> transactions = null;
		try {
			// these are changable method
			if (entity.equalsIgnoreCase(CHEQUE_BOOK)) {
				switch (transaction) {
				case CHEQUE_BOOK_AND_CARD_INGETION:
					transactions = chequeBooksIngestionTransactionRepository.searchChequeBookIngetionTransactions(searchKeyword);
					LOG.info(" Searched Result of ChequeBookIngestionTransaction :", transactions!=null?transactions.size():-1);

					break;

				case CHEQUE_BOOK_AND_CARD_LOADING:
					transactions = chequeBooksLoadingTransactionRepository.searchChequeBookLoadingTransactions(searchKeyword);
					LOG.info(" Searched Result of ChequeBookLoadingTransaction :", transactions!=null?transactions.size():-1);

					break;

				case CHEQUE_BOOK_AND_CARD_DELIVERY:
					transactions = chequeBooksDeliveryTransactionRepository.searchChequeBookDeliveryTransactions(searchKeyword);
					LOG.info(" Searched Result of ChequeBookDeliveryTransaction :", transactions!=null?transactions.size():-1);

					break;

				default:
					break;
				}
			} else {
				switch (transaction) {
				case CHEQUE_BOOK_AND_CARD_INGETION:
					transactions = creditCardIngestionTransactionRepository.searchCreditCardIngetionTransactions(searchKeyword);
					LOG.info(" Searched Result of CreditCardsIngestionTransaction :", transactions!=null?transactions.size():-1);

					break;

				case CHEQUE_BOOK_AND_CARD_LOADING:
					transactions = creditCardLoadingTransactionRepository.searchCreditCardLoadingTransactions(searchKeyword);
					LOG.info(" Searched Result of CreditCardsLoadingTransaction :", transactions!=null?transactions.size():-1);

					break;

				case CHEQUE_BOOK_AND_CARD_DELIVERY:
					transactions = creditCardDeliveryTransactionRepository.searchCreditCardDeliveryTransactions(searchKeyword);
					LOG.info(" Searched Result of CreditCardsDeliveryTransaction :", transactions!=null?transactions.size():-1);
					break;

				default:
					break;
				}
			}

			if (transactions != null && transactions.size() > 0) {
				responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, transactions);
			} else {
				responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
			}
			LOG.info("Exiting searchCreditCardAndChequeBookTransactions With Request Parameters entity {}. TransactionType {}. and search keword is {}. :",entity, transaction, searchKeyword);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info(" Exception in searchCreditCardAndChequeBookTransactions: ",e);
		}
		return responseModel;
	}

	@Override
	public ResponseModel getCreditCardOrChequeBookBySmartDeskId(String entity, Long smartdeskId) {
		LOG.info("Inside getCreditCardOrChequeBookBySmartDeskId with request Parameters entity {}. smartdeskId{}." ,entity,smartdeskId );
		ResponseModel responseModel = null;
		List<ChequeBook> chequeBooks = null;
		List<CreditCard> creditCards = null;
		try {
			// these are changable method
			switch (entity) {
			case CHEQUE_BOOK:
				chequeBooks = chequeBookRepository.getChequeBooksBySmartDeskId(smartdeskId);
				if (chequeBooks != null && chequeBooks.size() > 0) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, chequeBooks);
					LOG.info(" Successfully getting Chequebooks By SmartDeskId ",chequeBooks.size() );
				} else {
					responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
					LOG.info(" Failed to  get Chequebooks By SmartDeskId ",entity,smartdeskId);
				}
				break;

			case CREDIT_CARD:
				creditCards = creditCardRepository.getCreditCardBySmartDeskId(smartdeskId);
				if (creditCards != null && creditCards.size() > 0) {
					responseModel = Utility.getResponse(true, RECORD_FIND_STATUS_SUCCESS, creditCards);
					LOG.info(" Successfully getting Creditcards By SmartDeskId :",creditCards.size());
				} else {
					responseModel = Utility.getResponse(false, RECORD_FIND_STATUS_FAILED, null);
					LOG.info(" Failed to get Creditcards By SmartDeskId :" ,entity,smartdeskId);

				}
				break;

			default:
				break;
			}
			LOG.info("Inside getCreditCardOrChequeBookBySmartDeskId with request Parameters entity {}. smartdeskId{}." ,entity,smartdeskId );
		} catch (Exception e) {
			e.printStackTrace();
			responseModel = Utility.getResponse(false, SYSTEM_EXCEPTION, null);
			LOG.info("Exception in getCreditCardOrChequeBookBySmartDeskId ",e);
		}
		return responseModel;

	}
}
