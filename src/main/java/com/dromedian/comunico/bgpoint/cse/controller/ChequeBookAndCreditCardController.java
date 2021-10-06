package com.dromedian.comunico.bgpoint.cse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dromedian.comunico.bgpoint.cse.dto.ChequeBookDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.dto.CreditCardDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCard;
import com.dromedian.comunico.bgpoint.cse.service.ChequeBookAndCreditCardService;
import com.dromedian.comunico.bgpoint.cse.util.CSEConstants;

/**
 * @author DEEPAK
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/dromedian")
public class ChequeBookAndCreditCardController implements CSEConstants {
	private static final Logger LOG = LoggerFactory.getLogger(ChequeBookAndCreditCardController.class);

	@Autowired
	private ChequeBookAndCreditCardService chequeBookAndCreditCardService;

	/*
	 * // Chequebook Ingestion
	 * 
	 * @PostMapping("/ingestion/chequebooks") public
	 * ResponseEntity<ResponseModel> chequeBooksIngesion(@RequestBody
	 * List<ChequeBook> chequeBooks) { ResponseEntity<ResponseModel>
	 * responseEntity = null; ResponseModel responseModel = null;
	 * LOG.info("Inside savechequebooks Of Controller with request parameter ",
	 * chequeBooks); try { responseModel =
	 * chequeBookService.chequeBooksIngestion(chequeBooks); responseEntity = new
	 * ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	 * LOG.info("ChequeBooks  created Successfully!"); }catch (Exception e) {
	 * LOG.info("Failed to Save the ChequeBook"); responseEntity = new
	 * ResponseEntity<ResponseModel>(responseModel,HttpStatus.
	 * INTERNAL_SERVER_ERROR); }
	 * LOG.info("Exiting saveCreditcards Of Controller with response ",
	 * responseEntity); return responseEntity; }
	 * 
	 * // CreditCards Ingestion
	 * 
	 * @PostMapping("/ingestion/creditcards") public
	 * ResponseEntity<ResponseModel> creditCradsIngestion(@RequestBody
	 * List<CreditCard> creditcards) { LOG.
	 * info("Inside creditCradsIngestion Of Controller with request parameter ",
	 * creditcards); ResponseEntity<ResponseModel> responseEntity = null;
	 * ResponseModel responseModel = null; try { responseModel =
	 * creditCardService.creditCradsIngestion(creditcards); responseEntity = new
	 * ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	 * LOG.info("ChequeBooks  created Successfully!"); }catch (Exception e) {
	 * LOG.info("Failed to Save the ChequeBook"); responseEntity = new
	 * ResponseEntity<ResponseModel>(responseModel,HttpStatus.
	 * INTERNAL_SERVER_ERROR); }
	 * System.out.println("++++++++++++++++++++++++++++++++ "+responseEntity.
	 * getBody().getMessage());
	 * LOG.info("Exiting creditCradsIngestion Of Controller with response ",
	 * responseEntity); return responseEntity; }
	 * 
	 */

	/*
	 * //Chequbooks Loading
	 * 
	 * @PostMapping("/loading/chequebooks") public ResponseEntity<ResponseModel>
	 * chequeBooksLoading(@RequestBody List<ChequeBook> chequeBooks) {
	 * LOG.info("Inside updateChequeBooks Of Controller with request parameter "
	 * , chequeBooks); ResponseEntity<ResponseModel> responseEntity = null;
	 * ResponseModel responseModel = null; try { responseModel =
	 * chequeBookService.chequeBooksLoading(chequeBooks); responseEntity = new
	 * ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	 * LOG.info("Chequebooks  updated Successfully!"); } catch (Exception e) {
	 * responseEntity = new ResponseEntity<ResponseModel>(responseModel,
	 * HttpStatus.INTERNAL_SERVER_ERROR);
	 * LOG.info("Failed to update the ChequeBooks"); e.printStackTrace(); }
	 * LOG.info("Exiting updateChequeBooks Of Controller with response ",
	 * responseEntity); return responseEntity; }
	 * 
	 * // Update CreditCards
	 * 
	 * @PostMapping("/loading/creditcards") public ResponseEntity<ResponseModel>
	 * creditCardsLoading(@RequestBody List<CreditCard> creditCards) { LOG.
	 * info("Inside creditCardsLoading Of Controller with request parameter ",
	 * creditCards); ResponseEntity<ResponseModel> responseEntity = null;
	 * ResponseModel responseModel = null; try { responseModel =
	 * creditCardService.creditCardsLoading(creditCards); responseEntity = new
	 * ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	 * LOG.info("creditcards  Loaded Successfully!"); } catch (Exception e) {
	 * responseEntity = new
	 * ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
	 * LOG.info("Failed to Save the Load "); e.printStackTrace(); }
	 * LOG.info("Exiting creditCardsLoading Of Controller with response ",
	 * responseEntity); return responseEntity; }
	 * 
	 */

	// Chequebooks Delivery
	@PostMapping("/delivery/chequebook")
	public ResponseEntity<ResponseModel> chequebookDelivery(@RequestBody ChequeBookDeliveryDTO chequeBookDelivery) {
		LOG.info("Inside chequebookDelivery Of Controller with request parameter ", chequeBookDelivery);
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		try {
			responseModel = chequeBookAndCreditCardService.chequeBookDelivery(chequeBookDelivery);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info("Chequebooks  Delivered Successfully! :", chequeBookDelivery.toString());
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(responseModel,HttpStatus.INTERNAL_SERVER_ERROR.ACCEPTED);
			LOG.info("Failed to Delivered the ChequeBooks", chequeBookDelivery);
			e.printStackTrace();
		}
		LOG.info("Exiting chequebookDelivery Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// DeliverCreditcards
	@PostMapping("/delivery/creditcard")
	public ResponseEntity<ResponseModel> creditcardDelivery(@RequestBody CreditCardDeliveryDTO creditCardDelivery) {
		LOG.info("Inside creditcardDelivery Of Controller with request parameter ", creditCardDelivery);
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		try {
			responseModel = chequeBookAndCreditCardService.creditCardDelivery(creditCardDelivery);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info("CreditCards  Delivered Successfully!",responseEntity.toString());
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Delivered the Creditcards",responseEntity);
			e.printStackTrace();
		}
		LOG.info("Exiting creditcardDelivery Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// All Chequebooks

	@GetMapping("find/chequebooks")
	public ResponseEntity<ResponseModel> getAllChequeBook() {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside getAllChequeBook Of Controller with without request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.findAllChequeBook();
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetching All ChequeBooks Record Successfully! :",responseEntity.toString());
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Fetching ChequeBooks Records", responseEntity);
			e.printStackTrace();
		}
		LOG.info("Exiting getAllChequeBook Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// find All Credit Card
	@GetMapping("/find/creditcards")
	public ResponseEntity<ResponseModel> getAllCreditCard() {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside getAllCreditCard Of Controller without request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.findAllCreditCard();
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetching All  CreditCards Records Successfully! :", responseEntity.toString());
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Fetching CreditCards! :", responseEntity);
			e.printStackTrace();
		}
		LOG.info("Exiting getAllCreditCard Of Controller with response :", responseEntity);
		return responseEntity;
	}

	@GetMapping("/find/chequebook/transaction/{type}")
	public ResponseEntity<ResponseModel> findAllChequeBookTransaction(@PathVariable("type") String type) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findAllChequeBookTransaction Of Controller with request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.findAllChequeBookTransaction(type);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetching All Chequebooks transcations Successfully!", responseEntity.toString());
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Fetching Chequebooks transactions by type :", responseEntity);
			e.printStackTrace();
		}
		LOG.info("Exiting findAllChequeBookTransaction Of Controller with response ", responseEntity);
		return responseEntity;
	}

	@GetMapping("/find/creditcard/transaction/{type}")
	public ResponseEntity<ResponseModel> findAllCredidCardTransaction(@PathVariable("type") String type) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findAllCredidCardTransaction Of Controller with request parameter  type{} . :", type);
		try {
			responseModel = chequeBookAndCreditCardService.findAllCredidCardTransaction(type);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetching CreditCards Transactions Successfully!",responseEntity.toString());
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Fetch CreditCards Transactions :",responseEntity);
			e.printStackTrace();
		}
		LOG.info("Exiting findAllCredidCardTransaction Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Get All Chequebook By Id
	@GetMapping("/find/chequebook/byid/{id}")
	public ResponseEntity<ResponseModel> findChequeBookById(@PathVariable("id") Long chequebookId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findChequeBookById Of Controller with request parameter chequebookId{}. :",chequebookId);
		try {
			responseModel = chequeBookAndCreditCardService.findChequeBookById(chequebookId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting Chequebooks Successfully  by findChequeBookById :",responseEntity.toString() );
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get Chequebooks by findChequeBookById",responseEntity);
			e.printStackTrace();
		}
		LOG.info("Exiting findChequeBookById Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Get All CreditCard By Id
	@GetMapping("/find/creditcard/byid/{id}")
	public ResponseEntity<ResponseModel> findCreditCardById(@PathVariable("id") Long creditcardId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside findCreditCardById Of Controller with request parameter creditcard{}. creditcardId{} . :",creditcardId);
		try {
			responseModel = chequeBookAndCreditCardService.findCreditCardById(creditcardId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting CreditCards Records  Successfully by findCreditCardById ");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get Creditcard Records by findCreditCardById");
			e.printStackTrace();
		}
		LOG.info("Exiting findCreditCardById Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Chequebook Ingestion
	@PostMapping("/ingestion/chequebooks")
	public ResponseEntity<ResponseModel> chequeBooksIngesion(@RequestBody ChequeBook chequeBooks) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside chequeBooksIngesion Of Controller with request parameter ", chequeBooks);
		try {
			if (chequeBooks != null && chequeBooks.getId() != null && chequeBooks.getId() > 0) {
				responseModel = chequeBookAndCreditCardService.updateChequeBook(chequeBooks);
				LOG.info("ChequeBooks  Updated Successfully! :",chequeBooks.toString());
			} else {
				responseModel = chequeBookAndCreditCardService.saveChequeBook(chequeBooks);
				LOG.info("ChequeBooks  Created  Successfully! :",chequeBooks.toString());

			}
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
		} catch (Exception e) {
			LOG.info("Failed to Save the ChequeBook");
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("Exiting chequeBooksIngesion Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// CreditCards Ingestion
	@PostMapping("/ingestion/creditcards")
	public ResponseEntity<ResponseModel> creditcardsIngesion(@RequestBody CreditCard creditCards) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside creditcardsIngesion Of Controller with request parameter ", creditCards);
		try {
			if (creditCards != null && creditCards.getId() != null && creditCards.getId() > 0) {
				responseModel = chequeBookAndCreditCardService.updateCreditCard(creditCards);
			} else {
				responseModel = chequeBookAndCreditCardService.saveCreditCard(creditCards);
			}
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info("CreditCards  created Successfully!",creditCards.toString());
		} catch (Exception e) {
			LOG.info("Failed to create the CreditCards",creditCards);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("Exiting creditcardsIngesion Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Find ChequeBooks By Id
	@GetMapping("/search/chequebooks/{searchKeyword}")
	public ResponseEntity<ResponseModel> searchChequeBook(@PathVariable("searchKeyword") String searchKeyword) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside SearchchequBook Of Controller with request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.searchChequeBook(searchKeyword);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting ChequeBooks  Successfully by searchChequeBook ");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get ChequeBooks by searchChequeBook");
			e.printStackTrace();
		}
		LOG.info("Exiting searchChequeBook Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Search Credit Cards
	@GetMapping("/search/creditcards/{searchKeyword}")
	public ResponseEntity<ResponseModel> searchCreditCards(@PathVariable("searchKeyword") String searchKeyword) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside searchCreditCards Of Controller with request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.searchCreditCard(searchKeyword);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting Creditcards Records Successfully! By SearchByKeywords");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get CreditCards Records by SearchByKeywords");
			e.printStackTrace();
		}
		LOG.info("Exiting searchCreditCards Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// Chequebook or Creditcard transactions
	// Entity means chequeBook or Credit Card
	@GetMapping("/find/transaction/{entity}/{transaction}/{entityId}")
	public ResponseEntity<ResponseModel> getTransactionsByCreditCardIdOrChequeBookId(
			@PathVariable("entity") String entity, @PathVariable("transaction") String transaction,
			@PathVariable("entityId") Long entityId) {
		LOG.info("Inside creditcardsIngesion Of Controller with request parameter ", entity, transaction, entityId);
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside getTransactionsByCreditCardIdOrChequeBookId Of Controller with request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.getTransactionsByCreditCardIdOrChequeBookId(entity,
					transaction, entityId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Fetched ChequeBooks Or CareditCards Transaction By ChequeBookId Or creditCardId   !");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info(" Failed to Fetch ChequeBooks Or CareditCards Transaction By ChequeBookId Or creditCardId   !");
			e.printStackTrace();
		}
		LOG.info("Exiting getTransactionsByCreditCardIdOrChequeBookId Of Controller with response ", responseEntity);
		return responseEntity;
	}

	// search transaction of chequebook or creditacard
	@GetMapping("/search/transaction/{entity}/{transaction}/{searchKeyword}")
	public ResponseEntity<ResponseModel> searchCreditCardAndChequeBookTransactions(
			@PathVariable("entity") String entity, @PathVariable("transaction") String transaction,
			@PathVariable("searchKeyword") String searchKeyword) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside searchCreditCardAndChequeBookTransactions Of Controller with request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.searchCreditCardAndChequeBookTransactions(entity,
					transaction, searchKeyword);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting ChequebookAndCreaditcards Tarnsactions Successfully! By Searchkeywords");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info("Failed to Get ChequebookAndCreaditcards Tarnsactions Successfully! By Searchkeywords");
			e.printStackTrace();
		}
		LOG.info("Exiting searchCreditCardAndChequeBookTransactions Of Controller with response ", responseEntity);
		return responseEntity;
	}

	@GetMapping("/find/{entity}/bysmartdeskid/{smartdeskId}")
	public ResponseEntity<ResponseModel> getCreditCardOrChequeBookBySmartDeskId(@PathVariable("entity") String entity,
			@PathVariable("smartdeskId") Long smartdeskId) {
		ResponseEntity<ResponseModel> responseEntity = null;
		ResponseModel responseModel = null;
		LOG.info("Inside getCreditCardOrChequeBookBySmartDeskId Of Controller with request parameter ");
		try {
			responseModel = chequeBookAndCreditCardService.getCreditCardOrChequeBookBySmartDeskId(entity, smartdeskId);
			responseEntity = new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
			LOG.info(" Getting Chequebooks or Creditcards Successfully By SmartDeskId!");
		} catch (Exception e) {
			responseEntity = new ResponseEntity<ResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.info(" Failed to GetChequebooks or Creditcards Successfully By SmartDeskId!");
			e.printStackTrace();
		}
		LOG.info("Exiting getCreditCardOrChequeBookBySmartDeskId Of Controller with response ", responseEntity);
		return responseEntity;
	}
}