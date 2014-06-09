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

import com.jvc.projector.dla.DLAController;
import com.jvc.projector.dla.external.DLAExternalConnector;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class DLAInterface extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FILE = "./Status.dat";
	private JPanel contentPane;
	private static DLAController controller;

	public static DLAController getController() {
		return controller;
	}

	static void setController(DLAController cont) {
		controller = cont;
		storeToFile(FILE);
	}
    private static void storeToFile(String f){
    	try
    	{
    		FileOutputStream saveFile = new FileOutputStream(f);
    		ObjectOutputStream save = new ObjectOutputStream(saveFile);
    		save.writeObject(controller.getConnector());
    		save.close();
    	}
    	catch (FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }


	private static void loadFromFile(String f) {
		try {
			FileInputStream saveFile = new FileInputStream(f);

			ObjectInputStream save = new ObjectInputStream(saveFile);

			DLAExternalConnector con = (DLAExternalConnector) save.readObject();
			controller = new DLAController(con);
			save.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String statusFile = "./Status.dat";
		if (args.length > 0) {
			statusFile = args[0];
		}
		if (new File(statusFile).exists()) {
			loadFromFile(statusFile);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DLAInterface frame = new DLAInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DLAInterface() {
		setDefaultCloseOperation(3);
		setBounds(100, 100, 257, 626);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmConnection = new JMenuItem("Connection");
		mntmConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new DLALANConfigurator(DLAInterface.this)
								.setVisible(true);
					}
				});
			}
		});
		menuBar.add(mntmConnection);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(1);
		this.contentPane.add(tabbedPane, "Center");

		JPanel panel = new DLABasicControl();
		tabbedPane.addTab("Basic", null, panel, null);

		JPanel panel_1 = new DLAVirtualRemote();
		tabbedPane.addTab("Remote", null, panel_1, null);
	}
}