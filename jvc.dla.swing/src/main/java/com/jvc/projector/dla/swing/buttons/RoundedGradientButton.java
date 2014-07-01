/*******************************************************************************
 * Copyright 2012 Universidad Politécnica de Madrid
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.metal.MetalButtonUI;




/**
 * @author amedrano
 *
 */
public class RoundedGradientButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Color light;
	protected Color dark;
	protected Color bLight;
	protected Color bDark;
	
	public RoundedGradientButton(String text, Color light, Color dark) {
		this(text,null,light,dark);
	}
	
	public RoundedGradientButton(String text,Icon icon, Color light, Color dark){
	    super(text,icon);
	    this.light = light;
	    this.dark = dark;
	    this.bLight = light;
	    this.bDark = dark;
	    setBorderPainted(false);
	    setOpaque(true);
	    setUI(new UIRoundedGradientButton());
	    setContentAreaFilled(false);
	    setFocusPainted(false);
	    getShape();
	}
	
	public RoundedGradientButton(AbstractButton button, Color light, Color dark){
		this(button.getText(),button.getIcon(),light,dark);
		ActionListener[] a = button.getActionListeners();
		for (int i = 0; i < a.length; i++) {
			this.addActionListener(a[i]);
		}
		this.setName(button.getName());
	}
	
	protected Border getRaisedBorder() {
	    return new SoftBevelBorder(SoftBevelBorder.RAISED, bDark , bLight);
	}
	
	protected Border getLoweredBorder() {
	    return new SoftBevelBorder(SoftBevelBorder.LOWERED, bDark , bLight);
	}
	
	protected void scaleIcon(int width, int height){
	    setIcon(resizeIcon(getIcon(), width, height));
	}
	
	

	  protected Shape shape, base;

	  
	  protected Shape getShape(){
		  if(!getBounds().equals(base)) {
		      Dimension s = getSize();
		      base = getBounds();
		      shape = new RoundRectangle2D.Float(0,0,s.width-1,s.height-1,17,17);
		    }
		  return shape;
	  }
	  
	  @Override 
	  public boolean contains(int x, int y) {
	    return getShape().contains(x, y);
	  }
	  
	class UIRoundedGradientButton extends MetalButtonUI{

		/** {@inheritDoc} */
		@Override
		public void update(Graphics g, JComponent c) {
			Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

	        ButtonModel m = getModel();

	        Paint oldPaint = g2.getPaint();
	      
	        g2.clip(getShape());
	        g2.setPaint(new GradientPaint(0.0f, 0.0f, light,
	        		0.0f, getHeight(), dark));
	        g2.fillRect(0,0,getWidth(),getHeight());

	        g2.setStroke(new BasicStroke(4f));
	        g2.setPaint(new GradientPaint(0.0f, 0.0f, light,
	        		0.0f, getHeight(), dark));

	        g2.setPaint(oldPaint);
	        
	        if (!m.isRollover()) {
				getRaisedBorder().paintBorder(c, g2, 0, 0, getWidth(),
						getHeight());
			} else {
				Border b = new SoftBevelBorder(SoftBevelBorder.RAISED, bDark , bLight.brighter());
				b.paintBorder(c, g2, 0, 0, getWidth(),
						getHeight());
			}
				paint(g2, c);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        		RenderingHints.VALUE_ANTIALIAS_OFF);
		}

		/** {@inheritDoc} */
		@Override
		protected void paintButtonPressed(Graphics g, AbstractButton b) {
			Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

	        Paint oldPaint = g2.getPaint();
	      
	        g2.clip(getShape());
	        g2.setPaint(new GradientPaint(0.0f, 0.0f, light.brighter(),
	        		0.0f, getHeight(), dark.brighter()));
	        g2.fillRect(0,0,getWidth(),getHeight());

	        g2.setStroke(new BasicStroke(4f));
	        g2.setPaint(new GradientPaint(0.0f, 0.0f, light.brighter(),
	        		0.0f, getHeight(), dark.brighter()));

	        g2.setPaint(oldPaint);
	        getLoweredBorder().paintBorder(b, g2, 0, 0, getWidth(), getHeight());
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        		RenderingHints.VALUE_ANTIALIAS_OFF);
		}

		/** {@inheritDoc} */
		@Override
		protected void paintFocus(Graphics g, AbstractButton b,
				Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
			
		}
		
	}
	
    public static Icon resizeIcon(Icon icon, int width, int height){
    	Icon output = null;
    	if (icon != null){
        	Image img = ((ImageIcon) icon).getImage();  
        	Image newimg = img.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH );  
        	output  = new ImageIcon( newimg );
        }
    	return output;
    }
}
