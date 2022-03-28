package platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import platform.model.Code;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code,String> {


    @Query("SELECT s FROM Code s WHERE s.code = ?1")
    Optional<Code> findCode(String code);

    @Query("SELECT s FROM Code s WHERE s.id = ?1")
    Optional<Code> findById(UUID id);
}
