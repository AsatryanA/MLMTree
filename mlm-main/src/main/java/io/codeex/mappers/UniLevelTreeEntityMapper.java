package io.codeex.mappers;

import io.codeex.dto.NodeRequestDto;
import io.codeex.dto.NodeResponseDto;
import io.codeex.dto.TreeResponseDto;
import io.codeex.entity.UniLevelTreeNodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UniLevelTreeEntityMapper {
    private final ModelMapper modelMapper;

    public UniLevelTreeEntityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UniLevelTreeNodeEntity toEntity(NodeRequestDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, UniLevelTreeNodeEntity.class);
    }

    public NodeResponseDto toResponseDto(UniLevelTreeNodeEntity nodeEntity) {
        return Objects.isNull(nodeEntity) ? null : modelMapper.map(nodeEntity, NodeResponseDto.class);
    }

    public TreeResponseDto toTreeDto(UniLevelTreeNodeEntity nodeEntity) {
        return Objects.isNull(nodeEntity) ? null : modelMapper.map(nodeEntity, TreeResponseDto.class);
    }

}
