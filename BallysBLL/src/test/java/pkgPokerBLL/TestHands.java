package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {

	@Test
	public void TestRoyalFlush() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TEN, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.JACK, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.QUEEN, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.KING, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.ACE, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be Royal Flush
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.ACE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// no kickers.
		assertEquals(0, h.getHandScore().getKickers().size());

	}

	@Test
	public void TestStraightFlush() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SEVEN, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT, eSuit.CLUBS));
		h.EvaluateHand();

		// Hand
		assertEquals(eHandStrength.StraightFlush.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.EIGHT.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// no kickers.
		assertEquals(0, h.getHandScore().getKickers().size());

	}

	@Test
	public void TestFourOfAKind1() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be a full house
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(), h.getHandScore().getLoHand().getiRankNbr());

		// Number of kickers.
		assertEquals(1, h.getHandScore().getKickers().size());

		// Make sure 3 is first in list because highest kicker.
		assertEquals(eRank.THREE, h.getHandScore().getKickers().get(0).geteRank());

	}

	@Test
	public void TestFourOfAKind2() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be a full house
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(), h.getHandScore().getLoHand().getiRankNbr());

		// Full House has no kickers.
		assertEquals(1, h.getHandScore().getKickers().size());

		// Make sure 3 is first in list because highest kicker.
		assertEquals(eRank.THREE, h.getHandScore().getKickers().get(0).geteRank());

	}

	@Test
	public void TestFullHouse() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(), h.getHandScore().getLoHand().getiRankNbr());

		// Full House has no kickers.
		assertEquals(0, h.getHandScore().getKickers().size());

	}
	
	@Test
	public void TestAcesAndEights1() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT, eSuit.SPADES));
		h.EvaluateHand();

		// Hand
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.ACE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number kickers.
		assertEquals(1, h.getHandScore().getKickers().size());

		// Highest kicker.
		assertEquals(eRank.THREE, h.getHandScore().getKickers().get(0).geteRank());
	}
	
	@Test
	public void TestAcesAndEights2() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.SPADES));
		h.EvaluateHand();

		// Hand
		assertEquals(eHandStrength.AcesAndEights.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.ACE.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number kickers.
		assertEquals(1, h.getHandScore().getKickers().size());

		// Highest kicker.
		assertEquals(eRank.THREE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestFlush() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SEVEN, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX, eSuit.CLUBS));
		h.EvaluateHand();

		// Hand
		assertEquals(eHandStrength.Flush.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.EIGHT.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// no kickers.
		assertEquals(0, h.getHandScore().getKickers().size());

	}

	@Test
	public void TestStraight() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX, eSuit.SPADES));
		h.EvaluateHand();

		// Hand
		assertEquals(eHandStrength.Straight.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.SIX.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// no kickers.
		assertEquals(0, h.getHandScore().getKickers().size());

	}

	@Test
	public void TestThreeOfAKind1() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.EvaluateHand();

		// Hand
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number kickers.
		assertEquals(2, h.getHandScore().getKickers().size());

		// Highest kicker.
		assertEquals(eRank.THREE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestThreeOfAKind2() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number kickers.
		assertEquals(2, h.getHandScore().getKickers().size());

		// Make sure 3 is first kicker in list, because highest kicker.
		assertEquals(eRank.THREE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestThreeOfAKind3() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be a three of a kind
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// number kickers.
		assertEquals(2, h.getHandScore().getKickers().size());

		// Make sure 3 is first kicker in list, because highest kicker.
		assertEquals(eRank.THREE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestTwoPair1() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number of kickers.
		assertEquals(1, h.getHandScore().getKickers().size());

		// first kicker in list, because highest kicker.
		assertEquals(eRank.TWO, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestTwoPair2() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be two pair
		assertEquals(eHandStrength.TwoPair.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number of kickers.
		assertEquals(1, h.getHandScore().getKickers().size());

		// first kicker in list, because highest kicker.
		assertEquals(eRank.TWO, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestOnePair1() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be one pair
		assertEquals(eHandStrength.Pair.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number of kickers.
		assertEquals(3, h.getHandScore().getKickers().size());

		// first kicker in list, because highest kicker.
		assertEquals(eRank.FIVE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestOnePair2() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be one pair
		assertEquals(eHandStrength.Pair.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Num of kickers.
		assertEquals(3, h.getHandScore().getKickers().size());

		// first kicker in list, because highest kicker.
		assertEquals(eRank.FIVE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestOnePair3() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be one pair
		assertEquals(eHandStrength.Pair.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number of kickers.
		assertEquals(3, h.getHandScore().getKickers().size());

		// first kicker in list, because highest kicker.
		assertEquals(eRank.FIVE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestOnePair4() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.EvaluateHand();

		// Hand better be one pair
		assertEquals(eHandStrength.Pair.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number of kickers.
		assertEquals(3, h.getHandScore().getKickers().size());

		// first kicker in list, because highest kicker.
		assertEquals(eRank.FIVE, h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestHighCard() {

		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TEN, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.EIGHT, eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		h.EvaluateHand();

		// Hand
		assertEquals(eHandStrength.HighCard.getHandStrength(), 
				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand
		assertEquals(eRank.TEN.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// Number of kickers.
		assertEquals(4, h.getHandScore().getKickers().size());

		// first kicker in list, because highest kicker.
		assertEquals(eRank.EIGHT, h.getHandScore().getKickers().get(0).geteRank());
	}

}
