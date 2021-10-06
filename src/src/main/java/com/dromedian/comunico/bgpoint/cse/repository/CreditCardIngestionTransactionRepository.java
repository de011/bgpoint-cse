package com.dromedian.comunico.bgpoint.cse.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.CreditCardIngestionTransaction;
/**
*
* @author Waseem
*/
@Repository
public interface CreditCardIngestionTransactionRepository extends JpaRepository<CreditCardIngestionTransaction, Long>{


	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(ccit.id, ccit.bankOperator.id as bankOperatorId,ccit.creditCard.id as creditCardId, ccit.status ) "
			+ "from  CreditCardIngestionTransaction ccit where ccit.creditCard.id =:creditCardId")
	List<Transactions> getCreditCardIngetionsTransactionByCreditCardId(@Param("creditCardId") Long creditCardId);


	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(ccit.id, ccit.bankOperator.id as bankOperatorId,ccit.creditCard.id as creditCardId, ccit.status ) "
			+ "from  CreditCardIngestionTransaction ccit where "
			+ "ccit.creditCard.id like CONCAT ('%', :searchKeyword, '%')"
			+" or ccit.id like CONCAT ('%', :searchKeyword, '%')"
			+" or ccit.bankOperator.id like CONCAT ('%', :searchKeyword, '%')"
			+" or ccit.status like CONCAT ('%',:searchKeyword, '%' )"
			+ " order by ccit.createdOn desc ")
	List<Transactions> searchCreditCardIngetionTransactions(@Param("searchKeyword") String searchKeyword);

}
