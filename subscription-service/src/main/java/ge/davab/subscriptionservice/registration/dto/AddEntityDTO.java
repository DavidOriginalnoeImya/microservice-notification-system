package ge.davab.subscriptionservice.registration.dto;

public class AddEntityDTO {

    private String entityName;

    private String entityCaption;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityCaption() {
        return entityCaption;
    }

    public void setEntityCaption(String entityCaption) {
        this.entityCaption = entityCaption;
    }
}
