package se.experis.Task17.model;


public class Relationship {
    int personID;
    int person2ID;
    String relationType;

    public Relationship(int personID, int person2ID, String relationType) {
        this.personID = personID;
        this.person2ID = person2ID;
        this.relationType = relationType;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getPerson2ID() {
        return person2ID;
    }

    public void setPerson2ID(int person2ID) {
        this.person2ID = person2ID;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
}
