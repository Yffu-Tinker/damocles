package cc.tinker.repository;

import cc.tinker.entity.Leaved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavedRepository extends JpaRepository<Leaved, Integer> {

}