package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.dto.AddEntityDTO;

public interface EntityService<AddDTOType extends AddEntityDTO> {

    void add(AddDTOType entity);
}
