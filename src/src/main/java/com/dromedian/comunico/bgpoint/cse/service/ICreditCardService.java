package com.dromedian.comunico.bgpoint.cse.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.dromedian.bgpoint.hb.WZApplicationException_Exception;
import com.dromedian.comunico.bgpoint.cse.dto.CreditCardDeliveryDTO;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCard;
/**
*
* @author Waseem
*/
@Service
public interface ICreditCardService {

	public CreditCard insertCreditCard(CreditCard creditcard);

	public List<CreditCard> saveOrUpdate(List<CreditCard> creditcard, Map<String, String> headers, Boolean isUpdatable)
			throws WZApplicationException_Exception;

	public List<CreditCard> findAllCreditCard();

	// Retrieve credit card by credit card Number

//	public Optional<CreditCard> findByCreditCardNumber(Long numCard);

	public boolean isCreditCardExist(Integer id);

	public CreditCard creditCardDelivery(CreditCardDeliveryDTO creditcardDelivery, Map<String, String> headers);
}
