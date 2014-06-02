/*******************************************************************************
 * Copyright 2014 Universidad Polit�cnica de Madrid UPM
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
package com.jvc.projector.dla;

import java.nio.ByteBuffer;

import com.jvc.projector.dla.external.DLAExternalConnector;
import com.jvc.projector.dla.messages.Message;
import com.jvc.projector.dla.messages.OperationCommand;
import com.jvc.projector.dla.messages.ReferenceCommand;
import com.jvc.projector.dla.messages.ResponseCommand;


/**
 * @author amedrano
 *
 */
public class DLAController {

	private DLAExternalConnector con;

	/**
	 * 
	 */
	public DLAController(DLAExternalConnector connector) {
		this.con = connector;
	}

	public DLAExternalConnector getConnector(){
		return con;
	}
	
	public boolean checkConnection(){
		OperationCommand m = new OperationCommand(Binary.CMD_CONNECTION_CHECK);
		return con.sendMessage(m);
	}
	
	private byte getStatus(){
		ReferenceCommand m = new ReferenceCommand(Binary.CMD_POWER);
		Message[] r = con.sendMessageWithResponse(m);
		if (r != null && r.length > 1
				&& r[1] instanceof ResponseCommand){
			return ((ResponseCommand)r[1]).getArg(0);
		}
		throw new RuntimeException("did not receive correct response");
	}
	
	public boolean isOn(){
		return getStatus() == Binary.ARG_POWER_ON;
	}
	
	public boolean isOff(){
		return getStatus() == Binary.ARG_POWER_OFF;
	}
	
	public boolean isCoolingDown(){
		return getStatus() == Binary.ARG_POWER_COOL_DOWN;
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
	
	/**
	 * Only sends power_on command If and only if isOff() == true.
	 * Sending power_on command while in Cooling_Down status will result in error.
	 */
	public void poweOn(){
		if (isOff()){
			OperationCommand m = new OperationCommand(Binary.CMD_POWER, new byte[]{Binary.ARG_POWER_ON});
			con.sendMessage(m);
		}
	}
	
	/**
	 * Only sends power_off command if and only if status is on.
	 */
	public void poweOff(){
		if (isOn()) {
			OperationCommand m = new OperationCommand(Binary.CMD_POWER,
					new byte[] { Binary.ARG_POWER_OFF });
			con.sendMessage(m);
		}
	}
	
	public void setCurrentInput(byte input){
		if (getCurrentInput() != input) {
			OperationCommand m = new OperationCommand(Binary.CMD_INPUT,
					new byte[] { input });
			con.sendMessage(m);
		}
	}
	
	public void sendRemoteButton(int button){
		OperationCommand m = new OperationCommand(Binary.CMD_REMOTE, ByteBuffer.allocate(4).putInt(button).array());
		con.sendMessage(m);
	}
}
