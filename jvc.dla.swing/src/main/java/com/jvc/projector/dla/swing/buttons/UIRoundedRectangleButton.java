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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;

/**
 * @author amedrano
 *
 */
public class UIRoundedRectangleButton extends UIGradientButton {

	public UIRoundedRectangleButton(Color dark, Color light) {
		super(dark, light);
	}

	protected Shape shape, base;

	/** {@inheritDoc} */
	protected Shape getShape(JComponent j) {
		if (!j.getBounds().equals(base)) {
			Dimension s = j.getSize();
			base = j.getBounds();
			int borderSize = getBorderSize();
			shape = new RoundRectangle2D.Float(1 + borderSize , 1 + borderSize , s.width - 1 - 2*borderSize, s.height - 1- 2*borderSize,
					17, 17);
		}
		return shape;
	}

}
