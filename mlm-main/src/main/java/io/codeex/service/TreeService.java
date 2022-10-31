package io.codeex.service;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.dto.TreeResponseDto;
import io.codeex.entity.BaseNodeEntity;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TreeService<T extends BaseNodeEntity> {
    NodeResponseDto getNodeByNodeId(@NonNull Long id);

    NodeResponseDto getNodeByUserId(@NonNull Long userId);

    NodeResponseDto getNodeByUserIdAndBusinessId(@NonNull Long userId, @NonNull Long businessId);

    NodeResponseDto getBusinessAdmin(@NonNull Long businessId);

    NodeResponseDto createBusinessAdmin(NodeRequestDto nodeRequestDto);

    NodeResponseDto createNodeWithReferralCode(NodeRequestDto nodeRequestDto);

    NodeResponseDto createNodeWithoutReferralCode(NodeRequestDto nodeRequestDto);

    List<NodeResponseDto> getParentsByLevel(@NonNull Long userId, @NonNull Long level);

    TreeResponseDto getBusinessTree(@NonNull Long businessId);

    TreeResponseDto getUserTree(@NonNull Long userId);

    TreeResponseDto getTreeBFS(T start);

    Boolean disable(@NonNull Long userId);


}
