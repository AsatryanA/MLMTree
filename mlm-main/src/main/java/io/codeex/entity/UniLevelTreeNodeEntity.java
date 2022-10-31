package io.codeex.entity;

import io.codeex.entity.relationship.ChildRelationship;
import io.codeex.entity.relationship.ParentRelationship;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.DynamicLabels;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Node(primaryLabel = "UniLevelTreeNode")
@Getter
@Setter
@NoArgsConstructor
public class UniLevelTreeNodeEntity extends BaseNodeEntity {

    @Relationship(type = "PARENT", direction = Relationship.Direction.OUTGOING)
    private ParentRelationship<UniLevelTreeNodeEntity> parent;

    @Relationship(type = "CHILD", direction = Relationship.Direction.OUTGOING)
    @DynamicLabels
    private List<ChildRelationship> childRelationships;

    public void addChild(UniLevelTreeNodeEntity uniLevelTreeNodeEntity) {
        var childRelationship = new ChildRelationship(uniLevelTreeNodeEntity);

        if (childRelationships.isEmpty()) {
            childRelationships = new ArrayList<>();
        }
        childRelationships.add(childRelationship);
    }

    public void addParent(UniLevelTreeNodeEntity uniLevelTreeNodeEntity) {
        var parentRelationship = new ParentRelationship<>(uniLevelTreeNodeEntity);
        setParent(parentRelationship);
    }

    public UniLevelTreeNodeEntity getParentNode() {
        if (parent != null) {
            return parent.getParent();
        }
        return null;
    }
}
