package com.synoris.entities;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

public class SQSConsumer {
	
	  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SQSConsumer.class);

	private static final String QUEUE_URL = "YOUR_QUEUE_URL";
    private static final String LOG_FILE_PATH = "path/to/logfile.log";

 

    public static void main(String[] args) {
        // Configure the SQS client
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1) // Set the appropriate region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

 

        // Continuously poll the SQS queue for new messages
        while (true) {
            ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                    .queueUrl(QUEUE_URL)
                    .maxNumberOfMessages(10) // Adjust the number of messages to retrieve
                    .build();

 

            ReceiveMessageResponse receiveResponse = sqsClient.receiveMessage(receiveRequest);
            for (Message message : receiveResponse.messages()) {
                String body = message.body();
                logger.info("Received message: {}", body);

 

                // Perform any processing or logging of the message here

 

                // Delete the message from the queue
                DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                        .queueUrl(QUEUE_URL)
                        .receiptHandle(message.receiptHandle())
                        .build();

 

                sqsClient.deleteMessage(deleteRequest);
            }
        }
    }
}

