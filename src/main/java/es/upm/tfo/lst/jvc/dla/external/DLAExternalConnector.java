package es.upm.tfo.lst.jvc.dla.external;

import es.upm.tfo.lst.jvc.dla.messages.Message;

public interface DLAExternalConnector {

	public abstract Message[] sendMessageWithResponse(Message cmd);

	public abstract boolean sendMessage(Message cmd);

}