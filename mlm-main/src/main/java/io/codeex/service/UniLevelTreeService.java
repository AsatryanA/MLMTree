package io.codeex.service;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.entity.UniLevelTreeNodeEntity;

public interface UniLevelTreeService extends TreeService<UniLevelTreeNodeEntity> {
    NodeResponseDto addRelationships(UniLevelTreeNodeEntity parent, NodeRequestDto nodeRequestDto);
}
