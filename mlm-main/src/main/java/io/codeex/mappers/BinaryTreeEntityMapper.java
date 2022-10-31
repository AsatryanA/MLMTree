package io.codeex.mappers;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.dto.TreeResponseDto;
import io.codeex.entity.BinaryTreeNodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BinaryTreeEntityMapper {
    private final ModelMapper modelMapper;

    public BinaryTreeEntityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BinaryTreeNodeEntity toEntity(NodeRequestDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, BinaryTreeNodeEntity.class);
    }

    public NodeResponseDto toResponseDto(BinaryTreeNodeEntity nodeEntity) {
        return Objects.isNull(nodeEntity) ? null : modelMapper.map(nodeEntity, NodeResponseDto.class);
    }

    public TreeResponseDto toTreeDto(BinaryTreeNodeEntity nodeEntity) {
        return Objects.isNull(nodeEntity) ? null : modelMapper.map(nodeEntity, TreeResponseDto.class);
    }

}
