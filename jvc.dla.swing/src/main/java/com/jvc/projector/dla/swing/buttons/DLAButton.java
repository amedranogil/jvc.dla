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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.jvc.projector.dla.swing.DLAInterface;

public class DLAButton extends JButton implements Runnable {
	private static final long serialVersionUID = 1L;
	private int btn;
	private static final Color DARK = new Color(173,173,173);
    private static final Color BG = new Color(204,204,204);

	public DLAButton(Icon icon, int btn) {
		super("", icon);
		setButton(btn);
	}

	private void setButton(int btn) {
		if (btn != -1) {
			this.btn = btn;
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(DLAButton.this);
				}
			});
		}
	}

	public DLAButton(String name, int btn) {
		super(name);
		setButton(btn);
	}

	public DLAButton(String text, Icon icon, int btn) {
		super(text, icon);
		setButton(btn);
	}

	public void run() {
		DLAInterface.getController().sendRemoteButton(this.btn);
	}
}