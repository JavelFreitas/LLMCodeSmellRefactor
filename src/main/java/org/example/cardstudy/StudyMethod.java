package org.example.cardstudy;

public abstract class StudyMethod {
    String methodName = "";
    CardManager cardManager = null;
    public StudyMethod(String methodName) {
        this.methodName = methodName;
        this.cardManager = CardManager.getCardManager();
    }
    abstract String getMethodName();
    abstract void setMethodName(String methodName);
}
