package io.codeex.service;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.entity.BaseNodeEntity;
import io.codeex.entity.BinaryTreeNodeEntity;

import java.util.ArrayList;
import java.util.List;


public interface BinaryTreeService extends TreeService<BinaryTreeNodeEntity>{
    NodeResponseDto addChildBFS(BinaryTreeNodeEntity start, NodeRequestDto nodeRequestDto);

}
