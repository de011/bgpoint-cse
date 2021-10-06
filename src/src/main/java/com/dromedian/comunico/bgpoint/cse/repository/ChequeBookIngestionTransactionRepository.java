package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBooksIngestionTransaction;
/**
*
* @author Waseem
*/
@Repository
public interface ChequeBookIngestionTransactionRepository extends JpaRepository<ChequeBooksIngestionTransaction, Long> {
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cbit.id,cbit.status, cbit.bankOperator.id as bankOperatorId,cbit.chequeBook.id as chequeBookId ) "
			+ "from  ChequeBooksIngestionTransaction cbit where cbit.chequeBook.id =:chequeBookId")
	List<Transactions> getChequeBookIngetionTransactionsByChequeBookId(@Param("chequeBookId") Long chequeBookId);
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cbit.id,cbit.status, cbit.bankOperator.id as bankOperatorId, cbit.chequeBook.id as chequeBookId ) " 
			+ "from  ChequeBooksIngestionTransaction cbit where "
			+ " cbit.chequeBook.id like CONCAT ('%', :searchKeyword, '%')"
			+" or cbit.id like CONCAT ('%', :searchKeyword, '%')"
			+" or cbit.status like CONCAT ('%', :searchKeyword, '%')"
			+" or  cbit.bankOperator.id like CONCAT ('%',:searchKeyword, '%' )"
			+ " order by cbit.createdOn desc ")
	List<Transactions> searchChequeBookIngetionTransactions(@Param("searchKeyword") String searchKeyword);

	
	
}
