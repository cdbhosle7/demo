package com.synoris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synoris.entities.Document;
import com.synoris.entities.SQSConsumer;

public interface DocumentRepo extends JpaRepository<Document, Integer>{

	
}
