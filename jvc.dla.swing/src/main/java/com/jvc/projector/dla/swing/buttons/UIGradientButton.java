package com.jvc.projector.dla.swing.buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.metal.MetalButtonUI;

abstract class UIGradientButton extends MetalButtonUI {

	protected Color dark;
	protected Color light;

	public UIGradientButton(Color dark, Color light) {
		super();
		this.dark = dark;
		this.light = light;
	}

	protected Border getRaisedBorder() {
		return new SoftBevelBorder(SoftBevelBorder.RAISED, dark, light);
	}

	protected Border getLoweredBorder() {
		return new SoftBevelBorder(SoftBevelBorder.LOWERED, dark, light);
	}

	/** {@inheritDoc} */
	@Override
	public void update(Graphics g, JComponent c) {
		c.setOpaque(false);
		((AbstractButton) c).setBorderPainted(false);
	    ((AbstractButton) c).setContentAreaFilled(false);
	    ((AbstractButton) c).setFocusPainted(false);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();
		
		updateBorder(g2, c);
		
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light, 0.0f, c.getHeight(),
				dark));
		g2.fill(getShape(c));

		g2.setStroke(new BasicStroke(4f));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light, 0.0f, c.getHeight(),
				dark));

		g2.setPaint(oldPaint);
		
		paint(g2, c);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_DEFAULT);
	}
	
	protected int getBorderSize() {
		return 3;
	}

	private void updateBorder(Graphics2D g2, JComponent c) {
		ButtonModel m = ((AbstractButton) c).getModel();
		Color s1,s2,s3;
		Shape s = getShape(c);
		if (!m.isRollover()) {
			s1 = dark.darker().darker();
			s2 = dark.darker();
			s3 = light.darker();
		} else {
			s1 = light.darker().darker();
			s2 = light.darker();
			s3 = dark.brighter();
		}
		g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0));
		g2.setColor(s1);
		g2.draw(s);
		g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0));
		g2.setColor(s2);
		g2.draw(s);
		g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0));
		g2.setColor(s3);
		g2.draw(s);
	}
	
	protected abstract Shape getShape(JComponent j);

	@Override
	public boolean contains(JComponent j, int x, int y) {
		return getShape(j).contains(x, y);
	}

	/** {@inheritDoc} */
	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

//		g2.clip(getShape(b));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light.brighter(), 0.0f, b
				.getHeight(), dark.brighter()));
//		g2.fillRect(0, 0, b.getWidth(), b.getHeight());
		g2.fill(getShape(b));

		g2.setStroke(new BasicStroke(4f));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light.brighter(), 0.0f, b
				.getHeight(), dark.brighter()));

		g2.setPaint(oldPaint);
//		getLoweredBorder()
//				.paintBorder(b, g2, 0, 0, b.getWidth(), b.getHeight());
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_DEFAULT);
	}

	/** {@inheritDoc} */
	@Override
	protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect,
			Rectangle textRect, Rectangle iconRect) {

	}

	public static Icon resizeIcon(Icon icon, int width, int height) {
		Icon output = null;
		if (icon != null) {
			Image img = ((ImageIcon) icon).getImage();
			Image newimg = img.getScaledInstance(width, height,
					java.awt.Image.SCALE_SMOOTH);
			output = new ImageIcon(newimg);
		}
		return output;
	}

}