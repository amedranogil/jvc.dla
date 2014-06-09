/*******************************************************************************
 * Copyright 2014 Universidad Politécnica de Madrid UPM
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.jvc.projector.dla.external;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

import com.jvc.projector.dla.Binary;
import com.jvc.projector.dla.messages.ACKMessage;
import com.jvc.projector.dla.messages.Message;
import com.jvc.projector.dla.messages.OperationCommand;
import com.jvc.projector.dla.messages.ReferenceCommand;
import com.jvc.projector.dla.messages.ResponseCommand;


/**
 * @author amedrano
 *
 */
public class DLAExtLAN implements DLAExternalConnector, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String addr;
	private transient Socket socket;
	private transient OutputStream out;
	private transient InputStream in;
	
	private boolean debug = true;
	
	/**
	 * 
	 */
	public DLAExtLAN(String address) {
		addr = address;
	}
	
	public String getAddress(){
		return addr;
	}
	
	private void connect(){
		try {
			socket = new Socket(addr, Binary.PORT);
			socket.setSoTimeout(5000);
			authenticate();
			out = socket.getOutputStream();	
			in = socket.getInputStream();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void authenticate() throws IOException {
		InputStream i = socket.getInputStream();
		boolean auth = false;
		if (expect(i, Binary.PJOK)){
			OutputStream o = socket.getOutputStream();
			o.write(Binary.PJREQ.getBytes());
			o.flush();
			if (expect(i, Binary.PJACK)){
				auth = true;
			}
		}
		if (!auth){
			throw new IOException("unable to authenticate.");
		}
	}
	
	private static boolean expect(InputStream is, String s) throws IOException{
		int rc;
		int i = 0;
		StringBuffer sb = new StringBuffer();
		try {
			while((rc=is.read())!=-1)
			{
				char c = (char)rc;
				sb.append(c);
				if(i < s.length() && s.charAt(i) == c){
					i++;
				}
				else{
					throw new Exception();
				}
				if (i == s.length()){
					return true;
				}
			}
		}catch(Exception e){

			throw new IOException( "Expecting: " + s + ", received: " + sb.toString());
		}
		return false;
	}

	private void disconnect(){
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {}
		in = null;
		out = null;
		socket = null;
		
	}
	
	private void send(Message cmd) throws IOException{
		out.write(cmd.getData());
		out.flush();
		debug(true, cmd);
	}

	private void debug(boolean tx, Message m) {
		if (debug){
			StringBuffer sb = new StringBuffer();
			if (tx) {
				sb.append("tx: ");
			}else {
				sb.append("rx: ");
			}
			sb.append(m.toString());
			System.out.println(sb.toString());
		}
	}

	private Message receiveNextMessage(){
		byte[] bytes = getNextMessage();
		
		debug(false, new Message(bytes));
		
		try {
			return new ACKMessage(bytes);
		} catch (Exception e) {	}
		try {
			return new ResponseCommand(bytes);
		} catch (Exception e) {	}
		try {
			return new OperationCommand(bytes);
		} catch (Exception e) {	}
		try {
			return new ReferenceCommand(bytes);
		} catch (Exception e) {	}
		return null;
	}
	
	private byte[] getNextMessage() {
		int rc;
		StringBuffer sb = new StringBuffer();
		try {
			while((rc=in.read())!=-1)
			{
				char c = (char)rc;
				sb.append(c);
				if (c == Binary.TRAIL){
					return sb.toString().getBytes();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	/**{@inheritDoc} */
	public synchronized Message[] sendMessageWithResponse(Message cmd){
		Message[] msgs;
		if (cmd instanceof ReferenceCommand){
			msgs = new Message[2];
		} else {
			msgs = new Message[1];
		}
		connect();
		try {
			if (socket != null && socket.isConnected()) {
				send(cmd);
				Message ack = receiveNextMessage();
				if (ack instanceof ACKMessage
						&& ack.getCommand() == cmd.getCommand()) {
					msgs[0] = ack;
				}
				if (msgs.length == 2){
					Message resp = receiveNextMessage();
					msgs[1] = resp;
				}
				disconnect();
				return msgs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		disconnect();
		return null;
	}
	
	/**{@inheritDoc} */
	public synchronized boolean sendMessage(Message cmd){
		Message[] msgs = sendMessageWithResponse(cmd);
		return msgs != null && msgs.length > 0;
	}
}
