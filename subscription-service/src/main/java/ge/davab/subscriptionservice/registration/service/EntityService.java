package ge.davab.subscriptionservice.registration.service;

import ge.davab.subscriptionservice.registration.dto.AddEntityDTO;

public interface EntityService<AddDTOType extends AddEntityDTO> {

    void add(AddDTOType entity);
}
