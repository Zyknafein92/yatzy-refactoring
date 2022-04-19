import java.util.*;
import java.util.stream.Collectors;

public class Yatzy {

    protected int[] dices; //array with the roll of all dices.
    protected int betNumber; // number who represent the bet of Player for Ones,Twos,Threes,Fours,Fives,Sixes

    public Yatzy() {
        this.dices = new int[5];
    }

    public Yatzy(int[] dices, int betNumber) {
        this.dices = new int[5];
        this.betNumber = betNumber;
    }

    /**
     * This method do the sum of the five dices in they array.
     * @param dices array with the roll of all dices
     * @return int of the sum of array dices
     */
    public static int chance(int[] dices) {
        return Arrays.stream(dices).sum();
    }

    /**
     * This method check with a HashSet collection if all values are unique.
     * If they are, it will return 50, if not 0
     * @param dices array with the roll of all dices
     * @return int with value 50 if values are unique, or 0 if not.
     */
    public static int yatzy(int[] dices) {
        HashSet<Integer> diceResult = new HashSet<>();
        Arrays.stream(dices).forEach(diceResult::add);
        return diceResult.size() == 1 ? 50 : 0;
    }

    /**
     * This method replace Ones,Twos,Threes,Fours,Fives,Sixes
     * Player bet on a number, and the method will use a Map to get all numbers
     * and how many times they are present.
     * If the map contains the key, the method will return map.value * betNumber,
     * If not, it return 0.
     * @param dices array with the roll of all dices
     * @param betNumber int number who represent the bet of Player for Ones,Twos,Threes,Fours,Fives,Sixes
     * @return int with sum of betNumber * map.value or 0
     */
    public static int betOnNumber(int[] dices, int betNumber) {
        HashMap<Integer, Integer> diceMap = convertArrayToMap(dices);
        return diceMap.containsKey(betNumber) ? diceMap.get(betNumber) * betNumber : 0;
    }

    /**
     * This method will check if the array of dices contains a pair and will take the highest
     * if they are multiples by using a map for count the number of dice with the same value.
     * After the map, we use a filter to get all values superior at 2 for get all pairs and
     * we use Collections.max to get the highest pair value.
     * Finally, we multiply the value of the key by two to get the score.
     * @param dices array with the roll of all dices
     * @return int value of the hightest pair number multiply by 2 or 0.
     */
    public static int onePair(int[] dices) {
        HashMap<Integer, Integer> diceMap = convertArrayToMap(dices);
        List<Integer> filterMap = diceMap.keySet().stream().filter((v) -> diceMap.get(v) >= 2).collect(Collectors.toList());
        if (filterMap.isEmpty()) {
            return 0;
        } else {
            Integer key = Collections.max(filterMap);
            return key * 2;
        }
    }

    /**
     * This method will check if the array of dices contains two pairs.
     * We use a map for count the number of dice with the same value.
     * After the map, we use a filter to get all pairs and get a list of the pairs.
     * If there is two pairs, we will use the list obtain by the filter and reduce it
     * to the sum of the key values multiply by 2.
     * If not, we return 0.
     * @param dices array with the roll of all dices
     * @return int value of all pairs multiply by 2 or 0.
     */
    public static int twoPair(int[] dices) {
        HashMap<Integer, Integer> diceMap = convertArrayToMap(dices);
        List<Integer> filterMap = diceMap.keySet().stream().filter((v) -> diceMap.get(v) >= 2).collect(Collectors.toList());
        if(filterMap.size() == 2) {
            return filterMap.stream().reduce(0, Integer::sum) * 2;
        } else {
            return 0;
        }
    }

    /**
     * This method will check if there is three dices with the same value in the array.
     * We use a map for count the number of dice with the same value.
     * After we filter the map to get the key who his present 3 times or if not, we return 0.
     * We return the value of the filter multiply by 3.
     * @param dices array with the roll of all dices.
     * @return int value of the key multiply by 3.
     */
    public static int threeOfAKind(int[] dices) {
        HashMap<Integer, Integer> diceMap = convertArrayToMap(dices);
        Integer key = diceMap.keySet().stream().filter(k -> diceMap.get(k) >= 3).findFirst().orElse(0);
        return key * 3;
    }

    /**
     * This method will check if there is three dices with the same value in the array.
     * We use a map for count the number of dice with the same value.
     * After we filter the map to get the key who his present 4 times or if not, we return 0.
     * We return the value of the filter multiply by 4.
     * @param dices array with the roll of all dices.
     * @return int value of the key multiply by 4.
     */
    public static int fourOfAKind(int[] dices) {
        HashMap<Integer, Integer> diceMap = convertArrayToMap(dices);
        Integer key = diceMap.keySet().stream().filter(k -> diceMap.get(k) >= 4).findFirst().orElse(0);
        return key * 4;
    }

    /**
     * This method will check if there is a small straight or full straight and return the score value.
     * We use a Set for check if all dices values are uniques.
     * If they are, we check if 1 or 6 is present, if 1 it's a small straight, if 6 it's a large straight
     * and we return the score for the straight value.
     * If all values are not unique, we return 0
     * @param dices array with the roll of all dices.
     * @return int value of the straight score
     */
    public static int straight(int[] dices) {
        Set<Integer> diceSet = new HashSet<>();
        Arrays.stream(dices).forEach(diceSet::add);
        if(diceSet.size() != 5) {
            return 0;
        }
        return diceSet.contains(1) ? 15 : 20;
    }

    /**
     * This method will check if dices number are three of a kind and two of a king.
     * We use a map for count the number of dice with the same value.
     * We check is the map size is 2 and if the numbers present in the map are superior to 1
     * If they are we return the sum of the values
     * If not, we return 0
     * @param dices array with the roll of all dices.
     * @return int value of array dices sum
     */
    public static int fullHouse(int[] dices) {
        HashMap<Integer, Integer> diceMap = convertArrayToMap(dices);
        if(diceMap.size() != 2 || diceMap.containsValue(1)) {
            return 0;
        } else {
            return Arrays.stream(dices).sum();
        }
    }

    /**
     * This method will convert the array dices to a map.
     * @param dices array with the roll of all dices.
     * @return map with for key the number, and for the value of times it is present
     */
    private static HashMap<Integer, Integer> convertArrayToMap(int[] dices) {
        HashMap<Integer, Integer> diceMap = new HashMap<>();
        Arrays.stream(dices).forEach(d -> {
            if (diceMap.containsKey(d)) {
                diceMap.put(d, diceMap.get(d) + 1);
            }
            diceMap.putIfAbsent(d, 1);
        });
        return diceMap;
    }

}



