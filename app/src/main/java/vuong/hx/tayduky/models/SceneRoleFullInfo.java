package vuong.hx.tayduky.models;

import java.io.Serializable;
public class SceneRoleFullInfo implements Serializable {
    private int id;
    private Character character;
    private Actor assignedActor;
    private String desc;
    private String participatedDate;
    private String finishedDate;
    private Challenge challenge;

    public Challenge getChallenge() {
        return challenge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public SceneRoleFullInfo(int id, Character character, Actor assignedActor, String desc, String participatedDate, String finishedDate, Challenge challenge) {
        this.id = id;
        this.character = character;
        this.assignedActor = assignedActor;
        this.desc = desc;
        this.participatedDate = participatedDate;
        this.finishedDate = finishedDate;
        this.challenge = challenge;
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
