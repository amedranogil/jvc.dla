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

	private Color dark;
	private Color light;

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

		ButtonModel m = ((AbstractButton) c).getModel();

		Paint oldPaint = g2.getPaint();

		g2.clip(getShape(c));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light, 0.0f, c.getHeight(),
				dark));
		g2.fillRect(0, 0, c.getWidth(), c.getHeight());

		g2.setStroke(new BasicStroke(4f));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light, 0.0f, c.getHeight(),
				dark));

		g2.setPaint(oldPaint);

		if (!m.isRollover()) {
			getRaisedBorder().paintBorder(c, g2, 0, 0, c.getWidth(),
					c.getHeight());
		} else {
			Border b = new SoftBevelBorder(SoftBevelBorder.RAISED, dark,
					light.brighter());
			b.paintBorder(c, g2, 0, 0, c.getWidth(), c.getHeight());
		}
		paint(g2, c);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_DEFAULT);
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

		g2.clip(getShape(b));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light.brighter(), 0.0f, b
				.getHeight(), dark.brighter()));
		g2.fillRect(0, 0, b.getWidth(), b.getHeight());

		g2.setStroke(new BasicStroke(4f));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, light.brighter(), 0.0f, b
				.getHeight(), dark.brighter()));

		g2.setPaint(oldPaint);
		getLoweredBorder()
				.paintBorder(b, g2, 0, 0, b.getWidth(), b.getHeight());
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