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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import com.jvc.projector.dla.Binary;
import com.jvc.projector.dla.swing.buttons.DLAButton;
import com.jvc.projector.dla.swing.buttons.UIEllipticalButton;
import com.jvc.projector.dla.swing.buttons.UIRoundedRectangleButton;
import com.jvc.projector.dla.swing.buttons.UIToggleButton;

public class DLAVirtualRemote extends JPanel implements ActionListener, ComponentListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color DARK = new Color(173,173,173);
    private static final Color BG = new Color(204,204,204);
    

	private ScheduledExecutorService scheduler =
		       Executors.newScheduledThreadPool(1);
	
	private ScheduledFuture<?> refreshHandle;
    
    
	private AbstractButton tglbtnOnoff;
	private AbstractButton rdbtnHdmi1;
	private AbstractButton rdbtnHdmi2;
	private AbstractButton rdbtnComposite;
	private AbstractButton rdbtnPc;
	private ButtonGroup inputGroup;

	public DLAVirtualRemote() {
		setBackground(Color.DARK_GRAY);
		JPanel grid = new JPanel();
		grid.setOpaque(false);
		grid.setLayout(new GridLayout(0, 3, 5, 5));
		addComponentListener(this);
		//TODO Seems component listener is not notified...
		componentShown(null);

		AbstractButton b = new DLAButton("StBy", Binary.BTN_STAND_BY);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));
		grid.add(b);
		JPanel blank = new JPanel();
		blank.setOpaque(false);
		grid.add(blank);
		tglbtnOnoff = new JToggleButton("On");
		tglbtnOnoff.addActionListener(this);
		tglbtnOnoff.setUI(new UIToggleButton(DARK, BG, Color.RED));		
		grid.add(tglbtnOnoff);

		rdbtnHdmi1 = new JToggleButton("HDMI1");
		rdbtnHdmi1.addActionListener(this);
		rdbtnHdmi1.setUI(new UIToggleButton(DARK, BG, Color.YELLOW));		
		grid.add(rdbtnHdmi1);
		rdbtnHdmi2 = new JToggleButton("HDMI2");
		rdbtnHdmi2.addActionListener(this);
		rdbtnHdmi2.setUI(new UIToggleButton(DARK, BG, Color.YELLOW));		
		grid.add(rdbtnHdmi2);
		rdbtnComposite = new JToggleButton("COMP");
		rdbtnComposite.addActionListener(this);
		rdbtnComposite.setUI(new UIToggleButton(DARK, BG, Color.YELLOW));		
		grid.add(rdbtnComposite);

		b = new DLAButton("3D Form", Binary.BTN_3D_FORMAT);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		b = new DLAButton("3D Sett", Binary.BTN_3D_SETTING);
		b.setUI(new UIRoundedRectangleButton(DARK, BG));		
		grid.add(b);
		rdbtnPc = new JToggleButton("PC");
		rdbtnPc.addActionListener(this);
		rdbtnPc.setUI(new UIToggleButton(DARK, BG, Color.YELLOW));		
		grid.add(rdbtnPc);

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

		inputGroup = new ButtonGroup();

		inputGroup.add(this.rdbtnHdmi1);
		inputGroup.add(this.rdbtnHdmi2);
		inputGroup.add(this.rdbtnComposite);
		inputGroup.add(this.rdbtnPc);
	}
	
	public void actionPerformed(final ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Object c = e.getSource();
				if (c == tglbtnOnoff 
						&& !((AbstractButton) c).isSelected()) {
					DLAInterface.getController().poweOff();
				} else if (DLAInterface.getController().isCoolingDown()) {
					JOptionPane
							.showMessageDialog(
									DLAVirtualRemote.this,
									"Projector ir cooling down, wait a minute before retrying.",
									"Inane warning", 2);
					DLAVirtualRemote.this.tglbtnOnoff.setSelected(false);
				} else {
					DLAInterface.getController().poweOn();
				}
				
				if ((c == rdbtnHdmi1)
						&& (rdbtnHdmi1.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_HDMI1);
				}
				if ((c == rdbtnHdmi2)
						&& (rdbtnHdmi2.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_HDMI2);
				}
				if ((c == rdbtnComposite)
						&& (rdbtnComposite.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_COMP);
				}
				if ((c == rdbtnPc)
						&& (rdbtnPc.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_PC);
				}
			}
		});
	}
	
	public void componentShown(ComponentEvent arg0) {
		System.out.println("shown");
		refreshHandle =
	            scheduler.scheduleAtFixedRate(new Refresh(), 0, 10, TimeUnit.SECONDS);
	}

	public void componentResized(ComponentEvent arg0) {
	}

	public void componentMoved(ComponentEvent arg0) {
	}

	public void componentHidden(ComponentEvent arg0) {
		if (refreshHandle != null) {
			refreshHandle.cancel(false);
			refreshHandle = null;
		}
	}
	
	private class Refresh implements Runnable{

		public void run() {
			SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				if (DLAInterface.getController() != null && DLAInterface.getController().checkConnection()) {
					if (DLAInterface.getController().isOn()) {
						tglbtnOnoff.setSelected(true);
						byte currentInput = DLAInterface.getController()
								.getCurrentInput();
						if (currentInput == Binary.ARG_INPUT_HDMI1) {
							rdbtnHdmi1.setSelected(true);
						}
						if (currentInput == Binary.ARG_INPUT_HDMI2) {
							rdbtnHdmi2.setSelected(true);
						}
						if (currentInput == Binary.ARG_INPUT_COMP) {
							rdbtnComposite
									.setSelected(true);
						}
						if (currentInput == Binary.ARG_INPUT_PC) {
							rdbtnPc.setSelected(true);
						}
					} else {
						tglbtnOnoff.setSelected(false);
						tglbtnOnoff.setEnabled(!DLAInterface.getController().isCoolingDown());
						inputGroup.clearSelection();
					}
				} 
				System.out.print("refreshed ");
			}
		});
			
		}
		
	}
}