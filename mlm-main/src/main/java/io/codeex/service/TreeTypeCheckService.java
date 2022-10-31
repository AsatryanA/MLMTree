package io.codeex.service;

import io.codeex.dto.TreeResponseDto;
import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.entity.BaseNodeEntity;
import org.w3c.dom.Node;

import java.util.List;

public interface TreeTypeCheckService {
    NodeResponseDto getNodeByNodeId(NodeRequestDto nodeRequestDto);

    NodeResponseDto getNodeByUserId(NodeRequestDto nodeRequestDto);

    NodeResponseDto getNodeByUserIdAndBusinessId(NodeRequestDto nodeRequestDto);

    NodeResponseDto getBusinessAdmin(NodeRequestDto nodeRequestDto);

    NodeResponseDto createBusinessAdmin(NodeRequestDto nodeRequestDto);

    NodeResponseDto createNodeWithReferralCode(NodeRequestDto nodeRequestDto);

    NodeResponseDto createNodeWithoutReferralCode(NodeRequestDto nodeRequestDto);

    List<NodeResponseDto> getParentsByLevel(NodeRequestDto nodeRequestDto);

    TreeResponseDto getBusinessTree(NodeRequestDto nodeRequestDto);

    TreeResponseDto getUserTree(NodeRequestDto nodeRequestDto);

    Boolean disable(NodeRequestDto nodeRequestDto);
}
