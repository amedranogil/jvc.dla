package es.upm.tfo.lst.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import com.jvc.projector.dla.swing.buttons.UIEllipticalButton;
import com.jvc.projector.dla.swing.buttons.UIRoundedRectangleButton;
import com.jvc.projector.dla.swing.buttons.UIToggleButton;

public class ButtonTest extends JFrame {

	private JPanel contentPane;

	private static final Color DARK = new Color(173,173,173);
    private static final Color BG = new Color(204,204,204);
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ButtonTest frame = new ButtonTest();
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
	public ButtonTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnRounded = new JButton("Rounded");
		btnRounded.setUI(new UIRoundedRectangleButton(DARK, BG));
		contentPane.add(btnRounded, BorderLayout.WEST);
		
		JButton btnEliptical = new JButton("Eliptical");
		btnEliptical.setUI(new UIEllipticalButton(DARK, BG));
		contentPane.add(btnEliptical, BorderLayout.EAST);
		
		JToggleButton btnToggle = new JToggleButton("toggle");
		btnToggle.setUI(new UIToggleButton(DARK, BG, Color.red));
		contentPane.add(btnToggle, BorderLayout.SOUTH);
	}

}
