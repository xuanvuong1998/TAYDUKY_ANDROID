package vuong.hx.tayduky.models;

import java.io.Serializable;

public class SceneToolFullInfo implements Serializable {
    private Challenge challenge;
    private Tool tool;
    private long quantity;

    public Challenge getChallenge() {
        return challenge;
    }

    public SceneToolFullInfo() {
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public SceneToolFullInfo(Challenge challenge, Tool tool, long quantity) {
        this.challenge = challenge;
        this.tool = tool;
        this.quantity = quantity;
    }
}
