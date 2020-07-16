package vuong.hx.tayduky.models;

import java.io.Serializable;

public class SceneRole implements Serializable {
    private int id;
    private int challengeId;
    private int characterId;
    private String assignedActor;
    private String description;
    private String participatedDate;
    private String finishedDate;



    @Override
    public String toString() {
        return "SceneRole{" +
                "id=" + id +
                ", challengeId=" + challengeId +
                ", characterId=" + characterId +
                ", assignedActor='" + assignedActor + '\'' +
                ", description='" + description + '\'' +
                ", participatedDate='" + participatedDate + '\'' +
                ", finishedDate='" + finishedDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getAssignedActor() {
        return assignedActor;
    }

    public void setAssignedActor(String assignedActor) {
        this.assignedActor = assignedActor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public SceneRole() {
    }

    public SceneRole(int id, int challengeId, int characterId, String assignedActor, String description, String participatedDate, String finishedDate) {
        this.id = id;
        this.challengeId = challengeId;
        this.characterId = characterId;
        this.assignedActor = assignedActor;
        this.description = description;
        this.participatedDate = participatedDate;
        this.finishedDate = finishedDate;
    }
}
