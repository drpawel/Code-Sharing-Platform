package platform;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, UUID> {
    @Query("SELECT c FROM Code c WHERE c.time = ?1 " +
            "AND c.views = ?1 ORDER BY c.date DESC")
    Page<Code> findLatest(long time, long views, PageRequest pageable);

    //List<Code> findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(long time, long views);
}
