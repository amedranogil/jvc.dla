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
package com.jvc.projector.dla.swing;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.jvc.projector.dla.Binary;

public class DLAVirtualRemote extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DLAVirtualRemote() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(0, 3, 5, 5));

		grid.add(new DLAButton("StBy", Binary.BTN_STAND_BY));
		grid.add(new JPanel());
		grid.add(new DLAButton("On", Binary.BTN_ON));

		grid.add(new DLAButton("HDMI1", Binary.BTN_HDMI1));
		grid.add(new DLAButton("HDMI2", Binary.BTN_HDMI2));
		grid.add(new DLAButton("COMP", Binary.BTN_COMP));

		grid.add(new DLAButton("3D Form", Binary.BTN_3D_FORMAT));
		grid.add(new DLAButton("3D Sett", Binary.BTN_3D_SETTING));
		grid.add(new DLAButton("PC", Binary.BTN_PC));

		grid.add(new DLAButton("Lens Ctrl", Binary.BTN_LENS_CONTROL));
		grid.add(new DLAButton("Lens Mr", Binary.BTN_LENS_MEMORY));
		grid.add(new DLAButton("Anamorphic", Binary.BTN_ANAMO));

		grid.add(new DLAButton("Hide", Binary.BTN_HIDE));
		grid.add(new DLAButton("^", Binary.BTN_UP));
		grid.add(new JButton("Light"));

		grid.add(new DLAButton("<", Binary.BTN_LEFT));
		grid.add(new DLAButton("ok", Binary.BTN_OK));
		grid.add(new DLAButton(">", Binary.BTN_RIGHT));

		grid.add(new DLAButton("Menu", Binary.BTN_MENU));
		grid.add(new DLAButton("v", Binary.BTN_DOWN));
		grid.add(new DLAButton("Back", Binary.BTN_BACK));

		grid.add(new DLAButton("Film", Binary.BTN_FILM));
		grid.add(new DLAButton("Cinema", Binary.BTN_CINEMA));
		grid.add(new DLAButton("Anime", Binary.BTN_ANIME));

		grid.add(new DLAButton("Natural", Binary.BTN_NATURAL));
		grid.add(new DLAButton("Stage", Binary.BTN_STAGE));
		grid.add(new DLAButton("3D", Binary.BTN_3D));

		grid.add(new DLAButton("THX", Binary.BTN_THX));
		grid.add(new DLAButton("User", Binary.BTN_USER));
		grid.add(new DLAButton("C.M.D", Binary.BTN_CMD));

		JPanel colors = new JPanel();
		colors.add(new DLAButton("Gamma", Binary.BTN_GAMMA));
		colors.add(new DLAButton("Color Temp", Binary.BTN_COLOR_TEMP));
		colors.add(new DLAButton("Color P.file", Binary.BTN_COLOR_PROF));
		colors.add(new DLAButton("Pic. Adj", Binary.BTN_PIC_ADJ));
		setLayout(new BoxLayout(this, 3));

		add(grid);
		add(colors);
	}
}