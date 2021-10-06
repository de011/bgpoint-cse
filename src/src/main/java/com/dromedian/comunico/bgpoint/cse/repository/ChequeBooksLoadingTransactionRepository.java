package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBooksLoadingTransaction;

public interface ChequeBooksLoadingTransactionRepository extends JpaRepository<ChequeBooksLoadingTransaction,Long>{
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cblt.id,cblt.status, cblt.bankOperator.id as bankOperatorId,cblt.chequeBook.id as chequeBookId, cblt.smartDesk.id as smartDeskId ) "
			+ "from  ChequeBooksLoadingTransaction cblt where cblt.chequeBook.id =:chequeBookId")
	List<Transactions> getChequeBookLoadingTransactionsByChequeBookId(@Param("chequeBookId") Long chequeBookId);

	
	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cblt.id,cblt.status, cblt.bankOperator.id as bankOperatorId,cblt.chequeBook.id as chequeBookId, cblt.smartDesk.id as smartDeskId ) "
			+ "from  ChequeBooksLoadingTransaction cblt where "
			+ "cblt.chequeBook.id like CONCAT ('%', :searchKeyword, '%')"
			+" or cblt.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cblt.status like CONCAT ('%',:searchKeyword, '%')"
			+" or cblt.bankOperator.id like CONCAT ('%',:searchKeyword, '%' )"
			+" or  cblt.chequeBook.id like CONCAT ('%',:searchKeyword, '%')"
			+ " or cblt.smartDesk.id like CONCAT ('%',:searchKeyword, '%')"
			+ "order by cblt.createdOn desc")
	List<Transactions> searchChequeBookLoadingTransactions(@Param("searchKeyword") String searchKeyword);
	
}
