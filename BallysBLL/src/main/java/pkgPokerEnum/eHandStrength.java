package pkgPokerEnum;

public enum eHandStrength {

	//TODO: Add "AcesAndEights" between Flush and FullHouse
	
	RoyalFlush(100, "isHandRoyalFlush") {
		public String toString() {
			return "Royal Flush";
		}
	},
	StraightFlush(90, "isHandStraightFlush") {
		public String toString() {
			return "Straight Flush";
		}
	},
	FourOfAKind(80, "isHandFourOfAKind") {
		public String toString() {
			return "Four of a Kind";
		}
	},
	FullHouse(70, "isHandFullHouse") {
		public String toString() {
			return "Full House";
		}
	},
	Flush(60, "isHandFlush") {
		public String toString() {
			return "Flush";
		}
	},
	Straight(50, "isHandStraight") {
		public String toString() {
			return "Straight";
		}
	},
	ThreeOfAKind(40, "isHandThreeOfAKind") {
		public String toString() {
			return "Three of a Kind";
		}
	},
	TwoPair(30, "isHandTwoPair") {
		public String toString() {
			return "Two Pairs";
		}
	},

	Pair(20, "isHandPair") {
		public String toString() {
			return "One Pair";
		}
	},
	HighCard(10, "isHandHighCard") {
		public String toString() {
			return "High Card";
		}
	};

	private eHandStrength(final int handstrength, final String EvalMethod) {
		this.iHandStrength = handstrength;
		this.strEvalMethod = EvalMethod;
	}

	private int iHandStrength;
	private String strEvalMethod;

	public int getHandStrength() {
		return iHandStrength;
	}

	public String getEvalMethod() {
		return this.strEvalMethod;
	}

}