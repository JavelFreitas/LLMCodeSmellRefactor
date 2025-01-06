package org.example.studycards;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LeitnerSystemTest {
    static LeitnerSystem leitnerSystem = null;
    static CardManager manager = null;
    static Integer card1Id = null;
    static Integer card2Id = null;

    @BeforeAll
    static void setUp() {
        leitnerSystem = new LeitnerSystem("LeitnerSystemTest");
        manager = CardManager.getCardManager();
        card1Id = manager.addCard("Question 1", "Answer 1");
        card2Id = manager.addCard("Question 2", "Answer 2");

    }

    @BeforeEach
    void setUpEach() {
        leitnerSystem.clearBoxes();
    }

    @Test
    @DisplayName("Get Random Card No Cards Test")
    @Order(1)
    void testGetRandomCardNoCards() {
        Box box = new Box();
        String response = leitnerSystem.getRandomCard(List.of(box));
        assertNotNull(response);
        assertEquals("No card found", response);
    }

    @Test
    @DisplayName("Get Random Card Test")
    @Order(2)
    void getRandompackage org.example.studycards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitnerSystem extends StudyMethod{
    List<Box> boxes = null;
    public LeitnerSystem(String methodName) {
        super(methodName);
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString(){
        StringBuilder response = new StringBuilder();
        int index = 0;
        for(Box box : boxes){
            response.append("Box ").append(index).append(": ").append(box.toString()).append("\n");
            index++;
        }
        return response.toString();
    }

    public void clearBoxes(){
        boxes.clear();
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public String getRandomCard(List<Box> otherBoxes){
        if(otherBoxes == null){
            return null;
        }
        if(otherBoxes.isEmpty()){
            return null;
        }
        Box allBoxes = new Box();
        for(Box box : otherBoxes){
            allBoxes.addCards(box.getCards());
        }
        Integer randomCard = allBoxes.getRandomCard();
        if(randomCard == null){
            return "No card found";
        }
        CardManager manager = CardManager.getCardManager();
        Card card = manager.getCard(randomCard);
        String response = "["+ randomCard + "] ";
        response += "The random question was: " + card.getQuestion() + " | ";
        response += "The answer is: " + card.getAnswer();
        return  response;
    }

    public void addCardToBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).addCard(id);
    }

    public void removeCardFromBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).removeCard(id);
    }

    public Card takeCardFromBox(Integer boxId){
        Integer cardId = boxes.get(boxId).getRandomCard();
        return this.cardManager.getCard(cardId);
    }

    public void boxIdValidation(Integer boxId) throws Exception {
        if(boxId == null || boxId > (boxes.size()-1) || boxId <= 0){
            throw new Exception("Invalid box ID");
        }
    }

    public void upgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);

        Box refBox = boxes.get(boxId);
        if(refBox.hasCard(cardId)){
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        boxes.get(Math.min(boxId + 1, 4)).addCard(cardId);
    }

    public void downgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);

        Box refBox = boxes.get(boxId);
        if(refBox.hasCard(cardId)){
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        boxes.get(Math.max(boxId - 1, 0)).addCard(cardId);
    }

}
Card() {
        Box box = new Box();
        Box box2 = new Box();
        box.addCards(List.of(card1Id));
        box2.addCards(List.of(card2Id));
        String randomCard = leitnerSystem.getRandomCard(Arrays.asList(box, box2));
        assertNotNull(randomCard);
        if(randomCard.contains("Question 1")) {
            assertTrue(randomCard.contains("Answer 1"));
        } else {
            assertTrue(randomCard.contains("Answer 2"));
        }
    }
}