package com.dromedian.comunico.bgpoint.cse.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dromedian.bgpoint.hb.WZApplicationException_Exception;
import com.dromedian.comunico.bgpoint.cse.dto.ChequeBookDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBook;

/**
 * @author DEEPAK
 *
 */
@Service
public interface IChequeBookService {

	// Inserting the ChequeBook
	public ChequeBook insertChequeBook(ChequeBook chequebook);

	// inserting bulk ChequeBook

	public List<ChequeBook> saveOrUpdate(List<ChequeBook> chequebooks, Map<String, String> headers ,Boolean isUpdatable) throws WZApplicationException_Exception;
	

	//  Retrieving  all chequeBooks
	
	public  List<ChequeBook> findAllChequeBook();
	
	// Retrieve chequeBook by codiceUnivoco
	
   public  Optional<ChequeBook> findChequeBookByCodiceUnivoco(String codiceUnivoco);

	public boolean isChequeBookExist(Integer id);

	public ChequeBook chequebookDelivery(ChequeBookDeliveryDTO chequeBookDelivery, Map<String, String> headers);


}


