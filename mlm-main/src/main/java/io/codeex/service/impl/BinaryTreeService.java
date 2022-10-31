package io.codeex.service.impl;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.dto.TreeResponseDto;
import io.codeex.entity.BinaryTreeNodeEntity;
import io.codeex.exception.*;
import io.codeex.mappers.BinaryTreeEntityMapper;
import io.codeex.repository.BinaryTreeRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@Service
public class BinaryTreeService implements io.codeex.service.BinaryTreeService {
    private final BinaryTreeRepository binaryTreeRepository;
    private final BinaryTreeEntityMapper modelMapper;

    public BinaryTreeService(BinaryTreeRepository binaryTreeRepository, BinaryTreeEntityMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.binaryTreeRepository = binaryTreeRepository;
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByNodeId(@NonNull Long id) {
        var node = binaryTreeRepository.findFirstById(id);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new IdNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByUserId(@NonNull Long userId) {
        var node = binaryTreeRepository.findFirstByUserId(userId);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getNodeByUserIdAndBusinessId(@NonNull Long userId, @NonNull Long businessId) {
        var node = binaryTreeRepository.findFirstByBusinessIdAndUserId(businessId, userId);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public NodeResponseDto getBusinessAdmin(@NonNull Long businessId) {
        var node = binaryTreeRepository.findFirstByBusinessIdAndHeadTrue(businessId);
        if (node != null) {
            return modelMapper.toResponseDto(node);
        } else {
            throw new BusinessNotFoundException();
        }
    }

    @Transactional
    public NodeResponseDto createBusinessAdmin(NodeRequestDto nodeRequestDto) {
        var head = binaryTreeRepository.findFirstByUserId(nodeRequestDto.getUserId());
        if (head != null) {
            throw new UserNotFoundException();
        }

        var business = binaryTreeRepository.findFirstByBusinessIdAndHeadTrue(nodeRequestDto.getBusinessId());
        if (business != null) {
            head = business;
        }

        if (head == null) {
            head = modelMapper.toEntity(nodeRequestDto);
            binaryTreeRepository.save(head);
        }
        return modelMapper.toResponseDto(head);
    }

    @Transactional
    public NodeResponseDto createNodeWithReferralCode(NodeRequestDto nodeRequestDto) {
        if(nodeRequestDto.getParentId() == null) {
            throw new ParentNotFoundException();
        }
        var parent = binaryTreeRepository.findFirstByUserId(nodeRequestDto.getParentId());
        if (parent != null) {
            if (parent.getBusinessId() != nodeRequestDto.getBusinessId()) {
                throw new ParentNotFoundException();
            }
            return addChildBFS(parent, nodeRequestDto);
        } else {
            return createNodeWithoutReferralCode(nodeRequestDto);
        }
    }

    @Transactional
    public NodeResponseDto createNodeWithoutReferralCode(NodeRequestDto nodeRequestDto) {
        var head = binaryTreeRepository.findFirstByBusinessIdAndHeadTrue(nodeRequestDto.getBusinessId());
        if (head != null) {
            return addChildBFS(head, nodeRequestDto);
        } else {
            throw new BusinessNotFoundException();
        }
    }

    @Transactional
    public NodeResponseDto addChildBFS(BinaryTreeNodeEntity start, NodeRequestDto nodeRequestDto) {
        var created = false;
        var child = modelMapper.toEntity(nodeRequestDto);
        var queue = new ArrayDeque<BinaryTreeNodeEntity>();
        queue.add(start);
        while (!queue.isEmpty()) {
            var parent = queue.removeFirst();
            if (parent.addChild(child)) {
                child.addParent(parent);
                binaryTreeRepository.saveAll(List.of(parent, child));
                created = true;
                break;
            } else {
                queue.add(parent.getLeftChildNode());
                queue.add(parent.getRightChildNode());
            }
        }
        if (created) {
            return modelMapper.toResponseDto(child);
        } else {
            throw new UserNotCreatedException();
        }
    }

    @Transactional(readOnly = true)
    public List<NodeResponseDto> getParentsByLevel(@NonNull Long userId, @NonNull Long level) {
        var responseList = new ArrayList<NodeResponseDto>();
        var currentNode = binaryTreeRepository.findFirstByUserId(userId);
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

    @Transactional(readOnly = true)
    public TreeResponseDto getBusinessTree(@NonNull Long businessId) {
        var head = binaryTreeRepository.findFirstByBusinessIdAndHeadTrue(businessId);
        if (head != null) {
            return getTreeBFS(head);
        } else {
            throw new BusinessNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public TreeResponseDto getUserTree(@NonNull Long userId) {
        var start = binaryTreeRepository.findFirstByUserId(userId);
        if (start != null) {
            return getTreeBFS(start);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public TreeResponseDto getTreeBFS(BinaryTreeNodeEntity start) {
        var queueOfNodes = new ArrayDeque<BinaryTreeNodeEntity>();
        var queueOfDto = new ArrayDeque<TreeResponseDto>();
        var startDto = modelMapper.toTreeDto(start);
        queueOfNodes.add(start);
        queueOfDto.add(startDto);
        while (!queueOfNodes.isEmpty()) {
            var currentNode = queueOfNodes.removeFirst();
            var currentDto = queueOfDto.removeFirst();
            var leftChildNode = currentNode.getLeftChildNode();
            var rightChildNode = currentNode.getRightChildNode();
            for (var node : List.of(leftChildNode, rightChildNode)) {
                if (node != null) {
                    queueOfNodes.add(node);
                    var dto = modelMapper.toTreeDto(node);
                    currentDto.addChild(dto);
                    queueOfDto.add(dto);
                }
            }
        }
        return startDto;
    }

    @Transactional
    public Boolean disable(@NonNull Long userId) {
        var node = binaryTreeRepository.findFirstByUserId(userId);
        if (node == null) {
            throw new UserNotFoundException();
        }
        node.setDisabled(true);
        binaryTreeRepository.save(node);
        return true;
    }
}
