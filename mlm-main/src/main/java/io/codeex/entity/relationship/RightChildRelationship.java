package io.codeex.entity.relationship;

import io.codeex.entity.BinaryTreeNodeEntity;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
public class RightChildRelationship {
    @RelationshipId
    @GeneratedValue
    private Long id;

    @TargetNode
    private BinaryTreeNodeEntity rightChild;

    public RightChildRelationship(BinaryTreeNodeEntity rightChild) {
        this.rightChild = rightChild;
    }

}
