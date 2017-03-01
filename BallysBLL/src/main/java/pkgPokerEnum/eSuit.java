package pkgPokerEnum;

public enum eSuit {

	HEARTS(1), SPADES(2), CLUBS(3), DIAMONDS(4);
	
	
	private int iSuitNbr;

	private eSuit(int iSuitNbr) {
		this.iSuitNbr = iSuitNbr;
	}

	public int getiSuitNbr() {
		return iSuitNbr;
	}
	
	
}
