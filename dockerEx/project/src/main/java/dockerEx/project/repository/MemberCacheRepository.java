package dockerEx.project.repository;

import org.springframework.data.repository.CrudRepository;
import dockerEx.project.entity.MemberCache;


public interface MemberCacheRepository extends CrudRepository<MemberCache, String> {
    // email로 캐싱
}
