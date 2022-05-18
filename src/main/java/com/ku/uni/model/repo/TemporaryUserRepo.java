package com.ku.uni.model.repo;

import com.ku.uni.model.entity.TemporaryUser;
import org.springframework.data.repository.CrudRepository;

public interface TemporaryUserRepo extends CrudRepository<TemporaryUser, String> {

}
