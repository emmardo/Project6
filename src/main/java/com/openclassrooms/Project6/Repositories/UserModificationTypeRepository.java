package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.UserModificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModificationTypeRepository extends JpaRepository<UserModificationType, Integer> {

    /*UserModificationType findUserModificationTypeById(int id);

    UserModificationType findUserModificationTypeByUserModificationType(String userModificationType);*/
}
