package pkgPokerBLL;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;

import pkgPokerEnum.eCardNo;
import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;

public class Hand {

	private UUID HandID;
	private boolean bIsScored;
	private HandScore HS;
	private ArrayList<Card> CardsInHand = new ArrayList<Card>();

	public Hand() {
		HandID = UUID.randomUUID();
	}

	public void AddCardToHand(Card c) {
		CardsInHand.add(c);
	}

	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}

	public HandScore getHandScore() {
		return HS;
	}

	public Hand EvaluateHand() {
		Hand h = Hand.EvaluateHand(this);
		return h;
	}

	private static Hand EvaluateHand(Hand h) {

		Collections.sort(h.getCardsInHand());

		// Another way to sort
		// Collections.sort(h.getCardsInHand(), Card.CardRank);

		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pkgPokerBLL.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pkgPokerBLL.Hand.class;
				cArg[1] = pkgPokerBLL.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
			}

			h.bIsScored = true;
			h.HS = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}

	
	public static boolean isHandRoyalFlush(Hand h, HandScore hs) {
		
		if ((isHandFlush(h, hs) == true) && (isHandStraight(h, hs) == true)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.KING)){
			hs.setHandStrength(eHandStrength.RoyalFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			return true;
		}	
		else
			return false;

	}

	public static boolean isHandStraightFlush(Hand h, HandScore hs) {

		if ((isHandFlush(h, hs) == true) && (isHandStraight(h, hs) == true)
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() != eRank.KING)){
			hs.setHandStrength(eHandStrength.StraightFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			return true;
		}	
		else
			return false;
	}

	public static boolean isHandFourOfAKind(Hand h, HandScore hs) {

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank()) == (h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())){
			kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank());
			return true;
		}
		else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()) == (h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())){
			kickers.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			return true;
		}
		else
			return false;
	}

	public static boolean isHandFlush(Hand h, HandScore hs) {
			ArrayList<Card> kickers = new ArrayList<Card>();
			if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
					.get(eCardNo.SecondCard.getCardNo()).geteSuit()
					&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
							.get(eCardNo.ThirdCard.getCardNo()).geteSuit())
					&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
							.get(eCardNo.FourthCard.getCardNo()).geteSuit())
					&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
							.get(eCardNo.FifthCard.getCardNo()).geteSuit())) {
				hs.setHandStrength(eHandStrength.Flush);
				hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());				
				return true;
			}

			else
				return false;
		}

	public static boolean isHandStraight(Hand h, HandScore hs) {
		boolean isHandStraight = false;

		ArrayList<Card> kickers = new ArrayList<Card>();

		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank()
				.getiRankNbr() == (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr()
						+ 1))) {

			if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()
					.getiRankNbr() == (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr()
							+ 1))) {
				if ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr() == (h
						.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() + 1))) {

					if ((h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() == (h
							.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr() + 1))) {
						isHandStraight = true;
						hs.setHandStrength(eHandStrength.Straight);

						hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
						hs.setLoHand(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank());
						hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
						hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));

					}
				}
			}
		}

		return isHandStraight;
	}

	public static boolean isHandThreeOfAKind(Hand h, HandScore hs) {
		boolean isThreeOfAKind = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())) {
			isThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);

			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));

		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);

			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));

		} else if ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);

			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));

		}
		return isThreeOfAKind;
	}

	
	public static boolean isHandTwoPair(Hand h, HandScore hs) {
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank()) == (h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& ((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()) == (h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank()))
				&& (isHandFourOfAKind(h, hs) == false) && (isHandFullHouse(h, hs) == false)){
			kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			return true;
			
		}
			
		else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank()) == (h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& ((h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()) == (h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank()))
				&& (isHandFourOfAKind(h, hs) == false) && (isHandFullHouse(h, hs) == false)){
			kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			return true;
		}
		else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()) == (h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& ((h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()) == (h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank()))
				&& (isHandFourOfAKind(h, hs) == false) && (isHandFullHouse(h, hs) == false)){
			kickers.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			return true;
		}
		else
			return false;
	}


	public static boolean isHandPair(Hand h, HandScore hs) {
		boolean isHandPair = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		if((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
					.get(eCardNo.SecondCard.getCardNo()).geteSuit())
				&&(isHandAcesAndEights(h, hs)==false)&&(isHandFourOfAKind(h, hs)==false)
				&&(isHandTwoPair(h, hs)==false)&&(isHandThreeOfAKind(h, hs)==false)){
			kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			isHandPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setHandStrength(eHandStrength.Pair);
			
		}
		else if((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteSuit() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteSuit())
			&&(isHandAcesAndEights(h, hs)==false)&&(isHandFourOfAKind(h, hs)==false)
			&&(isHandTwoPair(h, hs)==false)&&(isHandThreeOfAKind(h, hs)==false)){
		kickers.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		isHandPair = true;
		hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
		hs.setHandStrength(eHandStrength.Pair);
		
		}
		else if((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteSuit() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteSuit())
			&&(isHandAcesAndEights(h, hs)==false)&&(isHandFourOfAKind(h, hs)==false)
			&&(isHandTwoPair(h, hs)==false)&&(isHandThreeOfAKind(h, hs)==false)){
		kickers.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		isHandPair = true;
		hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
		hs.setHandStrength(eHandStrength.Pair);
		}
		else if((h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteSuit() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteSuit())
			&&(isHandAcesAndEights(h, hs)==false)&&(isHandFourOfAKind(h, hs)==false)
			&&(isHandTwoPair(h, hs)==false)&&(isHandThreeOfAKind(h, hs)==false)){
		kickers.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		isHandPair = true;
		hs.setHiHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		hs.setHandStrength(eHandStrength.Pair);
		}
		return isHandPair;
	}

	public static boolean isHandHighCard(Hand h, HandScore hs) {
		boolean isHandHighCard = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((isHandAcesAndEights(h, hs) == false) && (isHandFourOfAKind(h, hs) == false)
				&& (isHandFullHouse(h, hs) == false) && (isHandPair(h, hs) == false) && (isHandTwoPair(h, hs))
				&& (isHandThreeOfAKind(h, hs) == false) && (isHandStraight(h, hs) == false)
				&& (isHandFlush(h, hs) == false) && (isHandFourOfAKind(h, hs) == false)
				&& (isHandStraightFlush(h, hs) == false) && (isHandRoyalFlush(h, hs) == false)) {
			isHandHighCard = true;
			kickers.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			hs.setHandStrength(eHandStrength.HighCard);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());

		}
		return isHandHighCard;
	}

	public static boolean isHandAcesAndEights(Hand h, HandScore hs) {
		boolean isAcesAndEights = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank()
				&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE)) {
			if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNo.FourthCard.getCardNo()).geteRank()
					&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.EIGHT)) {
				kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
				isAcesAndEights = true;
				hs.setHandStrength(eHandStrength.AcesAndEights);
				hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			}
			if (h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNo.FourthCard.getCardNo()).geteRank()
					&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.EIGHT)) {
				kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
				isAcesAndEights = true;
				hs.setHandStrength(eHandStrength.AcesAndEights);
				hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());

			} else {
				return isAcesAndEights;
			}

		}
		return isAcesAndEights;

	}

	public static boolean isHandFullHouse(Hand h, HandScore hs) {

		boolean isFullHouse = false;

		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		}

		return isFullHouse;

	}
}
