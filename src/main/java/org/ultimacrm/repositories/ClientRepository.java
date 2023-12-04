package org.ultimacrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ultimacrm.models.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    long deleteByNameAndEmail(String name, String email);

    long countByName(String name);
}
