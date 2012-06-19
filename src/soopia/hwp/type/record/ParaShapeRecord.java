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
 * 4.1.10 문단 모양
 * TagID : HWPTAG_PARA_SHAPE
 * 
 * @author chmin
 * @page p.23
 */
public class ParaShapeRecord extends AbstractRecord {

	/**
	 * 줄간격 종류 - 글자 크기에 따라(%)
	 */
	final public static int PROP_LH_BY_FONTSIZE = 0;
	/**
	 * 줄간격 종류 - 고정값
	 */
	final public static int PROP_LH_STATIC = 1;
	/**
	 * 줄간격 종류 - 여백만 지정
	 */
	final public static int PROP_LH_BLANK = 2;
	/**
	 * 줄간격 종류 - 최소
	 */
	final public static int PROP_LH_MINIMUM = 3;
	/**
	 * 가로 정렬 - 양쪽
	 */
	final public static int PROP_ALIGN_H_BOTH = 0;
	/**
	 * 가로 정렬 - 왼쪽
	 */
	final public static int PROP_ALIGN_H_LEFT = 1;
	/**
	 * 가로 정렬 - 오른쪽
	 */
	final public static int PROP_ALIGN_H_RIGHT = 2;
	/**
	 * 가로 정렬 - 가운데
	 */
	final public static int PROP_ALIGN_H_CENTER = 3;
	/**
	 * 가로 정렬 - 배분
	 * {@link http://blog.naver.com/queenlog/20155178140}
	 */
	final public static int PROP_ALIGN_H_EQ = 4;
	/**
	 * 가로 정렬 - 나눔
	 * {@link http://blog.naver.com/queenlog/20155178140}
	 */
	final public static int PROP_ALIGN_H_DIV = 5;
	
	
	/*
	 * 속성
	 */
	private UInt32 property ;
	/*
	 * 왼쪽 여백
	 */
	private HwpUnit paddingLeft ;
	/*
	 * 오른쪽 여백
	 */
	private HwpUnit paddingRight;
	/*
	 * 들여쓰기/내어쓰기
	 */
	private HwpUnit indentation ;
	/*
	 * 위쪽 여백
	 */
	private HwpUnit paddingTop;
	/*
	 * 아래쪽 여백
	 */
	private HwpUnit paddingBottom ;
	/*
	 * 2007 버전 이하 줄간격
	 */
	private HwpUnit lineHeight; // 줄간격
	/*
	 * HWP_TAB_DEF 참조 id
	 */
	private UInt16 tabDefId;
	/*
	 * HWP_NUMBERING 참조 ID 또는 HWP_BULLET 참조 ID
	 */
	private UInt16 numberingBulletId;
	/*
	 * HWP_BORDER_FILL 참조 ID
	 */
	private UInt16 borderFillId;
	/*
	 * 문단 테두리 왼쪽 간격
	 */
	private HwpUnit16 leftBorderMargin;
	/*
	 * 문단 테두리 오른쪽 간격
	 */
	private HwpUnit16 rightBorderMargin;
	/*
	 * 문단 테두리 위쪽 간격
	 */
	private HwpUnit16 topBorderMargin;
	/*
	 * 문단 테두리 아래쪽 간격
	 */
	private HwpUnit16 bottomBorderMargin;
	/*
	 * 속성2
	 */
	private UInt32 property2;
	/*
	 * 속성3(줄간격) 
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
			// TODO Auto-generated constructor stub
			prop1 = p1;
			prop2 = p2;
			prop3 = p3;
		}
		public int getLineHeightType() {
			// TEST
			return Converter.getBits(prop1.getValue().intValue(), 0, 2);
		}
		/**
		 * 가로 정렬 방식
		 * @return
		 */
		public int getHorizontalAlignment() {
			// TEST
			return Converter.getBits(prop1.getValue().intValue(), 3, 3);
		}
		/**
		 * 줄나눔 기준 - 영어
		 * @return
		 */
		public int getLineWrapPolicyEn() {
			// TEST
			return 0;
		}
		/**
		 * 줄나눔 기준 - 한글
		 * @return
		 */
		public int getLineWrapPolicyKr() {
			// TEST
			return 0;
		}
		/**
		 * 편집 용지의 줄 격자 사용 여부
		 */
		public boolean isLatticeVisible() {
			//TEST
			return false;
		}
		/**
		 * 공백 최소값(%)
		 * @return
		 */
		public int getMinSpaceWidth () {
			return 0;
		}
		/**
		 * 외톨이줄 보호 여부
		 */
		public boolean isSingleLineWrapAllowed() {
			//
			return false;
		}
		/**
		 * 다음 문단과 함께 여부 
		 * - 현재 문단과 다음에 나오는 문단이 항상 같은 페이지 내에서 보여지는지 설정함. 
		 */
		public boolean isNextParaAttached() {
			// TEST
			return false;
		}
		/**
		 * 문단 보호 여부
		 * - 현재 문단이 항상 같은 페이지 내에서 보여지는지 설정함. 출력되도록 하는지 설정함.
		 * @return
		 */
		public boolean isAtomicParagraph() {
			// TEST
			// MEMO 문단 보호를 설정했어도 하나의 문단 길이가 페이지를 넘어가는 경우 
			//      맨 처음 한 번만 문단 보호가 실행되고 그 이후부터는 페이지를 넘어가더라도 
			//      문단은 여러 페이지에 걸쳐 나뉘게 된다.
			return false;
		}
		/**
		 * 문단 앞에서 항상 쪽나눔 여부
		 * @return
		 */
		public boolean isStartInNewPage () {
			// TEST
			return false;
		}
		/**
		 * 세로 정렬 방식
		 * @return
		 */
		public int getVerticalAlignment() {
			// TEST
			return 0;
		}
		/**
		 * 문단 머리 종류 - 없음, 개요, 번호, 글머리표
		 * @return
		 */
		public int getParagraphType() {
			// TEST
			return 0;
		}
		/**
		 * 문단 수준 (1~ 7 수준)
		 * @return
		 */
		public int getLevel() {
			// TEST
			return 0;
		}
		/**
		 * 문단 연결 여부
		 * @return
		 */
		public boolean isBorderCoupled() {
			// TEST
			return false;
		}
		/**
		 * 문단 여백 무시 ( 테두리 그릴 때)
		 * @return
		 */
		public boolean isParaMarginIgnored() {
			// TEST
			return false;
		}
		/**
		 * 문단 꼬리 모양 (무엇인지 알 수 없음)
		 * @return
		 */
		public int getParaTailType() {
			// TEST
			return 0;
		}
	}

}
