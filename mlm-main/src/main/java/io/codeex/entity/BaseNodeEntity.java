package io.codeex.entity;

import io.codeex.enums.TreeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Node(primaryLabel = "BaseNodeEntity")
@Getter
@Setter
@NoArgsConstructor
public class BaseNodeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Property("head")
    private Boolean head;

    @Property("business_id")
    private Long businessId;

    @Property("user_id")
    private Long userId;

    @Property("company_id")
    private Long companyId;

    @Property("tree_type")
    private TreeType treeType;

    @Property("disabled")
    private Boolean disabled;

    @Property("created_at")
    @CreatedDate
    private LocalTime createdAt;
}
