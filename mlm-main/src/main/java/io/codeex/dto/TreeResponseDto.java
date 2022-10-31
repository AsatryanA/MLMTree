package io.codeex.dto;

import io.codeex.enums.TreeType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreeResponseDto implements Serializable {
    private Boolean head;
    private TreeType treeType;
    private Long companyId;
    private Long userId;
    private Long businessId;
    private List<TreeResponseDto> childList;

    public void addChild(TreeResponseDto child) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        childList.add(child);
    }
}
