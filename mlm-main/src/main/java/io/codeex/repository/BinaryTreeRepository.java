package io.codeex.repository;

import io.codeex.entity.BinaryTreeNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;


@Repository
public interface BinaryTreeRepository extends Neo4jRepository<BinaryTreeNodeEntity, Long> {

    BinaryTreeNodeEntity findFirstById(@NonNull  Long id);

    BinaryTreeNodeEntity findFirstByUserId(@NonNull Long userId);

    BinaryTreeNodeEntity findFirstByBusinessIdAndUserId(@NonNull Long businessId, @NonNull Long userId);

    BinaryTreeNodeEntity findFirstByBusinessIdAndHeadTrue(@NonNull Long businessId);

    boolean existsByBusinessId(@NonNull Long businessId);

    boolean existsByBusinessIdAndHeadTrue(@NonNull Long businessId);

    boolean existsByBusinessIdAndUserId(@NonNull Long businessId, @NonNull Long userId);


}
