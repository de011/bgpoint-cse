package com.dromedian.comunico.bgpoint.cse.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dromedian.comunico.bgpoint.cse.entity.CreditCard;
/**
 *
 * @author Waseem
 */
@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard, Long>{	

	public Optional<CreditCard> findByCodicePan(Long codicePan);

	public boolean existsById(Long id);

	@Query("select creditCard.codicePan from CreditCard creditCard where creditCard.codicePan in :codicePan")
	public List<Long> getDuplicateCreditCards(@Param("codicePan") List<Long> codicePan);

	public Optional<CreditCard> findById(Long id);

	//0 as noOfSmartDesk, 0 as noOfIngestionTransaction, 0 as noOfLodingTransaction, 0 as noOfDeliveryTransaction
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.CreditCard(cc.id,cc.codicePan, cc.prod, cc.stato, cc.isDeleted,"
			+ "(select count(cclt.smartDesk.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfSmartDesk, "
			+ "(select count(ccit.creditCard.id) from CreditCardIngestionTransaction ccit where ccit.creditCard.id = cc.id) as noOfIngestionTransaction, "
			+ "(select count(cclt.creditCard.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfLodingTransaction, "
			+ "(select count(ccdt.creditCard.id) from CreditCardDeliveryTransaction ccdt where ccdt.creditCard.id = cc.id) as noOfDeliveryTransaction)"
			+ " from CreditCard cc where "
			+ "cc.id like  CONCAT('%',:searchKeyword, '%') "
			+" or cc.codicePan like  CONCAT('%',:searchKeyword, '%') "
			+ "or cc.prod like  CONCAT('%',:searchKeyword, '%') "
			+ "or cc.stato like  CONCAT('%',:searchKeyword, '%') "
			+ "and cc.isDeleted=false "
			+ "order by cc.createdOn desc")
	public List<CreditCard>  searchCreditCard(@Param("searchKeyword") String searchKeyword);
	
	
	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.CreditCard(cc.id,cc.codicePan, cc.prod, cc.stato, cc.isDeleted,"
			+ "(select count(cclt.smartDesk.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfSmartDesk, "
			+ "(select count(ccit.creditCard.id) from CreditCardIngestionTransaction ccit where ccit.creditCard.id = cc.id) as noOfIngestionTransaction, "
			+ "(select count(cclt.creditCard.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfLodingTransaction, "
			+ "(select count(ccdt.creditCard.id) from CreditCardDeliveryTransaction ccdt where ccdt.creditCard.id = cc.id) as noOfDeliveryTransaction)"
			+ " from CreditCard cc where  cc.id =:creditcardId "
			+ " and cc.isDeleted=false "
			+ " order by cc.createdOn desc")
	public  Optional<CreditCard> findCreditCardById(@Param("creditcardId")  Long creditcardId);

	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.CreditCard(cc.id,cc.codicePan, cc.prod, cc.stato, cc.isDeleted,"
			+ "(select count(cclt.smartDesk.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfSmartDesk, "
			+ "(select count(ccit.creditCard.id) from CreditCardIngestionTransaction ccit where ccit.creditCard.id = cc.id) as noOfIngestionTransaction, "
			+ "(select count(cclt.creditCard.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfLodingTransaction, "
			+ "(select count(ccdt.creditCard.id) from CreditCardDeliveryTransaction ccdt where ccdt.creditCard.id = cc.id) as noOfDeliveryTransaction)"
			+ " from CreditCard cc where  cc.isDeleted=false "
			+ " order by cc.createdOn desc")
	public List<CreditCard> findAllCreditCard();

	@Query("select new com.dromedian.comunico.bgpoint.cse.entity.CreditCard(cc.id,cc.codicePan, cc.prod, cc.stato, cc.isDeleted,"
			+ "(select count(cclt.smartDesk.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfSmartDesk, "
			+ "(select count(ccit.creditCard.id) from CreditCardIngestionTransaction ccit where ccit.creditCard.id = cc.id) as noOfIngestionTransaction, "
			+ "(select count(cclt.creditCard.id) from CreditCardLoadingTransaction cclt where cclt.creditCard.id = cc.id) as noOfLodingTransaction, "
			+ "(select count(ccdt.creditCard.id) from CreditCardDeliveryTransaction ccdt where ccdt.creditCard.id = cc.id) as noOfDeliveryTransaction)"
			+ " from CreditCard cc, CreditCardLoadingTransaction ccLT WHERE cc.id=ccLT.creditCard.id and ccLT.smartDesk.id =:smartDeskId "
			+ " and cc.isDeleted=false "
			+ " order by cc.createdOn desc")
	public List<CreditCard> getCreditCardBySmartDeskId(@Param("smartDeskId") Long smartdeskId);
}
