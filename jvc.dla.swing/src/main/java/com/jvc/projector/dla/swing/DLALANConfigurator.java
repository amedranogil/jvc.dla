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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.jvc.projector.dla.DLAController;
import com.jvc.projector.dla.external.DLAExtLAN;

public class DLALANConfigurator extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private class OkProcedure implements Runnable {
		public OkProcedure() {
		}

		public void run() {
			DLAInterface.setController(new DLAController(new DLAExtLAN(
					DLALANConfigurator.this.address.getText())));
		}
	}

	private class CheckProcedure implements Runnable {
		public CheckProcedure() {
		}

		public void run() {
			DLAController c = new DLAController(new DLAExtLAN(
					DLALANConfigurator.this.address.getText()));
			if (c.checkConnection()) {
				JOptionPane.showMessageDialog(
						DLALANConfigurator.this.getOwner(),
						"Connection Successful.");
			} else {
				JOptionPane.showMessageDialog(
						DLALANConfigurator.this.getOwner(),
						"Connection was not successful.", "Inane error", 0);
			}
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField address;

	public DLALANConfigurator(Frame owner) {
		super(owner);
		init();
	}

	private void init() {
		setTitle("LAN external Connection configuration");
		setBounds(100, 100, 450, 175);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, "Center");

		this.address = new JTextField();
		this.address.setColumns(10);
		if (DLAInterface.getController() != null
				&& DLAInterface.getController().getConnector() instanceof DLAExtLAN){
			this.address.setText(((DLAExtLAN)DLAInterface.getController().getConnector()).getAddress());
		}
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities
						.invokeLater(new DLALANConfigurator.CheckProcedure());
			}
		});
		JLabel lblNewLabel = new JLabel(
				"Insert ip address (eg 192.168.1.111) or dns name of the proyector.");
		GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																lblNewLabel,
																-1, 404, 32767)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				this.address,
																				-2,
																				316,
																				-2)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnCheck,
																				-1,
																				108,
																				32767)))
										.addContainerGap()));

		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addComponent(lblNewLabel, -2, 14, -2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.address,
																-2, -1, -2)
														.addComponent(btnCheck))
										.addContainerGap(51, 32767)));

		this.contentPanel.setLayout(gl_contentPanel);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(2));
		getContentPane().add(buttonPane, "South");

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities
						.invokeLater(new DLALANConfigurator.OkProcedure());
				DLALANConfigurator.this.dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLALANConfigurator.this.dispose();
			}
		});
		buttonPane.add(cancelButton);
	}
}