package io.codeex.dto;

import io.codeex.enums.TreeType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NodeRequestDto {
    private Long id;

    private Long parentId;

    private Boolean head;

    private Long level;

    private Boolean disabled;

    @NotBlank(message = "Company Id is mandatory")
    private Long companyId;

    @NotBlank(message = "Business Id is mandatory")
    private Long businessId;

    @NotBlank(message = "User Id is mandatory")
    private Long userId;

    @NotBlank(message = "Tree Type is mandatory")
    private TreeType treeType;
}
