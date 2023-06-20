package com.synoris.services;

import java.util.List;

import com.synoris.entities.SQSConsumer;

public interface SQSDocument {

	SQSDocument savedDocument(SQSConsumer sqsConsumer);
	SQSDocument updateDocument(SQSConsumer sqsConsumer, Integer documentId);
	SQSDocument getDocumentById(Integer documentId);
	List<SQSDocument> getAllDocuments();
	void deleteDocument(Integer documentId);
	
	
  
}
