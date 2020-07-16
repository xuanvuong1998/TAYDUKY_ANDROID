package vuong.hx.tayduky.models;

import java.io.Serializable;

public class SceneTool implements Serializable {
    private int challengeId;
    private int toolId;
    private int quantity;
    private Challenge challenge;
    private Tool tool;

    @Override
    public String toString() {
        return "SceneTool{" +
                "challengeId=" + challengeId +
                ", toolId=" + toolId +
                ", quantity=" + quantity +
                '}';
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public SceneTool(int challengeId, int toolId, int quantity, Challenge challenge, Tool tool) {
        this.challengeId = challengeId;
        this.toolId = toolId;
        this.quantity = quantity;
        this.challenge = challenge;
        this.tool = tool;
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


}
