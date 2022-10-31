package io.codeex.entity.relationship;

import io.codeex.entity.BinaryTreeNodeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
public class LeftChildRelationship {
    @RelationshipId
    @GeneratedValue
    private Long id;

    @TargetNode
    private BinaryTreeNodeEntity leftChild;

    public LeftChildRelationship(BinaryTreeNodeEntity leftChild) {
        this.leftChild = leftChild;
    }

}
