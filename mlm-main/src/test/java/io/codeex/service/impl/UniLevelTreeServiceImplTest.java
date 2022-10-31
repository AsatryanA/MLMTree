//package io.codeex.service.impl;
//
//import io.codeex.App;
//import io.codeex.dto.NodeRequestDto;
//import io.codeex.dto.NodeResponseDto;
//import io.codeex.entity.UniLevelTreeNodeEntity;
//import io.codeex.enums.TreeType;
//import io.codeex.mapper.UniLevelTreeEntityMapper;
//import io.codeex.repository.UniLevelTreeRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest(classes = {App.class})
//@Transactional
//class UniLevelTreeServiceImplTest {
//
//    @Autowired
//    private UniLevelTreeServiceImpl uniLevelTreeService;
//
//    @Autowired
//    private UniLevelTreeRepository uniLevelTreeRepository;
//
//    private UniLevelTreeNodeEntity head;
//    private final ModelMapper modelMapper = new ModelMapper();
//
//    @BeforeEach
//    void setAll() {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setHead(true);
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(1L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(1L);
//        head = modelMapper.map(nodeRequestDto, UniLevelTreeNodeEntity.class);
//        uniLevelTreeRepository.save(head);
//    }
//
//
//    @Test
//    void createHead() {
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setHead(true);
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(2L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(2L);
//        var head = uniLevelTreeService.createHead(nodeRequestDto);
//        System.out.println(head);
//        System.out.println(uniLevelTreeRepository.findByUserId(2L));
//        Assertions.assertEquals(head.getNode(), uniLevelTreeRepository.findByUserId(2L));
//    }
//
//    @Test
//    void createNodeWithReferralCode() {
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(1L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(3L);
//        nodeRequestDto.setParentId(1L);
//        var child = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto);
//        Assertions.assertEquals(child.getNode().getParent(), head);
//        Assertions.assertEquals(child.getNode(), uniLevelTreeRepository.findByUserId(3L));
//    }
//
//    @Test
//    void createNodeWithoutReferralCode() {
//        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
//        nodeRequestDto1.setTreeType(TreeType.BINARY);
//        nodeRequestDto1.setBusinessId(1L);
//        nodeRequestDto1.setCompanyId(1L);
//        nodeRequestDto1.setUserId(3L);
//        nodeRequestDto1.setParentId(1L);
//        var ch1 = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto1);
//        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
//        nodeRequestDto2.setTreeType(TreeType.BINARY);
//        nodeRequestDto2.setBusinessId(1L);
//        nodeRequestDto2.setCompanyId(1L);
//        nodeRequestDto2.setUserId(4L);
//        nodeRequestDto2.setParentId(1L);
//        uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto2);
//
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(1L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(5L);
//        var child = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto);
//        Assertions.assertEquals(child.getNode().getParent(), ch1.getNode());
//        Assertions.assertEquals(child.getNode(), uniLevelTreeRepository.findByUserId(5L));
//    }
//
//    @Test
//    void getParentsByLevel() {
//        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
//        nodeRequestDto1.setTreeType(TreeType.BINARY);
//        nodeRequestDto1.setBusinessId(1L);
//        nodeRequestDto1.setCompanyId(1L);
//        nodeRequestDto1.setUserId(3L);
//        nodeRequestDto1.setParentId(1L);
//        var ch1 = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto1);
//        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
//        nodeRequestDto2.setTreeType(TreeType.BINARY);
//        nodeRequestDto2.setBusinessId(1L);
//        nodeRequestDto2.setCompanyId(1L);
//        nodeRequestDto2.setUserId(4L);
//        nodeRequestDto2.setParentId(1L);
//        uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto2);
//
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(1L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(5L);
//        var child = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto);
//
//        var parentsByLevel = uniLevelTreeService.getParentsByLevel(5L, 2L);
//        var parents = List.of(ch1, new NodeResponseDto<UniLevelTreeNodeEntity>(head));
//        Assertions.assertArrayEquals(parentsByLevel.toArray(), parents.toArray());
//    }
//
//    @Test
//    void getNodeByUserId() {
//        Assertions.assertEquals(head, uniLevelTreeService.getNodeByUserId(1L).getNode());
//    }
//
//    @Test
//    void getBusinessTree() {
//        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
//        nodeRequestDto1.setTreeType(TreeType.BINARY);
//        nodeRequestDto1.setBusinessId(1L);
//        nodeRequestDto1.setCompanyId(1L);
//        nodeRequestDto1.setUserId(3L);
//        nodeRequestDto1.setParentId(1L);
//        var ch1 = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto1);
//        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
//        nodeRequestDto2.setTreeType(TreeType.BINARY);
//        nodeRequestDto2.setBusinessId(1L);
//        nodeRequestDto2.setCompanyId(1L);
//        nodeRequestDto2.setUserId(4L);
//        nodeRequestDto2.setParentId(1L);
//        uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto2);
//
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(1L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(5L);
//        var child = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto);
//        System.out.println(uniLevelTreeService.getBusinessTree(1L));
//    }
//
//    @Test
//    void getUserTree() {
//        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
//        nodeRequestDto1.setTreeType(TreeType.BINARY);
//        nodeRequestDto1.setBusinessId(1L);
//        nodeRequestDto1.setCompanyId(1L);
//        nodeRequestDto1.setUserId(3L);
//        nodeRequestDto1.setParentId(1L);
//        var ch1 = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto1);
//        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
//        nodeRequestDto2.setTreeType(TreeType.BINARY);
//        nodeRequestDto2.setBusinessId(1L);
//        nodeRequestDto2.setCompanyId(1L);
//        nodeRequestDto2.setUserId(4L);
//        nodeRequestDto2.setParentId(1L);
//        uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto2);
//
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(1L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(5L);
//        var child = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto);
//        System.out.println(uniLevelTreeService.getUserTree(1L));
//    }
//
//    @Test
//    void disable() {
//        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
//        nodeRequestDto1.setTreeType(TreeType.BINARY);
//        nodeRequestDto1.setBusinessId(1L);
//        nodeRequestDto1.setCompanyId(1L);
//        nodeRequestDto1.setUserId(3L);
//        nodeRequestDto1.setParentId(1L);
//        var ch1 = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto1);
//        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
//        nodeRequestDto2.setTreeType(TreeType.BINARY);
//        nodeRequestDto2.setBusinessId(1L);
//        nodeRequestDto2.setCompanyId(1L);
//        nodeRequestDto2.setUserId(4L);
//        nodeRequestDto2.setParentId(1L);
//        uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto2);
//
//        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//        nodeRequestDto.setTreeType(TreeType.BINARY);
//        nodeRequestDto.setBusinessId(1L);
//        nodeRequestDto.setCompanyId(1L);
//        nodeRequestDto.setUserId(5L);
//        var child = uniLevelTreeService.createNodeWithReferralCode(nodeRequestDto);
//        uniLevelTreeService.disable(5L);
//        System.out.println(uniLevelTreeRepository.findByUserId(5L));
//    }
//}