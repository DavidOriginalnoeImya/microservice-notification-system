package edu.home.registrationservice.dto.kafka;

public class AddEntityMessageDTO {

    private EntityType entityType;

    private String caption;

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
