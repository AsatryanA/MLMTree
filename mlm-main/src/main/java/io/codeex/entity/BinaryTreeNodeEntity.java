package io.codeex.entity;

import io.codeex.entity.relationship.LeftChildRelationship;
import io.codeex.entity.relationship.ParentRelationship;
import io.codeex.entity.relationship.RightChildRelationship;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


@Node(primaryLabel = "BinaryTreeNode")
@Getter
@Setter
@NoArgsConstructor
public class BinaryTreeNodeEntity extends BaseNodeEntity {
    @Relationship(type = "LEFT", direction = Relationship.Direction.OUTGOING)
    private LeftChildRelationship leftChild;

    @Relationship(type = "RIGHT", direction = Relationship.Direction.OUTGOING)
    private RightChildRelationship rightChild;

    @Relationship(type = "PARENT", direction = Relationship.Direction.OUTGOING)
    private ParentRelationship<BinaryTreeNodeEntity> parent;


    public void addParent(BinaryTreeNodeEntity binaryTreeNodeEntity) {
        var parentRelationship = new ParentRelationship<>(binaryTreeNodeEntity);
        setParent(parentRelationship);
    }

    public void addLeft(BinaryTreeNodeEntity binaryTreeNodeEntity) {
        var left = new LeftChildRelationship(binaryTreeNodeEntity);
        setLeftChild(left);
    }

    public void addRight(BinaryTreeNodeEntity binaryTreeNodeEntity) {
        var right = new RightChildRelationship(binaryTreeNodeEntity);
        setRightChild(right);
    }

    public Boolean addChild(BinaryTreeNodeEntity binaryTreeNodeEntity) {
        if (leftChild == null) {
            addLeft(binaryTreeNodeEntity);
            return true;
        }

        if (rightChild == null) {
            addRight(binaryTreeNodeEntity);
            return true;
        }
        return false;
    }

    public BinaryTreeNodeEntity getParentNode() {
        if (parent != null) {
            return parent.getParent();
        }
        return null;
    }

    public BinaryTreeNodeEntity getLeftChildNode() {
        if (leftChild != null) {
            return leftChild.getLeftChild();
        }
        return null;
    }

    public BinaryTreeNodeEntity getRightChildNode() {
        if (rightChild != null) {
            return rightChild.getRightChild();
        }
        return null;
    }
}
