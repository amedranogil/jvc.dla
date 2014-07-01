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
package com.jvc.projector.dla.swing.buttons;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

/**
 * @author amedrano
 * 
 */
public class DLARoundButton extends DLAButton {

	/**
	 * @param icon
	 * @param btn
	 */
	public DLARoundButton(Icon icon, int btn) {
		super(icon, btn);
	}

	/**
	 * @param name
	 * @param btn
	 */
	public DLARoundButton(String name, int btn) {
		super(name, btn);
	}

	/**
	 * @param text
	 * @param icon
	 * @param btn
	 */
	public DLARoundButton(String text, Icon icon, int btn) {
		super(text, icon, btn);
	}

	
	/** Override, to get a elipse shape.*/
	@Override
	protected Shape getShape() {
		if (!getBounds().equals(base)) {
			Dimension s = getSize();
			base = getBounds();
//			shape = new RoundRectangle2D.Float(0, 0, s.width - 1, s.height - 1,
//					17, 17);
			shape = new Ellipse2D.Float(0, 0, s.width-1, s.height-1);
		}
		return shape;
	}
}
