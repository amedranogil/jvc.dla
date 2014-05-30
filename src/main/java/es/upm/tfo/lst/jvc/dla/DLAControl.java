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
package es.upm.tfo.lst.jvc.dla;

import java.nio.ByteBuffer;

import es.upm.tfo.lst.jvc.dla.messages.Message;
import es.upm.tfo.lst.jvc.dla.messages.OperationCommand;
import es.upm.tfo.lst.jvc.dla.messages.ReferenceCommand;
import es.upm.tfo.lst.jvc.dla.messages.ResponseCommand;

/**
 * @author amedrano
 *
 */
public class DLAControl {

	private DLAConnect con;

	/**
	 * 
	 */
	public DLAControl(String address) {
		this.con = new DLAConnect(address);
	}

	public boolean checkConnection(){
		OperationCommand m = new OperationCommand(Binary.CMD_CONNECTION_CHECK);
		return con.sendMessage(m);
	}
	
	public boolean isOn(){
		ReferenceCommand m = new ReferenceCommand(Binary.CMD_POWER);
		Message[] r = con.sendMessageWithResponse(m);
		if (r != null && r.length > 1
				&& r[1] instanceof ResponseCommand){
			return ((ResponseCommand)r[1]).getArg(0) == Binary.ARG_POWER_ON;
		}
		throw new RuntimeException("did not receive correct response");
	}
	
	public boolean isOff(){
		ReferenceCommand m = new ReferenceCommand(Binary.CMD_POWER);
		Message[] r = con.sendMessageWithResponse(m);
		if (r != null && r.length > 1
				&& r[1] instanceof ResponseCommand){
			return ((ResponseCommand)r[1]).getArg(0) == Binary.ARG_POWER_OFF;
		}
		throw new RuntimeException("did not receive correct response");
	}
	
	public boolean isCoolingDown(){
		ReferenceCommand m = new ReferenceCommand(Binary.CMD_POWER);
		Message[] r = con.sendMessageWithResponse(m);
		if (r != null && r.length > 1
				&& r[1] instanceof ResponseCommand){
			return ((ResponseCommand)r[1]).getArg(0) == Binary.ARG_POWER_COOL_DOWN;
		}
		throw new RuntimeException("did not receive correct response");
	}
	
	public byte getCurrentInput(){
		ReferenceCommand m = new ReferenceCommand(Binary.CMD_INPUT);
		Message[] r = con.sendMessageWithResponse(m);
		if (r != null && r.length > 1
				&& r[1] instanceof ResponseCommand){
			return ((ResponseCommand)r[1]).getArg(0);
		}
		throw new RuntimeException("did not receive correct response");		
	}
	
	public void poweOn(){
		OperationCommand m = new OperationCommand(Binary.CMD_POWER, new byte[]{Binary.ARG_POWER_ON});
		con.sendMessage(m);
	}
	
	public void poweOff(){
		OperationCommand m = new OperationCommand(Binary.CMD_POWER, new byte[]{Binary.ARG_POWER_OFF});
		con.sendMessage(m);
	}
	
	public void setCurrentInput(byte input){
		OperationCommand m = new OperationCommand(Binary.CMD_INPUT, new byte[]{input});
		con.sendMessage(m);
	}
	
	public void sendRemoteButton(int button){
		OperationCommand m = new OperationCommand(Binary.CMD_REMOTE, ByteBuffer.allocate(4).putInt(button).array());
		con.sendMessage(m);
	}
}
