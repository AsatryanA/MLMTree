//package io.codeex.service.impl;
//
//import io.codeex.App;
//import io.codeex.dto.NodeRequestDto;
//import io.codeex.dto.NodeResponseDto;
//import io.codeex.entity.BinaryTreeNodeEntity;
//import io.codeex.enums.TreeType;
//import io.codeex.mapper.BinaryTreeEntityMapper;
//import io.codeex.repository.BinaryTreeRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.modelmapper.spi.MatchingStrategy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest(classes = {App.class})
//@Transactional(propagation = Propagation.NEVER)
////@ContextConfiguration(initializers = BinaryTreeServiceImplTest.TestContainerInitializer.class)
//class BinaryTreeServiceImplTest {
//
//////    static class TestContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//////
//////        @Override
//////        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//////            final Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:4.4-community").withoutAuthentication();
//////            neo4jContainer.start();
//////            configurableApplicationContext
//////                    .addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> neo4jContainer.stop());
//////            TestPropertyValues
//////                    .of(
//////                            "spring.neo4j.uri=" + neo4jContainer.getBoltUrl(),
//////                            "spring.neo4j.authentication.username=neo4j",
//////                            "spring.neo4j.authentication.password=" + neo4jContainer.getAdminPassword()
//////                    )
//////                    .applyTo(configurableApplicationContext.getEnvironment());
//////        }
//////    }
////
////    @Autowired
////    private BinaryTreeRepository binaryTreeRepository;
////
////    @Autowired
////    private BinaryTreeServiceImpl binaryTreeService;
////
////    private BinaryTreeNodeEntity head;
////    private final ModelMapper modelMapper = new ModelMapper();
////
////
//////    @BeforeEach
//////    void setAll() {
//////        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//////        nodeRequestDto.setHead(true);
//////        nodeRequestDto.setTreeType(TreeType.BINARY);
//////        nodeRequestDto.setBusinessId(1L);
//////        nodeRequestDto.setCompanyId(1L);
//////        nodeRequestDto.setUserId(1L);
//////        head = modelMapper.map(nodeRequestDto, BinaryTreeNodeEntity.class);
//////        System.out.println(head);
//////        System.out.println(binaryTreeRepository.save(head));
//////    }
////
////
////    @Test
////    void createHead() {
////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
////        nodeRequestDto.setHead(true);
////        nodeRequestDto.setTreeType(TreeType.BINARY);
////        nodeRequestDto.setBusinessId(1L);
////        nodeRequestDto.setCompanyId(1L);
////        nodeRequestDto.setUserId(1L);
////        var head = binaryTreeService.createHead(nodeRequestDto);
////        System.out.println(head);
////        System.out.println(binaryTreeRepository.findFirstByUserId(2L));
//////        Assertions.assertEquals(head.getNode(), binaryTreeRepository.findFirstByUserId(2L));
////    }
////
////    @Test
////    void createNodeWithReferralCode() {
////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
////        nodeRequestDto.setTreeType(TreeType.BINARY);
////        nodeRequestDto.setBusinessId(1L);
////        nodeRequestDto.setCompanyId(1L);
////        nodeRequestDto.setUserId(17L);
////        nodeRequestDto.setParentId(1L);
////        var child = binaryTreeService.createNodeWithReferralCode(nodeRequestDto);
//////        Assertions.assertEquals(child.getNode().getParent(), head);
//////        Assertions.assertEquals(child.getNode(), binaryTreeRepository.findFirstByUserId(3L));
////    }
////
////    @Test
////    void createNodeWithoutReferralCode() {
////        for(long i = 8; i < 16; i ++){
////            NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
////            nodeRequestDto1.setTreeType(TreeType.BINARY);
////            nodeRequestDto1.setBusinessId(1L);
////            nodeRequestDto1.setCompanyId(1L);
////            nodeRequestDto1.setUserId(i);
//////            nodeRequestDto1.setParentId(1L);
////            var ch1 = binaryTreeService.createNodeWithReferralCode(nodeRequestDto1);
////        }
////
//////        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
//////        nodeRequestDto2.setTreeType(TreeType.BINARY);
//////        nodeRequestDto2.setBusinessId(1L);
//////        nodeRequestDto2.setCompanyId(1L);
//////        nodeRequestDto2.setUserId(4L);
//////        nodeRequestDto2.setParentId(1L);
//////        binaryTreeService.createNodeWithReferralCode(nodeRequestDto2);
////
//////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//////        nodeRequestDto.setTreeType(TreeType.BINARY);
//////        nodeRequestDto.setBusinessId(1L);
//////        nodeRequestDto.setCompanyId(1L);
//////        nodeRequestDto.setUserId(5L);
//////        var child = binaryTreeService.createNodeWithReferralCode(nodeRequestDto);
//////        Assertions.assertEquals(child.getNode().getParent(), ch1.getNode());
//////        Assertions.assertEquals(child.getNode(), binaryTreeRepository.findFirstByUserId(5L));
////    }
////
////    @Test
////    void getParentsByLevel() {
//////        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
//////        nodeRequestDto1.setTreeType(TreeType.BINARY);
//////        nodeRequestDto1.setBusinessId(1L);
//////        nodeRequestDto1.setCompanyId(1L);
//////        nodeRequestDto1.setUserId(3L);
//////        nodeRequestDto1.setParentId(1L);
//////        var ch1 = binaryTreeService.createNodeWithReferralCode(nodeRequestDto1);
//////        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
//////        nodeRequestDto2.setTreeType(TreeType.BINARY);
//////        nodeRequestDto2.setBusinessId(1L);
//////        nodeRequestDto2.setCompanyId(1L);
//////        nodeRequestDto2.setUserId(4L);
//////        nodeRequestDto2.setParentId(1L);
//////        binaryTreeService.createNodeWithReferralCode(nodeRequestDto2);
//////
//////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
//////        nodeRequestDto.setTreeType(TreeType.BINARY);
//////        nodeRequestDto.setBusinessId(1L);
//////        nodeRequestDto.setCompanyId(1L);
//////        nodeRequestDto.setUserId(5L);
//////        var child = binaryTreeService.createNodeWithReferralCode(nodeRequestDto);
////
////        var parentsByLevel = binaryTreeService.getParentsByLevel(5L, 2L,2L);
//////        var parents = List.of(ch1, new NodeResponseDto<BinaryTreeNodeEntity>(head));
//////        Assertions.assertArrayEquals(parentsByLevel.toArray(), parents.toArray());
////    }
////
////    @Test
////    void getNodeByUserId() {
//////        System.out.println(binaryTreeService.getNodeByUserId(2L).getNode());
//////        Assertions.assertEquals(head, binaryTreeService.getNodeByUserId(1L).getNode());
////    }
////
////    @Test
////    void getBusinessTree() {
////        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
////        nodeRequestDto1.setTreeType(TreeType.BINARY);
////        nodeRequestDto1.setBusinessId(1L);
////        nodeRequestDto1.setCompanyId(1L);
////        nodeRequestDto1.setUserId(3L);
////        nodeRequestDto1.setParentId(1L);
////        var ch1 = binaryTreeService.createNodeWithReferralCode(nodeRequestDto1);
////        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
////        nodeRequestDto2.setTreeType(TreeType.BINARY);
////        nodeRequestDto2.setBusinessId(1L);
////        nodeRequestDto2.setCompanyId(1L);
////        nodeRequestDto2.setUserId(4L);
////        nodeRequestDto2.setParentId(1L);
////        binaryTreeService.createNodeWithReferralCode(nodeRequestDto2);
////
////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
////        nodeRequestDto.setTreeType(TreeType.BINARY);
////        nodeRequestDto.setBusinessId(1L);
////        nodeRequestDto.setCompanyId(1L);
////        nodeRequestDto.setUserId(5L);
////        var child = binaryTreeService.createNodeWithReferralCode(nodeRequestDto);
////        System.out.println(binaryTreeService.getBusinessTree(1L));
////    }
////
////    @Test
////    void getUserTree() {
////        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
////        nodeRequestDto1.setTreeType(TreeType.BINARY);
////        nodeRequestDto1.setBusinessId(1L);
////        nodeRequestDto1.setCompanyId(1L);
////        nodeRequestDto1.setUserId(3L);
////        nodeRequestDto1.setParentId(1L);
////        var ch1 = binaryTreeService.createNodeWithReferralCode(nodeRequestDto1);
////        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
////        nodeRequestDto2.setTreeType(TreeType.BINARY);
////        nodeRequestDto2.setBusinessId(1L);
////        nodeRequestDto2.setCompanyId(1L);
////        nodeRequestDto2.setUserId(4L);
////        nodeRequestDto2.setParentId(1L);
////        binaryTreeService.createNodeWithReferralCode(nodeRequestDto2);
////
////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
////        nodeRequestDto.setTreeType(TreeType.BINARY);
////        nodeRequestDto.setBusinessId(1L);
////        nodeRequestDto.setCompanyId(1L);
////        nodeRequestDto.setUserId(5L);
////        var child = binaryTreeService.createNodeWithReferralCode(nodeRequestDto);
////        System.out.println(binaryTreeService.getUserTree(1L));
////    }
////
////    @Test
////    void disable() {
////        NodeRequestDto nodeRequestDto1 = new NodeRequestDto();
////        nodeRequestDto1.setTreeType(TreeType.BINARY);
////        nodeRequestDto1.setBusinessId(1L);
////        nodeRequestDto1.setCompanyId(1L);
////        nodeRequestDto1.setUserId(3L);
////        nodeRequestDto1.setParentId(1L);
////        var ch1 = binaryTreeService.createNodeWithReferralCode(nodeRequestDto1);
////        NodeRequestDto nodeRequestDto2 = new NodeRequestDto();
////        nodeRequestDto2.setTreeType(TreeType.BINARY);
////        nodeRequestDto2.setBusinessId(1L);
////        nodeRequestDto2.setCompanyId(1L);
////        nodeRequestDto2.setUserId(4L);
////        nodeRequestDto2.setParentId(1L);
////        binaryTreeService.createNodeWithReferralCode(nodeRequestDto2);
////
////        NodeRequestDto nodeRequestDto = new NodeRequestDto();
////        nodeRequestDto.setTreeType(TreeType.BINARY);
////        nodeRequestDto.setBusinessId(1L);
////        nodeRequestDto.setCompanyId(1L);
////        nodeRequestDto.setUserId(5L);
////        var child = binaryTreeService.createNodeWithReferralCode(nodeRequestDto);
////        binaryTreeService.disable(5L);
////        System.out.println(binaryTreeRepository.findFirstByUserId(5L));
////    }
//}