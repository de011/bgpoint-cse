package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCardDeliveryTransaction;

public interface CreditCardDeliveryTransactionRepository extends JpaRepository<CreditCardDeliveryTransaction, Long>{

	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(ccdt.id, ccdt.bankOperator.id as bankOperatorId,ccdt.creditCard.id as creditCardId, ccdt.smartDesk.id as smartDeskId, ccdt.bankClient.id as bankclientId , ccdt.status) "
			+ "from  CreditCardDeliveryTransaction ccdt where ccdt.creditCard.id =:creditCardId")
	List<Transactions> getCreditCardDeliveryTransactionsByCreditCardId(@Param("creditCardId")  Long creditCardId);

	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(ccdt.id, ccdt.bankOperator.id as bankOperatorId,ccdt.creditCard.id as creditCardId, ccdt.smartDesk.id as smartDeskId, ccdt.bankClient.id as bankclientId , ccdt.status) "
			+ "from  CreditCardDeliveryTransaction ccdt where"
	+ " ccdt.creditCard.id like CONCAT ('%', :searchKeyword, '%')"
	+" or ccdt.id like CONCAT ('%',:searchKeyword, '%')"
	+" or ccdt.bankOperator.id like CONCAT ('%',:searchKeyword, '%')"
	+" or  ccdt.smartDesk.id like CONCAT ('%',:searchKeyword, '%' )"
	+" or  ccdt.bankClient.id like CONCAT ('%',:searchKeyword, '%')"
	+" or ccdt.status like CONCAT ('%',:searchKeyword, '%')"
	+" order by ccdt.createdOn desc")
	List<Transactions> searchCreditCardDeliveryTransactions(@Param("searchKeyword") String searchKeyword);
}
