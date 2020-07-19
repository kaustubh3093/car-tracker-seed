package io.egen.repository;

import org.springframework.data.repository.CrudRepository;

import io.egen.entity.Alert;

public interface AlertRepository extends CrudRepository<Alert, String>{

}
