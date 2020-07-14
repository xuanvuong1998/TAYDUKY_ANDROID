package vuong.hx.tayduky.models;

import java.util.List;

public class ChallengeCreateModel {
    private Challenge challenge;
    private List<SceneRole> roles;
    private List<SceneTool> tools;

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public List<SceneRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SceneRole> roles) {
        this.roles = roles;
    }

    public List<SceneTool> getTools() {
        return tools;
    }

    public void setTools(List<SceneTool> tools) {
        this.tools = tools;
    }

    public ChallengeCreateModel() {
    }

    public ChallengeCreateModel(Challenge challenge, List<SceneRole> roles, List<SceneTool> tools) {
        this.challenge = challenge;
        this.roles = roles;
        this.tools = tools;
    }
}
