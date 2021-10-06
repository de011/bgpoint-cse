package com.dromedian.comunico.bgpoint.cse.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dromedian.comunico.bgpoint.cse.dto.ChequeBookDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.dto.CreditCardDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.BankBranch;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCard;

/**
 * @author DEEPAK
 *
 */
@Service
@Transactional
public interface ChequeBookAndCreditCardService {
	/*public ResponseModel chequeBooksIngestion(List<ChequeBook> chequeBooks);
	public ResponseModel chequeBooksLoading(List<ChequeBook> chequeBooks);*/
	
	public ResponseModel chequeBookDelivery(ChequeBookDeliveryDTO chequeBookDelivery);
	public ResponseModel creditCardDelivery(CreditCardDeliveryDTO creditCardDelivery);
	public ResponseModel findAllChequeBook();
	public ResponseModel findAllCreditCard();
	//public Optional<ChequeBook> findChequeBookByCodiceUnivoco(Long codiceUnivoco);
	public boolean isChequeBookExist(Long id);
	public ResponseModel findAllChequeBookTransaction(String type);
	public ResponseModel findAllCredidCardTransaction(String type);
	

	public ResponseModel findChequeBookById(Long chequebookId);
	public ResponseModel findCreditCardById(Long creditcardId);
	
	public ResponseModel saveChequeBook(ChequeBook chequebook);
	public ResponseModel saveCreditCard(CreditCard creditcard);
	
	public ResponseModel updateChequeBook(ChequeBook chequebook);
	public ResponseModel updateCreditCard(CreditCard creditcard);

	
	
	

	public ResponseModel searchChequeBook(String searchKeyword);

	//++++++++++++++++++++++++++++++++ Credit Card Methods
	/*
	 * 
	public ResponseModel creditCradsIngestion(List<CreditCard> creditCards);
	public ResponseModel creditCardsLoading(List<CreditCard> creditCards);*/

	
	

	// Retrieve credit card by credit card Number

	public Optional<CreditCard> findByCreditCardNumber(Long numCarta);
	public boolean isCreditCardExist(Long id);

	
	
	

	
	

	public ResponseModel searchCreditCard(String searchKeyword);
	
	public ResponseModel getTransactionsByCreditCardIdOrChequeBookId(String entity, String transaction, Long entityId);
	
	public ResponseModel searchCreditCardAndChequeBookTransactions(String entity, String transaction, String searchKeyword);
	public ResponseModel getCreditCardOrChequeBookBySmartDeskId(String entity, Long smartdeskId);
	
}
