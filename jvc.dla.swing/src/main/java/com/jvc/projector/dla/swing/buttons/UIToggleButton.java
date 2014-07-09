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

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import javax.swing.JComponent;
import javax.swing.JToggleButton;

/**
 * @author amedrano
 *
 */
public class UIToggleButton extends UIGradientButton {


	protected Shape shape, base;
	private Color led;
	
	/**
	 * @param dark
	 * @param background
	 */
	public UIToggleButton(Color dark, Color background , Color light) {
		super(dark, background);
		this.led = light;
	}
	
	/** {@inheritDoc} */
	@Override
	public void update(Graphics g, JComponent c) {
		super.update(g, c);
		if (c instanceof JToggleButton 
				&& ((JToggleButton)c).isSelected()){
			Graphics2D g2 = (Graphics2D) g;
			Dimension d = c.getSize();
			RoundGradientPaint rgp = new RoundGradientPaint(d.width/2, d.height/2, led,
					new Point2D.Double(0, Math.min(d.width/2, d.height/2)), new Color(255, 255, 255, 0));
			g2.setPaint(rgp);
			g2.fill(getShape(c));
			paint(g2, c);
		}
	}
	
	
	/**{@inheritDoc} */
	@Override
	protected Shape getShape(JComponent j) {
		if (!j.getBounds().equals(base)) {
			Dimension s = j.getSize();
			base = j.getBounds();
			shape = new RoundRectangle2D.Float(0, 0, s.width - 1, s.height - 1,
					17, 17);
		}
		return shape;
	}

	  class RoundGradientPaint implements Paint {
		    protected Point2D point;

		    protected Point2D mRadius;

		    protected Color mPointColor, mBackgroundColor;

		    public RoundGradientPaint(double x, double y, Color pointColor,
		        Point2D radius, Color backgroundColor) {
		      if (radius.distance(0, 0) <= 0)
		        throw new IllegalArgumentException("Radius must be greater than 0.");
		      point = new Point2D.Double(x, y);
		      mPointColor = pointColor;
		      mRadius = radius;
		      mBackgroundColor = backgroundColor;
		    }

		    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds,
		        Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
		      Point2D transformedPoint = xform.transform(point, null);
		      Point2D transformedRadius = xform.deltaTransform(mRadius, null);
		      return new RoundGradientContext(transformedPoint, mPointColor,
		          transformedRadius, mBackgroundColor);
		    }

		    public int getTransparency() {
		      int a1 = mPointColor.getAlpha();
		      int a2 = mBackgroundColor.getAlpha();
		      return (((a1 & a2) == 0xff) ? OPAQUE : TRANSLUCENT);
		    }
		  }
		  public class RoundGradientContext implements PaintContext {
		    protected Point2D mPoint;

		    protected Point2D mRadius;

		    protected Color color1, color2;

		    public RoundGradientContext(Point2D p, Color c1, Point2D r, Color c2) {
		      mPoint = p;
		      color1 = c1;
		      mRadius = r;
		      color2 = c2;
		    }

		    public void dispose() {
		    }

		    public ColorModel getColorModel() {
		      return ColorModel.getRGBdefault();
		    }

		    public Raster getRaster(int x, int y, int w, int h) {
		      WritableRaster raster = getColorModel().createCompatibleWritableRaster(
		          w, h);

		      int[] data = new int[w * h * 4];
		      for (int j = 0; j < h; j++) {
		        for (int i = 0; i < w; i++) {
		          double distance = mPoint.distance(x + i, y + j);
		          double radius = mRadius.distance(0, 0);
		          double ratio = distance / radius;
		          if (ratio > 1.0)
		            ratio = 1.0;

		          int base = (j * w + i) * 4;
		          data[base + 0] = (int) (color1.getRed() + ratio
		              * (color2.getRed() - color1.getRed()));
		          data[base + 1] = (int) (color1.getGreen() + ratio
		              * (color2.getGreen() - color1.getGreen()));
		          data[base + 2] = (int) (color1.getBlue() + ratio
		              * (color2.getBlue() - color1.getBlue()));
		          data[base + 3] = (int) (color1.getAlpha() + ratio
		              * (color2.getAlpha() - color1.getAlpha()));
		        }
		      }
		      raster.setPixels(0, 0, w, h, data);

		      return raster;
		    }
		  }
}
