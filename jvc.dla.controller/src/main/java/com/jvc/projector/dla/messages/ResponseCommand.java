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
		int j = 1;
		while (data[j++] == upper(Binary.UNIT_DLA_RS46));
		/* IS there a bug in the protocol? according to source it is supposed to be:
		 * tx: 3f 89 01 49 50 0a 
		 * rx: 06 89 01 49 50 0a 
		 * rx: 40 89 01 49 50 36 0a 
		 * 
		 * but trace is:
		 * tx: 3f 89 01 49 50 0a 
		 * rx: 06 c2 89 01 49 50 0a 
		 * rx: 40 c2 89 01 49 50 36 0a 
		 */

		return data[4 + i + j];
	}
}
