package soopia.hwp.type.record;

import soopia.hwp.type.AbstractRecord;
import soopia.hwp.type.HwpUnit;
import soopia.hwp.type.HwpUnit16;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.stream.RecordHeader;
import soopia.hwp.util.Converter;
/**
 * 4.1.10 ���� ���
 * TagID : HWPTAG_PARA_SHAPE
 * 
 * @author chmin
 * @page p.23
 */
public class ParaShapeRecord extends AbstractRecord {

	/**
	 * �ٰ��� ���� - ���� ũ�⿡ ����(%)
	 */
	final public static int PROP_LH_BY_FONTSIZE = 0;
	/**
	 * �ٰ��� ���� - ������
	 */
	final public static int PROP_LH_STATIC = 1;
	/**
	 * �ٰ��� ���� - ���鸸 ����
	 */
	final public static int PROP_LH_BLANK = 2;
	/**
	 * �ٰ��� ���� - �ּ�
	 */
	final public static int PROP_LH_MINIMUM = 3;
	/**
	 * ���� ���� - ����
	 */
	final public static int PROP_ALIGN_H_BOTH = 0;
	/**
	 * ���� ���� - ����
	 */
	final public static int PROP_ALIGN_H_LEFT = 1;
	/**
	 * ���� ���� - ������
	 */
	final public static int PROP_ALIGN_H_RIGHT = 2;
	/**
	 * ���� ���� - ���
	 */
	final public static int PROP_ALIGN_H_CENTER = 3;
	/**
	 * ���� ���� - ���
	 * {@link http://blog.naver.com/queenlog/20155178140}
	 */
	final public static int PROP_ALIGN_H_EQ = 4;
	/**
	 * ���� ���� - ����
	 * {@link http://blog.naver.com/queenlog/20155178140}
	 */
	final public static int PROP_ALIGN_H_DIV = 5;
	
	
	final public static int PROP_ALIGN_V_BY_FONT = 0;
	final public static int PROP_ALIGN_V_TOP = 1;
	final public static int PROP_ALIGN_V_MIDDLE = 2;
	final public static int PROP_ALIGN_V_BOTTOM = 3;
	
	/**
	 * �� ���� ���� - �ܾ�, ����
	 */
	final public static int PROP_LINEWRAP_BY_WORD = 0 ;
	/**
	 * �� ���� ���� - ������(���)
	 */
	final public static int PROP_LINEWRAP_BY_HYPEN = 1 ;
	/**
	 * �� ���� ���� - ����
	 */
	final public static int PROP_LINEWRAP_BY_CH = 2 ;
	
	
	final public static int PROP_HEADER_NONE = 0;
	final public static int PROP_HEADER_OUTLINE = 1;
	final public static int PROP_HEADER_NUMBERING = 2;
	final public static int PROP_HEADER_BULLET = 2;
	
	/*
	 * �Ӽ�
	 */
	private UInt32 property ;
	/*
	 * ���� ����
	 */
	private HwpUnit paddingLeft ;
	/*
	 * ������ ����
	 */
	private HwpUnit paddingRight;
	/*
	 * �鿩����/�����
	 */
	private HwpUnit indentation ;
	/*
	 * ���� ����
	 */
	private HwpUnit paddingTop;
	/*
	 * �Ʒ��� ����
	 */
	private HwpUnit paddingBottom ;
	/*
	 * 2007 ���� ���� �ٰ���
	 */
	private HwpUnit lineHeight; // �ٰ���
	/*
	 * HWP_TAB_DEF ���� id
	 */
	private UInt16 tabDefId;
	/*
	 * HWP_NUMBERING ���� ID �Ǵ� HWP_BULLET ���� ID
	 */
	private UInt16 numberingBulletId;
	/*
	 * HWP_BORDER_FILL ���� ID
	 */
	private UInt16 borderFillId;
	/*
	 * ���� �׵θ� ���� ����
	 */
	private HwpUnit16 leftBorderMargin;
	/*
	 * ���� �׵θ� ������ ����
	 */
	private HwpUnit16 rightBorderMargin;
	/*
	 * ���� �׵θ� ���� ����
	 */
	private HwpUnit16 topBorderMargin;
	/*
	 * ���� �׵θ� �Ʒ��� ����
	 */
	private HwpUnit16 bottomBorderMargin;
	/*
	 * �Ӽ�2
	 */
	private UInt32 property2;
	/*
	 * �Ӽ�3(�ٰ���) 
	 */
	private UInt32 lineHeighProperty;
	
