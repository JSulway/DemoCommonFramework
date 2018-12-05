package com.demo.message;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;

/**
 * Validates messages content for required body and headers
 * 
 * @author SULWAYJO
 *
 */
public class MessageValidator {

	/**
	 * Validate the Payload and headers to ensure if anything is missing an appropriate AmqpRejectAndDontRequeueException is raised
	 * which in turn causes the message to be put onto the dead letter queue
	 * 
	 * @param jsonAsByteArray
	 * @param replyToQueue
	 * @param correlationID
	 * @param sourceIP
	 * @param usernameHeader
	 * @throws AmqpRejectAndDontRequeueException
	 */
	public static void validateHeaders(byte[] jsonAsByteArray, String replyToQueue) throws AmqpRejectAndDontRequeueException{
			// No header validation required as fields are optional
	}

}
