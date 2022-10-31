package io.codeex.service.impl;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.dto.TreeResponseDto;
import io.codeex.entity.UniLevelTreeNodeEntity;
import io.codeex.entity.relationship.ChildRelationship;
import io.codeex.exception.BusinessNotFoundException;
import io.codeex.exception.IdNotFoundException;
import io.codeex.exception.ParentNotFoundException;
import io.codeex.exception.UserNotFoundException;
import io.codeex.mappers.UniLevelTreeEntityMapper;
import io.codeex.repository.UniLevelTreeRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UniLevelTreeService implements io.codeex.service.UniLevelTreeService {
    private final UniLevelTreeRepository uniLevelTreeRepository;
    private final UniLevelTreeEntityMapper modelMapper;

    public UniLevelTreeService(UniLevelTreeRepository uniLevelTreeRepository, UniLevelTreeEntityMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.uniLevelTreeRepository = uniLevelTreeRepository;
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByNodeId(@NonNull Long id) {
        var node = uniLevelTreeRepository.findFirstById(id);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new IdNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByUserId(@NonNull Long userId) {
        var node = uniLevelTreeRepository.findFirstByUserId(userId);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByUserIdAndBusinessId(@NonNull Long userId, @NonNull Long businessId) {
        var node = uniLevelTreeRepository.findFirstByBusinessIdAndUserId(businessId, userId);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getBusinessAdmin(@NonNull Long businessId) {
        var node = uniLevelTreeRepository.findFirstByBusinessIdAndHeadTrue(businessId);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new BusinessNotFoundException();
        }
    }

    @Transactional
    public NodeResponseDto createBusinessAdmin(NodeRequestDto nodeRequestDto) {
        var head = uniLevelTreeRepository.findFirstByUserId(nodeRequestDto.getUserId());
        if (head != null) {
            throw new UserNotFoundException();
        }

        var business = uniLevelTreeRepository.findFirstByBusinessIdAndHeadTrue(nodeRequestDto.getBusinessId());
        if (business != null) {
            head = business;
        }

        if (head == null) {
            head = modelMapper.toEntity(nodeRequestDto);
            uniLevelTreeRepository.save(head);
        }
        return modelMapper.toResponseDto(head);
    }

    @Transactional
    public NodeResponseDto createNodeWithReferralCode(NodeRequestDto nodeRequestDto) {
        if (nodeRequestDto.getParentId() == null) {
            throw new ParentNotFoundException();
        }
        var parent = uniLevelTreeRepository.findFirstByUserId(nodeRequestDto.getParentId());
        if (parent != null) {
            if (parent.getBusinessId() != nodeRequestDto.getBusinessId()) {
                throw new ParentNotFoundException();
            }
            return addRelationships(parent, nodeRequestDto);
        }
        return createNodeWithoutReferralCode(nodeRequestDto);
    }

    @Transactional
    public NodeResponseDto createNodeWithoutReferralCode(NodeRequestDto nodeRequestDto) {
        var head = uniLevelTreeRepository.findFirstByBusinessIdAndHeadTrue(nodeRequestDto.getBusinessId());
        if (head != null) {
            return addRelationships(head, nodeRequestDto);
        } else {
            throw new BusinessNotFoundException();
        }
    }

    @Transactional
    public NodeResponseDto addRelationships(UniLevelTreeNodeEntity parent, NodeRequestDto nodeRequestDto) {
        var child = modelMapper.toEntity(nodeRequestDto);
        parent.addChild(child);
        child.addParent(parent);
        uniLevelTreeRepository.saveAll(List.of(parent, child));
        return modelMapper.toResponseDto(child);
    }

    @Transactional(readOnly = true)
    public List<NodeResponseDto> getParentsByLevel(@NonNull Long userId, @NonNull Long level) {
        var responseList = new ArrayList<NodeResponseDto>();
        var currentNode = uniLevelTreeRepository.findFirstByUserId(userId);
        if (currentNode == null) {
            throw new UserNotFoundException();
        }
        for (int i = 0; i < level; i++) {
            var parent = currentNode.getParentNode();
            if (parent == null) {
                break;
            }
            responseList.add(modelMapper.toResponseDto(parent));
            currentNode = parent;
        }
        return responseList;
    }

    @Override
    public TreeResponseDto getBusinessTree(@NonNull Long businessId) {
        var head = uniLevelTreeRepository.findFirstByBusinessIdAndHeadTrue(businessId);
        if (head != null) {
            return getTreeBFS(head);
        } else {
            throw new BusinessNotFoundException();
        }
    }

    @Override
    public TreeResponseDto getUserTree(@NonNull Long userId) {
        var start = uniLevelTreeRepository.findFirstByUserId(userId);
        if (start != null) {
            return getTreeBFS(start);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public TreeResponseDto getTreeBFS(UniLevelTreeNodeEntity start) {
        var queueOfNodes = new ArrayDeque<UniLevelTreeNodeEntity>();
        var queueOfDto = new ArrayDeque<TreeResponseDto>();
        var startDto = modelMapper.toTreeDto(start);
        queueOfNodes.add(start);
        queueOfDto.add(startDto);
        while (!queueOfNodes.isEmpty()) {
            var currentNode = queueOfNodes.removeFirst();
            var currentDto = queueOfDto.removeFirst();
            var childNode = currentNode.getChildRelationships().stream()
                    .map(ChildRelationship::getChild)
                    .sorted(Comparator.comparing(UniLevelTreeNodeEntity::getCreatedAt))
                    .toList();
            for (var entity : childNode) {
                queueOfNodes.add(entity);
                var dto = modelMapper.toTreeDto(entity);
                currentDto.addChild(dto);
                queueOfDto.add(dto);
            }
        }
        return startDto;
    }


    @Transactional
    public Boolean disable(@NonNull Long userId) {
        var node = uniLevelTreeRepository.findFirstByUserId(userId);
        if (node == null) {
            throw new UserNotFoundException();
        }
        node.setDisabled(true);
        uniLevelTreeRepository.save(node);
        return true;
    }
}
