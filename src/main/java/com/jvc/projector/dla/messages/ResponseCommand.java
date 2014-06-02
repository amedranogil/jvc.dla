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
public class ResponseCommand extends Message {

	/**
	 * 
	 */
	public ResponseCommand() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 * @throws IllegalArgumentException
	 */
	public ResponseCommand(byte[] data) throws IllegalArgumentException {
		super(data);
		if (data[0] != Binary.HEAD_RESP){
			throw new IllegalArgumentException();
		}
	}

	public byte getArg(int i){
		return data[5 + i];
	}
}
