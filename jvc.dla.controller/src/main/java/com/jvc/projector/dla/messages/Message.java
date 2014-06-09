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

/**
 * @author amedrano
 *
 */
public class Message {

	protected byte[] data;
	
	public Message(){
		super();
	};
	
	public Message(byte[] data) throws IllegalArgumentException{
		this.data = data;
	}
	
	public byte[] getData(){
		return data;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				sb.append(String.format("%02x ", data[i] & 0xff));
			}
		}
		return sb.toString();
	}
	
	static protected byte upper(short x){
		return (byte)((x>>8) & 0xff);
	}
	
	static protected byte lower(short x){
		return (byte)(x & 0xff);
	}
	
	public short getCommand(){
		return (short) ((data[3]<<8) + data[4]);
	}
	
	public short getUnit(){
		return (short) ((data[1]<<8) + data[2]);
	}
}
