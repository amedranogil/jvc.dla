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
package com.jvc.projector.dla.messages;

import com.jvc.projector.dla.Binary;

/**
 * @author amedrano
 *
 */
public class ReferenceCommand extends Message {

	/**
	 * 
	 */
	public ReferenceCommand() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 * @throws IllegalArgumentException
	 */
	public ReferenceCommand(byte[] data) throws IllegalArgumentException {
		super(data);
		if (data[0] != Binary.HEAD_REFERECE_CMD){
			throw new IllegalArgumentException();
		}
	}
	

	public ReferenceCommand(short command, byte[] args) {
		super();
		data = new byte[6 + args.length];
		data[0] = Binary.HEAD_REFERECE_CMD;
		data[1] = upper(Binary.UNIT_DLA_RS46);
		data[2] = lower(Binary.UNIT_DLA_RS46);
		data[3] = upper(command);
		data[4] = lower(command);
		for (int i = 0; i < args.length; i++) {
			data [5+i] = args[i];
		}
		data[data.length-1] = Binary.TRAIL;
	}
	
	public ReferenceCommand(short command){
		this(command, new byte[0]);
	}
}
