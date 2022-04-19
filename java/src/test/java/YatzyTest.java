import org.junit.*;
import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void testChanceGivenValueReturnSum() {
        assertEquals(15, Yatzy.chance(new int[]{2, 3, 4, 5, 1}));
        assertEquals(16, Yatzy.chance(new int[]{3,3,4,5,1}));
    }

    @Test
    public void testYatzyGivenInvalidValueReturnZero() {
        assertEquals(0, Yatzy.yatzy(new int[]{2, 3, 4, 5, 1}));
        assertEquals(0, Yatzy.yatzy(new int[]{1, 1, 4, 5, 1}));
    }

    @Test
    public void testYatzyGivenValueReturn50() {
        assertEquals(50, Yatzy.yatzy(new int[]{2, 2, 2, 2, 2}));
        assertEquals(50, Yatzy.yatzy(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testBetOnNumberGivenInvalidValueReturnZero () {
        assertEquals(0, Yatzy.betOnNumber(new int[]{2, 2, 2, 2, 2},3));
        assertEquals(0, Yatzy.betOnNumber(new int[]{1, 3, 2, 1, 5},6));
    }

    @Test
    public void testBetOnNumberGivenValueReturnSumBetNumberXDiceNumbersWithBetValue () {
        assertEquals(10, Yatzy.betOnNumber(new int[]{2, 2, 2, 2, 2},2));
        assertEquals(2, Yatzy.betOnNumber(new int[]{1, 3, 2, 1, 5},1));
    }

    @Test
    public void testOnePaireGivenInvalidValueReturnZero () {
        assertEquals(0, Yatzy.onePair(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, Yatzy.onePair(new int[]{3, 2, 4, 5, 6}));
    }

    @Test
    public void testOnePaireGivenValueReturnSumOfPairValue () {
        assertEquals(8, Yatzy.onePair(new int[]{1, 2, 6, 4, 4}));
        assertEquals(4, Yatzy.onePair(new int[]{3, 2, 2, 5, 6}));
    }

    @Test
    public void testOnePaireGivenValueWithTwoPairReturnHighestSumOfPairValue () {
        assertEquals(8, Yatzy.onePair(new int[]{1, 2, 2, 4, 4}));
        assertEquals(10, Yatzy.onePair(new int[]{3, 2, 2, 5, 5}));
    }

    @Test
    public void testTwoPaireGivenInvalidValueReturnZero () {
        assertEquals(0, Yatzy.twoPair(new int[]{1, 2, 1, 5, 4}));
        assertEquals(0, Yatzy.twoPair(new int[]{3, 2, 1, 5, 5}));
    }

    @Test
    public void testTwoPaireGivenValueReturnSumOfTwoPairValues () {
        assertEquals(6, Yatzy.twoPair(new int[]{1, 2, 2, 5, 1}));
        assertEquals(16, Yatzy.twoPair(new int[]{3, 3, 1, 5, 5}));
    }

    @Test
    public void testThreeOfAKingGivenInvalidValueReturnZero () {
        assertEquals(0, Yatzy.threeOfAKind(new int[]{1, 2, 2, 5, 1}));
        assertEquals(0, Yatzy.threeOfAKind(new int[]{3, 3, 1, 5, 5}));
        assertEquals(0, Yatzy.threeOfAKind(new int[]{1, 2, 3, 5, 6}));
    }

    @Test
    public void testThreeOfAKingGivenInvalidValueReturnSumOfValues () {
        assertEquals(3, Yatzy.threeOfAKind(new int[]{1, 1, 2, 5, 1}));
        assertEquals(15, Yatzy.threeOfAKind(new int[]{3, 5, 1, 5, 5}));
    }

    @Test
    public void testFourOfAKingGivenInvalidValueReturnZero () {
        assertEquals(0, Yatzy.fourOfAKind(new int[]{1, 2, 2, 5, 1}));
        assertEquals(0, Yatzy.fourOfAKind(new int[]{3, 3, 1, 5, 5}));
        assertEquals(0, Yatzy.fourOfAKind(new int[]{1, 2, 3, 5, 6}));
    }

    @Test
    public void testFourOfAKingGivenInvalidValueReturnSumOfValues () {
        assertEquals(4, Yatzy.fourOfAKind(new int[]{1, 1, 1, 5, 1}));
        assertEquals(20, Yatzy.fourOfAKind(new int[]{5, 5, 1, 5, 5}));
    }

    @Test
    public void testStraightGivenInvalidValuesReturnZero() {
        assertEquals(0, Yatzy.straight(new int[]{1,2,2,5,5}));
        assertEquals(0, Yatzy.straight(new int[]{1,2,1,4,5}));
    }

    @Test
    public void testStraightGivenSmallStraightReturn15() {
        assertEquals(15, Yatzy.straight(new int[]{1,2,3,4,5}));
        assertEquals(15, Yatzy.straight(new int[]{2,3,4,5,1}));
    }

    @Test
    public void testStraightGivenLargeStraightReturn20() {
        assertEquals(20, Yatzy.straight(new int[]{6,2,3,4,5}));
        assertEquals(20, Yatzy.straight(new int[]{2,3,4,5,6}));
    }

    @Test
    public void testFullHouseGivenInvalidValueReturnSumOfValues() {
        assertEquals(0, Yatzy.fullHouse(new int[]{2,3,4,5,6}));
        assertEquals(0, Yatzy.fullHouse(new int[]{3,3,4,5,6}));
        assertEquals(0, Yatzy.fullHouse(new int[]{3,3,5,5,6}));
    }

    @Test
    public void testFullHouseGivenTwoDifferentDicesValueWithOneUniqueNumberReturnZero() {
        assertEquals(0, Yatzy.fullHouse(new int[]{1,1,1,1,5}));
    }

    @Test
    public void testFullHouseGivenValidValueReturnSumOfValues() {
        assertEquals(18, Yatzy.fullHouse(new int[]{6,2,2,2,6}));
        assertEquals(14, Yatzy.fullHouse(new int[]{2,2,2,4,4}));
        assertEquals(14, Yatzy.fullHouse(new int[]{2,4,2,2,4}));
    }
}
