package entity;

import java.sql.Timestamp;

/**
 * Created by m on 2015/6/7.
 */
public class Message {
	private int id;
	private int sendUserID;
	private String sendUsername;
	private String sendHeadURL;
	private int receiveUserID;
	private String receiveUsername;
	private String receiveHeadURL;
	private String messageContent;
	private Timestamp messageDate;
	private int readStatus;//0 or 1

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSendUserID() {
		return sendUserID;
	}

	public void setSendUserID(int sendUserID) {
		this.sendUserID = sendUserID;
	}

	public String getSendUsername() {
		return sendUsername;
	}

	public void setSendUsername(String sendUsername) {
		this.sendUsername = sendUsername;
	}

	public String getSendHeadURL() {
		return sendHeadURL;
	}

	public void setSendHeadURL(String sendHeadURL) {
		this.sendHeadURL = sendHeadURL;
	}

	public int getReceiveUserID() {
		return receiveUserID;
	}

	public void setReceiveUserID(int receiveUserID) {
		this.receiveUserID = receiveUserID;
	}

	public String getReceiveUsername() {
		return receiveUsername;
	}

	public void setReceiveUsername(String receiveUsername) {
		this.receiveUsername = receiveUsername;
	}

	public String getReceiveHeadURL() {
		return receiveHeadURL;
	}

	public void setReceiveHeadURL(String receiveHeadURL) {
		this.receiveHeadURL = receiveHeadURL;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Timestamp getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}

	public int getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}
}
