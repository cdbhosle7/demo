package com.synoris.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synoris.entities.Document;
import com.synoris.entities.SQSConsumer;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
@RestController
public class SQSConsumerController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SQSConsumer.class);

	 

    @Autowired
    private MongoTemplate mongoTemplate;

 

    public static void main(String[] args) {
        SpringApplication.run(SQSConsumer.class, args);
    }

 

    @GetMapping("/receive")
    public String receiveMessages() {
        // Retrieve and process messages from SQS
        // ...

 

        return "Received and processed messages.";
    }

 

    @PostMapping("/documents")
    public Document createDocument(@RequestBody Document document) {
        Document savedDocument = mongoTemplate.save(document);
        logger.info("Created document: {}", savedDocument);
        return savedDocument;
    }

 

    @GetMapping("/documents")
    public List<Document> getAllDocuments() {
        return mongoTemplate.findAll(Document.class);
    }

 

    @GetMapping("/documents/{id}")
    public Document getDocumentById(@PathVariable("id") String id) {
        return mongoTemplate.findById(id, Document.class);
    }

 

    @PutMapping("/documents/{id}")
    public Document updateDocument(@PathVariable("id") String id, @RequestBody Document document) {
        Document existingDocument = mongoTemplate.findById(id, Document.class);
        if (existingDocument != null) {
            existingDocument.setName(document.getName());
            existingDocument.setContent(document.getContent());
            mongoTemplate.save(existingDocument);
            logger.info("Updated document: {}", existingDocument);
        }
        return existingDocument;
    }

 

    @DeleteMapping("/documents/{id}")
    public String deleteDocument(@PathVariable("id") String id) {
        Document document = mongoTemplate.findById(id, Document.class);
        if (document != null) {
            mongoTemplate.remove(document);
            logger.info("Deleted document with id: {}", id);
            return "Document deleted successfully.";
        }
        return "Document not found.";
    }
}
	
}
