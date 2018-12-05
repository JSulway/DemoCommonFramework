package com.demo.message;

import static com.demo.common.CommonConstant.JSON_CONTENT_TYPE;
import static com.demo.common.CommonConstant.MULE_CORRELATION_ID;
import static com.demo.common.CommonConstant.REPLY_TO_QUEUE;
import static com.demo.common.CommonConstant.SOURCE_IP;
import static com.demo.common.CommonConstant.USERNAME;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.stereotype.Service;

import com.demo.error.CommonErrorCode;
import com.demo.error.CommonRuntimeException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Sender class for sending messages to RabbitMQs
 * 
 * @author sulwayj
 *
 */
@Service
public class MessageSender {

	private final Logger logger = Logger.getLogger(MessageSender.class);

	private String queueName;
	private String rabbitMQhost;
	private String rabbitMQexchange;
	private String rabbitMQvirtualhost;
	private String username;
	private String password;

	/**
	 * 
	 * Publish the provided message to configured queue
	 * 
	 * @param msg
	 * @param correlationID
	 * @param replyToQueue
	 * @throws Exception
	 */
	public void publish(String message, String replyToQueue, boolean queueDurability, Map<String, Object> queueArgs) {
		ConnectionFactory factory = null;
		Connection connection = null;
		Channel channel = null;
		try {
			// Create connection factory
			factory = createConnectionFactory(rabbitMQhost, rabbitMQvirtualhost, username, password);
			// Create a connection
			connection = factory.newConnection();
			channel = connection.createChannel();

			// On declare, it will only create it if it doesn't already exist
			channel.queueDeclare(queueName, queueDurability, false, false, queueArgs);
			channel.exchangeDeclare(rabbitMQexchange, ExchangeTypes.TOPIC, true);
			channel.queueBind(queueName, rabbitMQexchange, queueName);

			// Set the content type otherwise the message wont parse once it
			// arrives on the Message Receiver
			AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties().builder();
			builder.contentType(JSON_CONTENT_TYPE);

			if (replyToQueue != null) {
				Map<String, Object> headerMap = new HashMap<String, Object>();
				if (replyToQueue != null) {
					headerMap.put(REPLY_TO_QUEUE, replyToQueue);
					builder.headers(headerMap);
				}
		}

			logger.debug("\n [#] Sending Message : " + message + "\n");
			channel.basicPublish(rabbitMQexchange, queueName, builder.build(), message.getBytes("UTF-8"));
		} catch (IOException e) {
			logger.error("IO Exception during message send:" + e);
			throw new CommonRuntimeException(CommonErrorCode.IO_EXCEPTION, e, "IO Exception during message send");
		} catch (TimeoutException e) {
			logger.error("Timeout Exception during message send:" + e);
			throw new CommonRuntimeException(CommonErrorCode.TIMEOUT_EXCEPTION, e,
					"Timeout Exception during message send");
		} finally {
			try {
				if (channel != null) {
					channel.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (IOException | TimeoutException e) {
				// ignore the error as
			}

		}
	}

	/**
	 * 
	 * @param hostName
	 * @param virtualhost
	 * @param username
	 * @param password
	 * @return
	 */
	private ConnectionFactory createConnectionFactory(String hostName, String virtualhost, String username,
			String password) {
		ConnectionFactory factory = null;
		factory = new ConnectionFactory();
		factory.setHost(hostName);
		factory.setVirtualHost(virtualhost);
		factory.setUsername(username);
		factory.setPassword(password);

		return factory;
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName
	 *            the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	/**
	 * @return the rabbitMQhost
	 */
	public String getRabbitMQhost() {
		return rabbitMQhost;
	}

	/**
	 * @param rabbitMQhost
	 *            the rabbitMQhost to set
	 */
	public void setRabbitMQhost(String rabbitMQhost) {
		this.rabbitMQhost = rabbitMQhost;
	}

	/**
	 * @return the rabbitMQexchange
	 */
	public String getRabbitMQexchange() {
		return rabbitMQexchange;
	}

	/**
	 * @param rabbitMQexchange
	 *            the rabbitMQexchange to set
	 */
	public void setRabbitMQexchange(String rabbitMQexchange) {
		this.rabbitMQexchange = rabbitMQexchange;
	}

	/**
	 * @return the rabbitMQvirtualhost
	 */
	public String getRabbitMQvirtualhost() {
		return rabbitMQvirtualhost;
	}

	/**
	 * @param rabbitMQvirtualhost
	 *            the rabbitMQvirtualhost to set
	 */
	public void setRabbitMQvirtualhost(String rabbitMQvirtualhost) {
		this.rabbitMQvirtualhost = rabbitMQvirtualhost;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
