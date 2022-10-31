package io.codeex.service.impl;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.dto.TreeResponseDto;
import io.codeex.enums.TreeType;
import io.codeex.exception.TreeTypeException;
import io.codeex.service.TreeService;
import io.codeex.service.TreeTypeCheckService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreeTypeCheckServiceImpl implements TreeTypeCheckService {
    private final BinaryTreeService binaryTreeService;
    private final UniLevelTreeService uniLevelTreeService;

    public TreeTypeCheckServiceImpl(BinaryTreeService binaryTreeService, UniLevelTreeService uniLevelTreeService) {
        this.binaryTreeService = binaryTreeService;
        this.uniLevelTreeService = uniLevelTreeService;
    }
    private TreeService<?> getService(NodeRequestDto nodeRequestDto) {
        if (nodeRequestDto.getTreeType() == TreeType.BINARY) {
            return binaryTreeService;
        } else if (nodeRequestDto.getTreeType() == TreeType.UNILEVEL) {
            return uniLevelTreeService;
        } else {
            throw new TreeTypeException();
        }
    }
    @Override
    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByNodeId(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).getNodeByNodeId( nodeRequestDto.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByUserId(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).getNodeByUserId(nodeRequestDto.getUserId());
    }

    @Override
    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByUserIdAndBusinessId(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).getNodeByUserIdAndBusinessId(nodeRequestDto.getUserId(), nodeRequestDto.getBusinessId());
    }

    @Override
    @Transactional(readOnly = true)
    public NodeResponseDto getBusinessAdmin(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).getBusinessAdmin(nodeRequestDto.getBusinessId());
    }

    @Override
    @Transactional
    public NodeResponseDto createBusinessAdmin(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).createBusinessAdmin(nodeRequestDto);
    }

    @Override
    @Transactional
    public NodeResponseDto createNodeWithReferralCode(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).createNodeWithReferralCode(nodeRequestDto);
    }

    @Override
    @Transactional
    public NodeResponseDto createNodeWithoutReferralCode(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).createNodeWithoutReferralCode(nodeRequestDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NodeResponseDto> getParentsByLevel(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).getParentsByLevel(nodeRequestDto.getUserId(), nodeRequestDto.getLevel());
    }

    @Override
    @Transactional(readOnly = true)
    public TreeResponseDto getBusinessTree(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).getBusinessTree(nodeRequestDto.getBusinessId());
    }

    @Override
    @Transactional(readOnly = true)
    public TreeResponseDto getUserTree(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).getUserTree(nodeRequestDto.getUserId());
    }

    @Override
    @Transactional
    public Boolean disable(NodeRequestDto nodeRequestDto) {
        return getService(nodeRequestDto).disable(nodeRequestDto.getUserId());
    }
}
