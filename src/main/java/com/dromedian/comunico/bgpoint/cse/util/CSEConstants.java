package com.dromedian.comunico.bgpoint.cse.util;

/**
 * @author DEEPAK
 *
 */
public interface CSEConstants {
	
	public static final String UNABLE_TO_FETCH = "unable to fetch record.";
	public static final String SYSTEM_EXCEPTION = "Something went wrong, Please check your request input.";
	public static final String RECORD_NOT_FOUND = "Record Not Found";

	public static final String CREDITCARD_FIND = " Successfully fetched all records from Creditcard";
	public static final String CREDITCARD_FAILED_TO_FIND = "Unable to fetch records from Credit Crads";


	// Credit Card/Chequebook ingestion 
	public static final String RECORD_INGESTION_SUCCESS="Record  created Successfully!";
	public static final String RECORD_INGESTION_FAILED="Failed to Save the Record";
	
	
	// Credit Card/ Chequebook Loading
	public static final String RECORD_LOADING_STATUS_SUCCESS="Record updated Successfully!";
	public static final String RECORD_LOADING_STATUS_FAILED="Failed to update the Record";
	

	// Chequebook Delivery
	public static final String RECORD_DELIVERY_STATUS_SUCCESS = "cheque Book/credit card  Delivered Successfully!";
	public static final String RECORD_DELIVERY_STATUS_FAILED = "Failed to Delivered the Record!";

	/*
	// SmartDesk/ BankBranch Insertion
		public static final String RECORD_INSERTION_STATUS_SUCCESS="Record save Successfully!";
		public static final String RECORD_INSERTION_STATUS_FAILED="Failed to save the Record";
		*/
	// SmartDesk/ BankBranch Delete
		public static final String RECORD_DELETE_STATUS_SUCCESS="Record Deleted Successfully!";
		public static final String RECORD_DELETE_STATUS_FAILED="Failed to Delete the Record";
		
	
		// SmartDesk/ BankBranch Find
		public static final String RECORD_FIND_STATUS_SUCCESS="Record Found Successfully!";
		public static final String RECORD_FIND_STATUS_FAILED="No Record found.";

		
		
		public static final String RECORD_DUPLICATE="Duplicate Record found";
		public static final Integer CHEQUEBOOK_LOADING_ACTION=37;
		public static final Integer CHEQUEBOOK_DELIVERY_ACTION=35;
		
		
		public static final Integer CREDITCARD_LOADING_ACTION=37;
		public static final Integer CREDITCARD_DELIVERY_ACTION=41;
		
		
		
		public static final String BANK_BRANCH="bankbranch";
		public static final String CHEQUE_BOOK="chequebook";
		public static final String CREDIT_CARD="creditcard";
		
		
		public static final String CHEQUE_BOOK_AND_CARD_INGETION="ingestion";
		public static final String CHEQUE_BOOK_AND_CARD_LOADING="loading";
		public static final String CHEQUE_BOOK_AND_CARD_DELIVERY="delivery";
		
		
		
		
		
}
