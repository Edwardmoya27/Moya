package edu.unc.moya.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.unc.moya.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
