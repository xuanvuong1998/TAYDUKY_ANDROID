package vuong.hx.tayduky.models;

import java.io.Serializable;

public class SceneTool implements Serializable {
    private long challengeId;
    private long toolId;
    private long quantity;
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

    public SceneTool(long challengeId, long toolId, long quantity, Challenge challenge, Tool tool) {
        this.challengeId = challengeId;
        this.toolId = toolId;
        this.quantity = quantity;
        this.challenge = challenge;
        this.tool = tool;
    }

    public long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(long challengeId) {
        this.challengeId = challengeId;
    }

    public long getToolId() {
        return toolId;
    }

    public void setToolId(long toolId) {
        this.toolId = toolId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public SceneTool() {
    }

    public SceneTool(long challengeId, long toolId, long quantity) {
        this.challengeId = challengeId;
        this.toolId = toolId;
        this.quantity = quantity;
    }


}
