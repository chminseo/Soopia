package soopia.hwp.hexdump.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class UnderLineBorder extends AbstractBorder {

	public UnderLineBorder() {
		// TODO Auto-generated constructor stub
	}
	
	  protected int thickness;

	  protected Color lineColor;

	  protected int gap;

	  public UnderLineBorder(Color color) {
	    this(color, 1, 1);
	  }

	  public UnderLineBorder(Color color, int thickness) {
	    this(color, thickness, thickness);
	  }

	  public UnderLineBorder(Color color, int thickness, int gap) {
	    lineColor = color;
	    this.thickness = thickness;
	    this.gap = gap;
	  }

	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	    Color oldColor = g.getColor();
	    int i;

	    g.setColor(lineColor);
	    for (i = 0; i < thickness; i++) {
	      g.drawLine(x, y + height - i - 1, x + width, y + height - i - 1);
	    }
	    g.setColor(oldColor);
	  }

	  public Insets getBorderInsets(Component c) {
	    return new Insets(0, 0, gap, 0);
	  }

	  public Insets getBorderInsets(Component c, Insets insets) {
	    insets.left = 0;
	    insets.top = 0;
	    insets.right = 0;
	    insets.bottom = gap;
	    return insets;
	  }

	  /**
	   * Returns the color of the border.
	   */
	  public Color getLineColor() {
	    return lineColor;
	  }

	  /**
	   * Returns the thickness of the border.
	   */
	  public int getThickness() {
	    return thickness;
	  }

	  /**
	   * Returns whether or not the border is opaque.
	   */
	  public boolean isBorderOpaque() {
	    return false;
	  }

	  public int getGap() {
	    return gap;
	  }

}
