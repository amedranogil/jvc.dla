package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import es.upm.tfo.lst.jvc.dla.DLAController;
import es.upm.tfo.lst.jvc.dla.external.DLAExtLAN;

public class OnOff extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String ADDR = "192.168.1.232";
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnOff frame = new OnOff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OnOff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToggleButton tglbtnProjector = new JToggleButton("Projector");
		DLAController cont = new DLAController(new DLAExtLAN(ADDR));
		tglbtnProjector.setSelected(cont.isOn());
		tglbtnProjector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DLAController cont = new DLAController(new DLAExtLAN(ADDR));
				if (!((JToggleButton)arg0.getSource()).isSelected()){
					cont.poweOff();
				}else {
					cont.poweOn();
				}
			}
		});
		contentPane.add(tglbtnProjector, BorderLayout.CENTER);
	}

}
