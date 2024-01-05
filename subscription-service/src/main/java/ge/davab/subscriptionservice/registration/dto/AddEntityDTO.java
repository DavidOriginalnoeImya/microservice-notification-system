package ge.davab.subscriptionservice.registration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AddEntityDTO<Entity> {

    private String entityName;

    private String entityCaption;

    public abstract Entity toEntity();
}
