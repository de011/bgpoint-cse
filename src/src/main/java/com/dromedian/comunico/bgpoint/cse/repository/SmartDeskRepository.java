package com.dromedian.comunico.bgpoint.cse.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.dto.Transactions;
import com.dromedian.comunico.bgpoint.cse.entity.SmartDesk;

@Repository
public interface SmartDeskRepository extends JpaRepository<SmartDesk, Long> {
	
	public Optional<SmartDesk> findByIdAndIsDeleted(Long id, boolean isDeleted);

	public Optional<SmartDesk> findBySmartDeskIdAndIsDeleted(Integer smartDeskId, boolean isDeleted);
	
	public boolean existsById(Long id);
	public SmartDesk findByIsDeleted(boolean isDeleted);
	
	public Optional<SmartDesk> findById(Long id);
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.SmartDesk(sd.id, sd.smartDeskId, sd.bankBranch, sd.smartDeskName, sd.externalId,sd.isDeleted, "
			+ "(select count(cblt.smartDesk.smartDeskId) from ChequeBooksLoadingTransaction cblt where cblt.smartDesk.smartDeskId = sd.smartDeskId ) as noOfChequebook," + 
			" (select count(cclt.smartDesk.smartDeskId) from CreditCardLoadingTransaction cclt where cclt.smartDesk.smartDeskId = sd.smartDeskId )  as noOfCards) "
			+ "from SmartDesk sd where sd.bankBranch.id =:branchId and sd.isDeleted = false order by sd.createdOn desc ")
	public List<SmartDesk> getSmartDeskByBankBranchId(@Param("branchId") Long branchId);
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.SmartDesk(sd.id, sd.smartDeskId, sd.bankBranch, sd.smartDeskName, sd.externalId,sd.isDeleted, "
			+ "(select count(cblt.smartDesk.smartDeskId) from ChequeBooksLoadingTransaction cblt where cblt.smartDesk.smartDeskId = sd.smartDeskId ) as noOfChequebook," + 
			" (select count(cclt.smartDesk.smartDeskId) from ChequeBooksLoadingTransaction cclt where cclt.smartDesk.smartDeskId = sd.smartDeskId )  as noOfCards) "
			+ "from SmartDesk sd, ChequeBooksLoadingTransaction cblts where sd.id =cblts.smartDesk.id"
			+ " and cblts.chequeBook.id=:chequeBookId and sd.isDeleted = false order by sd.createdOn desc")
	public List<SmartDesk> getSmartDeskByChequeBookId(@Param("chequeBookId") Long chequeBookId);
	
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.SmartDesk(sd.id, sd.smartDeskId, sd.bankBranch, sd.smartDeskName, sd.externalId,sd.isDeleted, "
			+ "(select count(cblt.smartDesk.smartDeskId) from ChequeBooksLoadingTransaction cblt where cblt.smartDesk.smartDeskId = sd.smartDeskId ) as noOfChequebook," + 
			" (select count(cclt.smartDesk.smartDeskId) from CreditCardLoadingTransaction cclt where cclt.smartDesk.smartDeskId = sd.smartDeskId )  as noOfCards) "
			+ "from SmartDesk sd, CreditCardLoadingTransaction cclts where sd.id =cclts.smartDesk.id and cclts.creditCard.id =:creditCardId"
			+ " and sd.isDeleted = false order by sd.createdOn desc ")
	public List<SmartDesk> getSmartDeskByCreditCard(@Param("creditCardId") Long creditCardId);


	
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.SmartDesk(sd.id, sd.smartDeskId, sd.bankBranch, sd.smartDeskName, sd.externalId,sd.isDeleted, (select count(cblt.smartDesk.smartDeskId) from ChequeBooksLoadingTransaction cblt where cblt.smartDesk.smartDeskId = sd.smartDeskId ) as noOfChequebook, "
			+ "  (select count(cclt.smartDesk.smartDeskId) from CreditCardLoadingTransaction cclt where cclt.smartDesk.smartDeskId = sd.smartDeskId )  as noOfCards) FROM SmartDesk sd where  sd.isDeleted = false order by sd.createdOn desc")
	public List<SmartDesk>  findAllSmartDesk();
	
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.SmartDesk(sd.id, sd.smartDeskId, sd.bankBranch, sd.smartDeskName, sd.externalId,sd.isDeleted, (select count(cblt.smartDesk.smartDeskId) from ChequeBooksLoadingTransaction cblt where cblt.smartDesk.smartDeskId = sd.smartDeskId ) as noOfChequebook, "
			+ "  (select count(cclt.smartDesk.smartDeskId) from CreditCardLoadingTransaction cclt where cclt.smartDesk.smartDeskId = sd.smartDeskId )  as noOfCards) FROM SmartDesk sd where "
			+ "sd.smartDeskId like  CONCAT('%',:searchKeyword, '%') "
			+ "or sd.smartDeskName like  CONCAT('%',:searchKeyword, '%') "
			+ "or sd.externalId like  CONCAT('%',:searchKeyword, '%') "
			+ "and  sd.isDeleted = false "
			+ "order by sd.createdOn desc")
	public List<SmartDesk>  searchSmartDesk(@Param("searchKeyword") String searchKeyword);
}
