package com.dromedian.comunico.bgpoint.cse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.ChequeBooksDeliveryTransaction;

public interface ChequeBooksDeliveryTransactionRepository extends JpaRepository<ChequeBooksDeliveryTransaction, Long>{
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cbdt.id,cbdt.status, cbdt.bankOperator.id as bankOperatorId,cbdt.chequeBook.id as chequeBookId, cbdt.smartDesk.id as smartDeskId, cbdt.bankClient.id as bankclientId ) "
			+ "from  ChequeBooksDeliveryTransaction cbdt where cbdt.chequeBook.id =:chequeBookId")
	List<Transactions> getChequeBookDeliveryTransactionsByChequeBookId(@Param("chequeBookId") Long chequeBookId);

	@Query("select new com.dromedian.comunico.bgpoint.cse.dto.Transactions(cbdt.id,cbdt.status, cbdt.bankOperator.id as bankOperatorId,cbdt.chequeBook.id as chequeBookId, cbdt.smartDesk.id as smartDeskId, cbdt.bankClient.id as bankclientId ) "
			+ "from  ChequeBooksDeliveryTransaction cbdt where "
			+ "cbdt.chequeBook.id like CONCAT ('%', :searchKeyword, '%')"
			+" or cbdt.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cbdt.status like CONCAT ('%',:searchKeyword, '%')"
			+" or cbdt.bankOperator.id like CONCAT ('%',:searchKeyword, '%' )"
			+" or  cbdt.chequeBook.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cbdt.smartDesk.id like CONCAT ('%',:searchKeyword, '%')"
			+" or cbdt.bankClient.id like CONCAT ('%',:searchKeyword, '%')"
			+" order by cbdt.createdOn desc")
		List<Transactions> searchChequeBookDeliveryTransactions(@Param("searchKeyword") String searchKeyword);
}
