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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import com.jvc.projector.dla.Binary;

public class DLABasicControl extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnHdmi1;
	private JRadioButton rdbtnHdmi2;
	private JRadioButton rdbtnComposite;
	private JRadioButton rdbtnPc;
	private JToggleButton tglbtnOnoff;
	private ButtonGroup inputGroup;
	
	private ScheduledExecutorService scheduler =
		       Executors.newScheduledThreadPool(1);
	
	private ScheduledFuture<?> refreshHandle;

	public DLABasicControl() {
		setLayout(new BoxLayout(this, 3));

		this.tglbtnOnoff = new JToggleButton("OnOff");
		this.tglbtnOnoff.setAlignmentX(0.5F);
		this.tglbtnOnoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!((JToggleButton) arg0.getSource()).isSelected()) {
					DLAInterface.getController().poweOff();
				} else if (DLAInterface.getController().isCoolingDown()) {
					JOptionPane
							.showMessageDialog(
									DLABasicControl.this,
									"Projector ir cooling down, wait a minute before retrying.",
									"Inane warning", 2);
					DLABasicControl.this.tglbtnOnoff.setSelected(false);
				} else {
					DLAInterface.getController().poweOn();
				}
			}
		});
		add(this.tglbtnOnoff);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Input", 4, 2, null, null));
		add(panel);

		inputGroup = new ButtonGroup();

		this.rdbtnHdmi1 = new JRadioButton("HDMI1");
		panel.add(this.rdbtnHdmi1);
		this.rdbtnHdmi1.addActionListener(this);
		inputGroup.add(this.rdbtnHdmi1);

		this.rdbtnHdmi2 = new JRadioButton("HDMI2");
		panel.add(this.rdbtnHdmi2);
		this.rdbtnHdmi2.addActionListener(this);
		inputGroup.add(this.rdbtnHdmi2);

		this.rdbtnComposite = new JRadioButton("Composite");
		panel.add(this.rdbtnComposite);
		this.rdbtnComposite.addActionListener(this);
		inputGroup.add(this.rdbtnComposite);

		this.rdbtnPc = new JRadioButton("PC");
		panel.add(this.rdbtnPc);
		this.rdbtnPc.addActionListener(this);
		inputGroup.add(this.rdbtnPc);
		
		

		addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent arg0) {
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
		});
	}

	public void actionPerformed(final ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Object c = e.getSource();
				if ((c == DLABasicControl.this.rdbtnHdmi1)
						&& (DLABasicControl.this.rdbtnHdmi1.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_HDMI1);
				}
				if ((c == DLABasicControl.this.rdbtnHdmi2)
						&& (DLABasicControl.this.rdbtnHdmi2.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_HDMI2);
				}
				if ((c == DLABasicControl.this.rdbtnComposite)
						&& (DLABasicControl.this.rdbtnComposite.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_COMP);
				}
				if ((c == DLABasicControl.this.rdbtnPc)
						&& (DLABasicControl.this.rdbtnPc.isSelected())) {
					DLAInterface.getController().setCurrentInput(
							Binary.ARG_INPUT_PC);
				}
			}
		});
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
			}
		});
			
		}
		
	}
}
