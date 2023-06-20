package com.synoris.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synoris.entities.Document;
import com.synoris.entities.SQSConsumer;
import com.synoris.repositories.DocumentRepo;

@Service
public class SQSDocumentImpl implements SQSDocument{

	@Autowired
	private DocumentRepo documentRepo;
	
	@Override
	public SQSDocument savedDocument(SQSConsumer sqsConsumer) {
		
		
		return null;
	}

	@Override
	public SQSDocument updateDocument(SQSConsumer sqsConsumer, Integer documentId) {
		
		return null;
	}

	@Override
	public SQSDocument getDocumentById(Integer documentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SQSDocument> getAllDocuments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDocument(Integer documentId) {
		// TODO Auto-generated method stub
		
	}

	
}