	private PropertyHandler handler;
	
	public ParaShapeRecord(RecordHeader header, IStreamStruct ds) {
		super(header, ds);
	}

	public UInt32 getProperty() {
		return property;
	}

	public void setProperty(UInt32 property) {
		this.property = property;
	}

	public HwpUnit getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(HwpUnit paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public HwpUnit getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(HwpUnit paddingRight) {
		this.paddingRight = paddingRight;
	}

	public HwpUnit getIndentation() {
		return indentation;
	}

	public void setIndentation(HwpUnit indentation) {
		this.indentation = indentation;
	}

	public HwpUnit getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(HwpUnit paddingTop) {
		this.paddingTop = paddingTop;
	}

	public HwpUnit getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(HwpUnit paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	public HwpUnit getLineHeight() {
		return lineHeight;
	}

	public void setLineHeight(HwpUnit lineHeight) {
		this.lineHeight = lineHeight;
	}

	public UInt16 getTabDefId() {
		return tabDefId;
	}

	public void setTabDefId(UInt16 tabDefId) {
		this.tabDefId = tabDefId;
	}

	public UInt16 getNumberingBulletId() {
		return numberingBulletId;
	}

	public void setNumberingBulletId(UInt16 numberingBulletId) {
		this.numberingBulletId = numberingBulletId;
	}

	public UInt16 getBorderFillId() {
		return borderFillId;
	}

	public void setBorderFillId(UInt16 borderFillId) {
		this.borderFillId = borderFillId;
	}

	public HwpUnit16 getBorderMarginLeft() {
		return leftBorderMargin;
	}

	public void setBorderMarginLeft(HwpUnit16 leftBorderMargin) {
		this.leftBorderMargin = leftBorderMargin;
	}

	public HwpUnit16 getBorderMarginRight() {
		return rightBorderMargin;
	}

	public void setBorderMarginRight(HwpUnit16 rightBorderMargin) {
		this.rightBorderMargin = rightBorderMargin;
	}

	public HwpUnit16 getBorderMarginTop() {
		return topBorderMargin;
	}

	public void setBorderMarginTop(HwpUnit16 topBorderMargin) {
		this.topBorderMargin = topBorderMargin;
	}

	public HwpUnit16 getBorderMarginBottom() {
		return bottomBorderMargin;
	}

	public void setBorderMarginBottom(HwpUnit16 bottomBorderMargin) {
		this.bottomBorderMargin = bottomBorderMargin;
	}

	public UInt32 getProperty2() {
		return property2;
	}

	public void setProperty2(UInt32 property2) {
		this.property2 = property2;
	}

	public UInt32 getPropertyForLineHeigh() {
		return lineHeighProperty;
	}

	public void setPropertyForLineHeigh(UInt32 lineHeighProperty) {
		this.lineHeighProperty = lineHeighProperty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((borderFillId == null) ? 0 : borderFillId.hashCode());
		result = prime
				* result
				+ ((bottomBorderMargin == null) ? 0 : bottomBorderMargin
						.hashCode());
		result = prime * result
				+ ((indentation == null) ? 0 : indentation.hashCode());
		result = prime
				* result
				+ ((leftBorderMargin == null) ? 0 : leftBorderMargin.hashCode());
		result = prime
				* result
				+ ((lineHeighProperty == null) ? 0 : lineHeighProperty
						.hashCode());
		result = prime * result
				+ ((lineHeight == null) ? 0 : lineHeight.hashCode());
		result = prime
				* result
				+ ((numberingBulletId == null) ? 0 : numberingBulletId
						.hashCode());
		result = prime * result
				+ ((paddingBottom == null) ? 0 : paddingBottom.hashCode());
		result = prime * result
				+ ((paddingLeft == null) ? 0 : paddingLeft.hashCode());
		result = prime * result
				+ ((paddingRight == null) ? 0 : paddingRight.hashCode());
		result = prime * result
				+ ((paddingTop == null) ? 0 : paddingTop.hashCode());
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		result = prime * result
				+ ((property2 == null) ? 0 : property2.hashCode());
		result = prime
				* result
				+ ((rightBorderMargin == null) ? 0 : rightBorderMargin
						.hashCode());
		result = prime * result
				+ ((tabDefId == null) ? 0 : tabDefId.hashCode());
		result = prime * result
				+ ((topBorderMargin == null) ? 0 : topBorderMargin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParaShapeRecord other = (ParaShapeRecord) obj;
		if (borderFillId == null) {
			if (other.borderFillId != null)
				return false;
		} else if (!borderFillId.equals(other.borderFillId))
			return false;
		if (bottomBorderMargin == null) {
			if (other.bottomBorderMargin != null)
				return false;
		} else if (!bottomBorderMargin.equals(other.bottomBorderMargin))
			return false;
		if (indentation == null) {
			if (other.indentation != null)
				return false;
		} else if (!indentation.equals(other.indentation))
			return false;
		if (leftBorderMargin == null) {
			if (other.leftBorderMargin != null)
				return false;
		} else if (!leftBorderMargin.equals(other.leftBorderMargin))
			return false;
		if (lineHeighProperty == null) {
			if (other.lineHeighProperty != null)
				return false;
		} else if (!lineHeighProperty.equals(other.lineHeighProperty))
			return false;
		if (lineHeight == null) {
			if (other.lineHeight != null)
				return false;
		} else if (!lineHeight.equals(other.lineHeight))
			return false;
		if (numberingBulletId == null) {
			if (other.numberingBulletId != null)
				return false;
		} else if (!numberingBulletId.equals(other.numberingBulletId))
			return false;
		if (paddingBottom == null) {
			if (other.paddingBottom != null)
				return false;
		} else if (!paddingBottom.equals(other.paddingBottom))
			return false;
		if (paddingLeft == null) {
			if (other.paddingLeft != null)
				return false;
		} else if (!paddingLeft.equals(other.paddingLeft))
			return false;
		if (paddingRight == null) {
			if (other.paddingRight != null)
				return false;
		} else if (!paddingRight.equals(other.paddingRight))
			return false;
		if (paddingTop == null) {
			if (other.paddingTop != null)
				return false;
		} else if (!paddingTop.equals(other.paddingTop))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (property2 == null) {
			if (other.property2 != null)
				return false;
		} else if (!property2.equals(other.property2))
			return false;
		if (rightBorderMargin == null) {
			if (other.rightBorderMargin != null)
				return false;
		} else if (!rightBorderMargin.equals(other.rightBorderMargin))
			return false;
		if (tabDefId == null) {
			if (other.tabDefId != null)
				return false;
		} else if (!tabDefId.equals(other.tabDefId))
			return false;
		if (topBorderMargin == null) {
			if (other.topBorderMargin != null)
				return false;
		} else if (!topBorderMargin.equals(other.topBorderMargin))
			return false;
		return true;
	}
	
	public PropertyHandler getPropertyHandler() {
		if ( handler == null ) {
			handler = new PropertyHandler(property, property2, lineHeighProperty);
		}
		return handler;
	}
	
	public static class PropertyHandler {
		private UInt32 prop1, prop2, prop3;
		
		public PropertyHandler(UInt32 p1, UInt32 p2, UInt32 p3) {
			prop1 = p1;
			prop2 = p2;
			prop3 = p3;
		}
		public int getLineHeightType() {
			return Converter.getBits(prop1.getValue().intValue(), 0, 2);
		}
		/**
		 * ���� ���� ���
		 * @return
		 */
		public int getHorizontalAlignment() {
			return Converter.getBits(prop1.getValue().intValue(), 3, 3);
		}
		/**
		 * �ٳ��� ���� - ����
		 * @return
		 */
		public int getLineWrapPolicyEn() {
			return Converter.getBits(prop1.getValue().intValue(), 5, 2);
		}
		/**
		 * �ٳ��� ���� - �ѱ�
		 * @return
		 */
		public int getLineWrapPolicyKr() {
			int val = Converter.getBits(prop1.getValue().intValue(), 7, 1); 
			return val == 1 ? PROP_LINEWRAP_BY_WORD : PROP_LINEWRAP_BY_CH;
		}
		/**
		 * ���� ������ �� ���� ��� ����
		 */
		public boolean isLatticeVisible() {
			return Converter.getBits(prop1.getValue().intValue(), 8, 1) == 0;
		}
		/**
		 * ���� �ּҰ�(%)
		 * @return
		 */
		public int getMinSpaceWidth () {
			return Converter.getBits(prop1.getValue().intValue(), 9, 7);
		}
		/**
		 * �������� ��ȣ ����
		 */
		public boolean isSingleLineWrapAllowed() {
			return Converter.getBits(prop1.getValue().intValue(), 16, 1) == 1 ;
		}
		/**
		 * ���� ���ܰ� �Բ� ���� 
		 * - ���� ���ܰ� ������ ������ ������ �׻� ���� ������ ������ ���������� ������. 
		 */
		public boolean isNextParaAttached() {
			return Converter.getBits(prop1.getValue().intValue(), 17, 1) == 1 ;
		}
		/**
		 * ���� ��ȣ ����
		 * - ���� ������ �׻� ���� ������ ������ ���������� ������. ��µǵ��� �ϴ��� ������.
		 * @return
		 */
		public boolean isAtomicParagraph() {
			// MEMO ���� ��ȣ�� �����߾ �ϳ��� ���� ���̰� �������� �Ѿ�� ��� 
			//      �� ó�� �� ���� ���� ��ȣ�� ����ǰ� �� ���ĺ��ʹ� �������� �Ѿ���� 
			//      ������ ���� �������� ���� ������ �ȴ�.
			return Converter.getBits(prop1.getValue().intValue(), 18, 1) == 1;
		}
		/**
		 * ���� �տ��� �׻� �ʳ��� ����
		 * @return
		 */
		public boolean isStartInNewPage () {
			return Converter.getBits(prop1.getValue().intValue(), 19, 1) == 1;
		}
		/**
		 * ���� ���� ���
		 * @return
		 */
		public int getVerticalAlignment() {
			return Converter.getBits(prop1.getValue().intValue(), 20, 2);
		}
		/**
		 * �۲ÿ� ��︮�� �� ���� ��� ����
		 * @return
		 */
		public boolean isAutoLineHeight() {
			return Converter.getBits(prop1.getValue().intValue(), 22, 1) == 1;
		}
		/**
		 * ���� �Ӹ� ���� - ����, ����, ��ȣ, �۸Ӹ�ǥ
		 * @return
		 */
		public int getParagraphType() {
			return Converter.getBits(prop1.getValue().intValue(), 23, 2);
		}
		/**
		 * ���� ���� (1~ 7 ����)
		 * @return
		 */
		public int getLevel() {
			return Converter.getBits(prop1.getValue().intValue(), 25, 3);
		}
		/**
		 * ���� �׵θ� ���� ����
		 * @return
		 */
		public boolean isBorderCoupled() {
			return Converter.getBits(prop1.getValue().intValue(), 28, 1) == 1;
		}
		/**
		 * ���� ���� ���� ( �׵θ� �׸� ��)
		 * @return
		 */
		public boolean isParaMarginIgnored() {
			return Converter.getBits(prop1.getValue().intValue(), 29, 1) == 1;
		}
		/**
		 * ���� ���� ��� (�������� �� �� ����)
		 * @return
		 */
		public int getParaTailType() {
			// TEST ����μ��� �ǹ��ϴ� �ٸ� ���� ����
			return Converter.getBits(prop1.getValue().intValue(), 30, 1);
		}
		/**
		 * �� �ٷ� �Է� ����
		 * @return
		 */
		public boolean isOneLinePara() {
			// TEST
			return Converter.getBits(prop1.getValue().intValue(), 0, 2) == 1;
		}
		/**
		 * �ѱ۰� ���� ���� �ڵ� ���� ����
		 * @return
		 */
		public boolean isAutoAdjustingEnKr() {
			// TEST
			return Converter.getBits(prop1.getValue().intValue(), 4, 1) == 1;
		}
		/**
		 * �ѱ۰� ���� ���� �ڵ� ���� ����
		 * @return
		 */
		public boolean isAutoAdjustingKrNum() {
			// TEST
			return Converter.getBits(prop1.getValue().intValue(), 5, 1) == 1;
		}
	}

}
