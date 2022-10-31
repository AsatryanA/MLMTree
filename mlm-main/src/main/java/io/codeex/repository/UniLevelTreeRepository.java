package io.codeex.repository;

import io.codeex.entity.UniLevelTreeNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UniLevelTreeRepository extends Neo4jRepository<UniLevelTreeNodeEntity, Long> {

    UniLevelTreeNodeEntity findFirstById(@NonNull Long id);

    UniLevelTreeNodeEntity findFirstByUserId(@NonNull Long userId);

    UniLevelTreeNodeEntity findFirstByBusinessIdAndUserId(@NonNull Long businessId, @NonNull Long userId);

    UniLevelTreeNodeEntity findFirstByBusinessIdAndHeadTrue(@NonNull Long businessId);

    boolean existsByBusinessId(@NonNull Long businessId);

    boolean existsByBusinessIdAndHeadTrue(@NonNull Long businessId);

    boolean existsByBusinessIdAndUserId(@NonNull Long businessId,@NonNull Long userId);
}
