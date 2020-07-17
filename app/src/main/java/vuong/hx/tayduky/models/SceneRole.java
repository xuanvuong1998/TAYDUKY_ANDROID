package vuong.hx.tayduky.models;

import java.io.Serializable;

public class SceneRole implements Serializable {
    private long id;
    private long challengeId;
    private long characterId;
    private String assignedActor;
    private String description;
    private String participatedDate;
    private String finishedDate;
    private Challenge challenge;
    private Character character;

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(long challengeId) {
        this.challengeId = challengeId;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
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

    public SceneRole(long id, long challengeId, long characterId, String assignedActor, String description, String participatedDate, String finishedDate) {
        this.id = id;
        this.challengeId = challengeId;
        this.characterId = characterId;
        this.assignedActor = assignedActor;
        this.description = description;
        this.participatedDate = participatedDate;
        this.finishedDate = finishedDate;
    }
}
