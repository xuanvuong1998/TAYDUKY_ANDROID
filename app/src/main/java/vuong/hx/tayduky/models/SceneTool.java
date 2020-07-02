package vuong.hx.tayduky.models;

public class SceneTool {
    private int challengeId;
    private int toolId;

    @Override
    public String toString() {
        return "SceneTool{" +
                "challengeId=" + challengeId +
                ", toolId=" + toolId +
                ", quantity=" + quantity +
                '}';
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SceneTool() {
    }

    public SceneTool(int challengeId, int toolId, int quantity) {
        this.challengeId = challengeId;
        this.toolId = toolId;
        this.quantity = quantity;
    }

    private int quantity;
}
