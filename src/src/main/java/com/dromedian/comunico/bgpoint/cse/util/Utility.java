package com.dromedian.comunico.bgpoint.cse.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dromedian.comunico.bgpoint.cse.dto.ResponseModel;
import com.dromedian.comunico.bgpoint.cse.entity.BankBranch;
import com.dromedian.comunico.bgpoint.cse.entity.BankClient;
import com.dromedian.comunico.bgpoint.cse.entity.BankOperator;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCard;
import com.dromedian.comunico.bgpoint.cse.entity.SmartDesk;
import com.dromedian.comunico.bgpoint.cse.repository.ChequeBookRepository;
import com.dromedian.comunico.bgpoint.cse.repository.CreditCardRepository;

@Service
public class Utility {
	
	
	@Autowired
	ChequeBookRepository chequebookrepo;
	@Autowired
	CreditCardRepository creditcardrepo;
	
	// For SmartDesk validation
	public static String validateSmartDeskRequest(SmartDesk smartDesk) {
		String error = null;

			if (smartDesk.getExternalId() == null) {
				error = "Post Azione Id can't be null";
				return error;
			}
			if (smartDesk.getSmartDeskName() == null) {
				error = "SmartDesk name can't be null";
				return error;
			}
			if (smartDesk.getBankBranchId() == null) {
				error = "Branch id can't be null";
				return error;
			}
		return error;
	}

	// For Bank Branch Validation
	public static String validateBankBranchRequest(BankBranch bankbranch) {
		String error = null;
			if (bankbranch.getBranchName() == null) {
				error = "Id Branch can't be null";
				return error;
			}
		
		return error;
}

	// For BankOperator Validation
	public static String validateBankOperatorRequest(BankOperator bankoperator) {
		String error = null;
			if (bankoperator.getOperatorId() == null) {
				error = " Operator Id can't be null";
				return error;
			}
		return error;
	}

	// For BankClient Validation
	public static String validateBankClientRequest(BankClient bankclient) {
		String error = null;
			if (bankclient.getCircuito() == null) {
				error = "Circuito can't be null";
				return error;
			}
			if (bankclient.getUserName() == null) {
				error = " UserName can't be null";
				return error;
			}

			if (bankclient.getCdg() == null) {
				error = "CDG can't be Null";
				return error;
			}

			if (bankclient.getDes() == null) {
				error = " Des can't be null";
				return error;
			}

			if (bankclient.getCellCert() == null) {
				error = "cellcert can't be null";
				return error;
			}

			if (bankclient.getConcat() == null) {
				error = "Concat can't be null";
				return error;
			}

			if (bankclient.getConcnt() == null) {
				error = "Concnt can't be null";
				return error;
			}

			if (bankclient.getConfil() == null) {
				error = "Confill  can't be null";
				return error;
			}

			if (bankclient.getDataNascita() == null) {
				error = "DataNascita can't be null";
				return error;
			}

			if (bankclient.getGgMancanti() == null) {
				error = "GgMancanti can't be null";
				return error;
			}

			if (bankclient.getInddis() == null) {
				error = "Inddis can't be null";
				return error;
			}

			if (bankclient.getStatoppb() == null) {
				error = "Statoppb can't be null";
				return error;
			}
			if (bankclient.getUserId() == null) {
				error = "User Id can't be null";
				return error;
			}
		return error;
	}

	// validate for chequebooks ingestion request
	public  String validatechequeBooksIngesionRequest(ChequeBook chequebook) {
		List<Long> codiceUnivocoList = new ArrayList<>();
		List<Long> chequeBookDuplicateCheck = null;
		String error = null;
		//for (ChequeBook chequebook : chequeBooks) {
			if (chequebook.getRequestId() == null) {
				error = "Request Id can't be null";
				return error;
			}

			if (chequebook.getCabAssegno() == null) {
				error = "Cab Assegno can't be null and must be Integer";
				return error;
			}

			if (chequebook.getFilAssegno() == null) {
				error = "Fil  Assegno can't be null and must be Integer";
				return error;
			}

			if (chequebook.getNrPrimoAssegno() == null) {
				error = "Nr Primo Assegno can't be null and must be Integer";
				return error;
			}

			if (chequebook.getNrUltimoAssegno() == null) {
				error = "Nr Ultimo Assegno can't be null and must be Integer";
				return error;
			}

			if (chequebook.getNrAssegni() == null) {
				error = "Nr Assegni can't be null and must be Integer";
				return error;
			}

			if (chequebook.getCinPrimoAssegno() == null) {
				error = "Cin Primo Assegno can't be null and must be Integer";
				return error;
			}
			
			if (chequebook.getCodiceUnivoco() == null) {
				error = "Codice Univoco can't be null and must be Integer";
				return error;
			}
			codiceUnivocoList.add(chequebook.getCodiceUnivoco());
			if (chequebook.getCodCategoria() == null) {
				error = "Cod Categoria can't be null and must be Integer";
				return error;
			}

			if (chequebook.getCodGruppo() == null) {
				error = "Cod Gruppo can't be null and must be Integer";
				return error;
			}

			if (chequebook.getNumRapporto() == null) {
				error = "Num Rapporto can't be null and must be Integer";
				return error;
			}

			if (chequebook.getSerieAssegni() == null) {
				error = "Serie Assegni can't be null";
				return error;
			}
	//}
		
		if(codiceUnivocoList!=null && codiceUnivocoList.size()>0){
			chequeBookDuplicateCheck = chequebookrepo.getDuplicateChequeBook(codiceUnivocoList);
			if(chequeBookDuplicateCheck!=null && chequeBookDuplicateCheck.size()>0){
				error =  chequeBookDuplicateCheck.stream().map(String::valueOf).collect(Collectors.joining(","))+" codiceUnivoco is duplicate";
				return error;
			}
		}
		return error;
	}

