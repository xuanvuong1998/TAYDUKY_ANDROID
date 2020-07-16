package vuong.hx.tayduky.models;

import java.io.Serializable;

public class SceneRoleFullInfo implements Serializable {
    private Character character;
    private Actor assignedActor;
    private String desc;
    private String participatedDate;
    private String finishedDate;
    private Challenge challenge;

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public SceneRoleFullInfo(Character character, Actor assignedActor, String desc, String participatedDate, String finishedDate) {
        this.character = character;
        this.assignedActor = assignedActor;
        this.desc = desc;
        this.participatedDate = participatedDate;
        this.finishedDate = finishedDate;
    }

    @Override
    public String toString() {
        return "SceneRoleFullInfo{" +
                "character=" + character +
                ", assignedActor=" + assignedActor +
                ", desc='" + desc + '\'' +
                ", participatedDate='" + participatedDate + '\'' +
                ", finishedDate='" + finishedDate + '\'' +
                '}';
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Actor getAssignedActor() {
        return assignedActor;
    }

    public void setAssignedActor(Actor assignedActor) {
        this.assignedActor = assignedActor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParticipatedDate() {
        return participatedDate;
    }

    public void setParticipatedDate(String participatedDate) {
        this.participatedDate = participatedDate;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public SceneRoleFullInfo() {
    }
}
