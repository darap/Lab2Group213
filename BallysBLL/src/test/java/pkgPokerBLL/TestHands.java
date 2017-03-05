package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {

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
		assertEquals(eHandStrength.FullHouse.getHandStrength(), h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(), h.getHandScore().getHiHand().getiRankNbr());

		// LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(), h.getHandScore().getLoHand().getiRankNbr());

		// Full House has no kickers.
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

		assertEquals(eRank.FOUR.getiRankNbr(),

				h.getHandScore().getHiHand().getiRankNbr());

		// LO hand better be 'Three'

		assertEquals(eRank.THREE.getiRankNbr(),

				h.getHandScore().getLoHand().getiRankNbr());

		// Full House has no kickers.

		assertEquals(1, h.getHandScore().getKickers().size());

		// Make sure 3 is first in list because highest kicker.

		assertEquals(eRank.THREE,

				h.getHandScore().getKickers().get(0).geteRank());

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

		assertEquals(eRank.FOUR.getiRankNbr(),

				h.getHandScore().getHiHand().getiRankNbr());

		// LO hand better be 'Three'

		assertEquals(eRank.THREE.getiRankNbr(),

				h.getHandScore().getLoHand().getiRankNbr());

		// Full House has no kickers.

		assertEquals(1, h.getHandScore().getKickers().size());

		// Make sure 3 is first in list because highest kicker.

		assertEquals(eRank.THREE,

				h.getHandScore().getKickers().get(0).geteRank());

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

		// Hand better be a full house

		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),

				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'

		assertEquals(eRank.FOUR.getiRankNbr(),

				h.getHandScore().getHiHand().getiRankNbr());

		// Full House has no kickers.

		assertEquals(2, h.getHandScore().getKickers().size());

		// Make sure 3 is first in list because highest kicker.

		assertEquals(eRank.THREE,

				h.getHandScore().getKickers().get(0).geteRank());

	}

	@Test

	public void TestThreeOfAKind2() {

		Hand h = new Hand();

		h.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));

		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));

		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));

		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));

		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));

		h.EvaluateHand();

		// Hand better be a full house

		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),

				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'

		assertEquals(eRank.FOUR.getiRankNbr(),

				h.getHandScore().getHiHand().getiRankNbr());

		// Full House has no kickers.

		assertEquals(2, h.getHandScore().getKickers().size());

		// Make sure 3 is first kicker in list, because highest kicker.

		assertEquals(eRank.THREE,

				h.getHandScore().getKickers().get(0).geteRank());

	}

	@Test

	public void TestThreeOfAKind3() {

		Hand h = new Hand();

		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));

		h.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));

		h.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));

		h.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));

		h.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));

		h.EvaluateHand();

		// Hand better be a full house

		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),

				h.getHandScore().getHandStrength().getHandStrength());

		// HI hand better be 'Four'

		assertEquals(eRank.FOUR.getiRankNbr(),

				h.getHandScore().getHiHand().getiRankNbr());

		// Full House has no kickers.

		assertEquals(2, h.getHandScore().getKickers().size());

		// Make sure 3 is first kicker in list, because highest kicker.

		assertEquals(eRank.THREE,

				h.getHandScore().getKickers().get(0).geteRank());

	}
}
