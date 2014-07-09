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
package com.jvc.projector.dla.swing;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.jvc.projector.dla.Binary;
import com.jvc.projector.dla.swing.buttons.DLAButton;
import com.jvc.projector.dla.swing.buttons.UIEllipticalButton;
import com.jvc.projector.dla.swing.buttons.UIRoundedRectangleButton;

public class DLAVirtualRemote extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color DARK = new Color(173,173,173);
    private static final Color BG = new Color(204,204,204);

	public DLAVirtualRemote() {
		setBackground(Color.DARK_GRAY);
		JPanel grid = new JPanel();
		grid.setOpaque(false);
		grid.setLayout(new GridLayout(0, 3, 5, 5));

		AbstractButton b = new DLAButton("StBy", Binary.BTN_STAND_BY);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));
		grid.add(b);
		JPanel blank = new JPanel();
		blank.setOpaque(false);
		grid.add(blank);
		b = new DLAButton("On", Binary.BTN_ON);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		b = new DLAButton("HDMI1", Binary.BTN_HDMI1);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("HDMI2", Binary.BTN_HDMI2);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("COMP", Binary.BTN_COMP);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		b = new DLAButton("3D Form", Binary.BTN_3D_FORMAT);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("3D Sett", Binary.BTN_3D_SETTING);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("PC", Binary.BTN_PC);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		b = new DLAButton("Lens Ctrl", Binary.BTN_LENS_CONTROL);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("Lens Mr", Binary.BTN_LENS_MEMORY);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("Anamorphic", Binary.BTN_ANAMO);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		b = new DLAButton("Hide", Binary.BTN_HIDE);
		b.setUI(new UIEllipticalButton(DARK, BG));
		grid.add(b);
		b = new DLAButton("^", Binary.BTN_UP);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("Light", -1);
		b.setUI(new UIEllipticalButton(DARK, BG));
		grid.add(b);

		b = new DLAButton("<", Binary.BTN_LEFT);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("ok", Binary.BTN_OK);
		b.setUI(new UIEllipticalButton(DARK, BG));
		grid.add(b);
		b = new DLAButton(">", Binary.BTN_RIGHT);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		b = new DLAButton("Menu", Binary.BTN_MENU);
		b.setUI(new UIEllipticalButton(DARK, BG));
		grid.add(b);
		b = new DLAButton("v", Binary.BTN_DOWN);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("Back", Binary.BTN_BACK);
		b.setUI(new UIEllipticalButton(DARK, BG));
		grid.add(b);

		b = new DLAButton("Film", Binary.BTN_FILM);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("Cinema", Binary.BTN_CINEMA);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("Anime", Binary.BTN_ANIME);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		b = new DLAButton("Natural", Binary.BTN_NATURAL);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("Stage", Binary.BTN_STAGE);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("3D", Binary.BTN_3D);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		b = new DLAButton("THX", Binary.BTN_THX);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("User", Binary.BTN_USER);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("C.M.D", Binary.BTN_CMD);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);

		JPanel colors = new JPanel();
		b = new DLAButton("Gamma", Binary.BTN_GAMMA);
		b.setUI(new UIEllipticalButton(DARK, BG));
		colors.add(b);
		b = new DLAButton("Color Temp", Binary.BTN_COLOR_TEMP);
		b.setUI(new UIEllipticalButton(DARK, BG));
		colors.add(b);
		b = new DLAButton("Color P.file", Binary.BTN_COLOR_PROF);
		b.setUI(new UIEllipticalButton(DARK, BG));
		colors.add(b);
		b = new DLAButton("Pic. Adj", Binary.BTN_PIC_ADJ);
		b.setUI(new UIEllipticalButton(DARK, BG));
		colors.add(b);
		colors.setOpaque(false);
		setLayout(new BoxLayout(this, 3));

		add(grid);
		add(colors);
		
	}
}