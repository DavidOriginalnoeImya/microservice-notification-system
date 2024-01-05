package ge.davab.subscriptionservice.registration.dto;

import ge.davab.subscriptionservice.registration.data.domainapp.DomainApp;

public class AddServiceDTO extends AddEntityDTO<DomainApp> {

    @Override
    public DomainApp toEntity() {
        DomainApp domainApp = new DomainApp();
        domainApp.setName(getEntityName());
        domainApp.setCaption(getEntityCaption());
        return domainApp;
    }
}
