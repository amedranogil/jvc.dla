package com.jvc.projector.dla.external;

import com.jvc.projector.dla.messages.Message;

public interface DLAExternalConnector {

	public abstract Message[] sendMessageWithResponse(Message cmd);

	public abstract boolean sendMessage(Message cmd);

}