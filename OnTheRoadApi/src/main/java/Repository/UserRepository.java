package Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> getUserByUsername(String username);

}