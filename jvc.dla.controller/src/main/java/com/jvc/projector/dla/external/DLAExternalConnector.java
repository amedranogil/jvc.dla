package com.jvc.projector.dla.external;

import java.io.Serializable;

import com.jvc.projector.dla.messages.Message;

public interface DLAExternalConnector extends Serializable{

	public abstract Message[] sendMessageWithResponse(Message cmd);

	public abstract boolean sendMessage(Message cmd);

}