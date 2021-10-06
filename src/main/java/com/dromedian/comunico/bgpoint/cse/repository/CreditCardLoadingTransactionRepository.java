package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCardLoadingTransaction;

public interface CreditCardLoadingTransactionRepository extends JpaRepository<CreditCardLoadingTransaction, Long>{


	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cclt.id, cclt.bankOperator.id as bankOperatorId,cclt.creditCard.id as creditCardId, cclt.smartDesk.id as smartDeskId, cclt.status ) "
			+ "from  CreditCardLoadingTransaction cclt where cclt.creditCard.id =:creditCardId")
	List<Transactions> getCreditCardLoadingTransactionsByCreditCardId(@Param("creditCardId") Long creditCardId);


	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cclt.id, cclt.bankOperator.id as bankOperatorId,cclt.creditCard.id as creditCardId, cclt.smartDesk.id as smartDeskId, cclt.status ) "
			+ "from  CreditCardLoadingTransaction cclt where "
			+ "cclt.creditCard.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cclt.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cclt.bankOperator.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cclt.creditCard.id like CONCAT ('%',:searchKeyword, '%' )"
			+" or cclt.smartDesk.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cclt.status like CONCAT ('%',:searchKeyword, '%')"
	        + "order by cclt.createdOn desc")
	List<Transactions> searchCreditCardLoadingTransactions(@Param("searchKeyword") String searchKeyword);


}
