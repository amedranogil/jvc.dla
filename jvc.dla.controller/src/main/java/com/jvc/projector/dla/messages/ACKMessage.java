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
public class ACKMessage extends Message {

	/**
	 * 
	 */
	public ACKMessage() {
	}

	/**
	 * @param data
	 * @throws IllegalArgumentException
	 */
	public ACKMessage(byte[] data) throws IllegalArgumentException {
		super(data);
		if (data[0] != Binary.HEAD_ACK){
			throw new IllegalArgumentException();
		}
	}

}
