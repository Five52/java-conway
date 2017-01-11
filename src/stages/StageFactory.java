package stages;

public class StageFactory {
    public static BabyStage babyStage = new BabyStage();
    public static TeenageStage teenageStage = new TeenageStage();
    public static AdultStage adultStage = new AdultStage();

    public static BabyStage getBabyStage() {
        return babyStage;
    }
    public static TeenageStage getTeenageStage() {
        return teenageStage;
    }
    public static AdultStage getAdultStage() {
        return adultStage;
    }
}