	// validate for chequebooks loading request
	public static String validateChequeBooksLoadingRequest(List<ChequeBook> chequeBooks) {
		String error = null;
		for (ChequeBook chequebook : chequeBooks) {
			if (chequebook.getCodiceUnivoco() == null) {
				error = "Codice Univoco can't be null and must be Integer";
				return error;
			}
		}
		return error;
	}

	// validate for creditcards ingestion request

	public String validateCreditCradsIngesionRequest(CreditCard creditcard) {
		List<Long> numCardList = new ArrayList<>();
		List<Long> creditCardDuplicateCheck = null;
		String error = null;
		//for (CreditCard creditcard : creditCards) {
		
			/*if (creditcard.getIdPostazione() == null) {
				error = "Id Post Azione can't be null";
				return error;
			}
			if (creditcard.getRequestId() == null) {
				error = "Request Id can't be null";
				return error;
			}
			if (creditcard.getCdg() == null) {
				error = "Cdg can't be null";
				return error;
			}


			if (creditcard.getBranch() == null) {
				error = "Branch can't be null";
				return error;
			}
*/
			if (creditcard.getProd() == null) {
				error = "Prod can't be null";
				return error;
			}
			
			if (creditcard.getCodicePan() == null) {
				error = " NumCarta can't be null and must be Long";
				return error;
			}
			numCardList.add(creditcard.getCodicePan());
			
			if (creditcard.getProd() == null) {
				error = "Prod can't be null";
				return error;
			}
			/*
			

			if (creditcard.getCodCategoria() == null) {
				error = "Cod Categoria can't be null";
				return error;
			}

			if (creditcard.getCodGruppo() == null) {
				error = "CodGruppo can't be null";
				return error;
			}

			if (creditcard.getNumRapporto() == null) {
				error = "NumRapporto can't be null";
				return error;
			}

			if (creditcard.getCodAnagrafico() == null) {
				error = "CodAnagrafico can't be null";
				return error;
			}

			if (creditcard.getAzione() == null) {
				error = "Azione can't be null";
				return error;
			}

			if (creditcard.getSerieAssegni() == null) {
				error = "SerieAssegni can't be null";
				return error;
			}

			if (creditcard.getFilAssegno() == null) {
				error = "FilAssegno can't be null";
				return error;
			}

			if (creditcard.getCabAssegno() == null) {
				error = "CabAssegno can't be null";
				return error;
			}

			if (creditcard.getNrPrimoAssegno() == null) {
				error = "Nr Primo Assegno can't be null";
				return error;
			}

			if (creditcard.getNrUltimoAssegno() == null) {
				error = "Nr Ultimo Assegno can't be null";
				return error;
			}

			if (creditcard.getNrAssegni() == null) {
				error = "Nr Assegni can't be null";
				return error;
			}

			if (creditcard.getCinPrimoAssegno() == null) {
				error = "CinPrimo Assegno can't be null";
				return error;
			}

			if (creditcard.getTipoAzione() == null) {
				error = "TipoAzione can't be null";
				return error;
			}
*/			
		//}
		
		if(numCardList!=null && numCardList.size()>0){
			creditCardDuplicateCheck = creditcardrepo.getDuplicateCreditCards(numCardList);
			if(creditCardDuplicateCheck!=null && creditCardDuplicateCheck.size()>0){
				error =  creditCardDuplicateCheck.stream().map(String::valueOf).collect(Collectors.joining(","))+" NumCarta is duplicate";
				return error;
			}
		}
		return error;
	}

	// validate for creditcards loading request 
	public static String validatecreditCardsLoadingRequest(List<CreditCard> creditCards) {
		String error = null;
		for (CreditCard creditcard : creditCards) {
			if (creditcard.getCodicePan() == null) {
				error = "NumCarta can't be null and must be Integer";
				return error;
			}
		}
		return error;
	}
	
	public static ResponseModel getResponse(Boolean status, String message, Object data) {
		ResponseModel responseModel = new ResponseModel();
		try {
			responseModel.setStatus(status);
			responseModel.setMessage(message);
			responseModel.setData(data);
			responseModel.setTimeStamp(LocalDateTime.now());
		} catch (Exception e) {
			responseModel.setStatus(false);
			responseModel.setMessage(CSEConstants.SYSTEM_EXCEPTION);
			responseModel.setData(null);
			responseModel.setTimeStamp(LocalDateTime.now());
		}
		return responseModel;
	}
	
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
}
