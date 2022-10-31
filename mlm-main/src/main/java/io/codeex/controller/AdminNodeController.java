package io.codeex.controller;

import io.codeex.dto.NodeRequestDto;
import io.codeex.enums.TreeType;
import io.codeex.service.impl.TreeTypeCheckServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/admin/node")
@RequiredArgsConstructor
public class AdminNodeController {

    private final TreeTypeCheckServiceImpl treeTypeCheckService;

    @PostMapping("/create/business_admin")
    public ResponseEntity<?> createBusinessAdmin(@Valid @RequestBody NodeRequestDto dto) {
        dto.setHead(true);
        return ResponseEntity.ok().body(treeTypeCheckService.createBusinessAdmin(dto));
    }

    @PostMapping("/create/with_referral")
    public ResponseEntity<?> createNodeWithReferralCode(@Valid @RequestBody NodeRequestDto dto) {
        dto.setHead(false);
        return ResponseEntity.ok().body(treeTypeCheckService.createNodeWithReferralCode(dto));
    }

    @PostMapping("/create/without_referral")
    public ResponseEntity<?> createNodeWithoutReferralCode(@Valid @RequestBody NodeRequestDto dto) {
        dto.setHead(false);
        return ResponseEntity.ok().body(treeTypeCheckService.createNodeWithoutReferralCode(dto));
    }

    @GetMapping("/get/parents_by_level")
    public ResponseEntity<?> getParentsByLevel(@RequestParam("userId") Long userId,
                                               @RequestParam("businessId") Long businessId,
                                               @RequestParam("level") @Min(0) Long level,
                                               @RequestParam("treeType") TreeType treeType) {
        var nodeRequestDto = new NodeRequestDto();
        nodeRequestDto.setUserId(userId);
        nodeRequestDto.setBusinessId(businessId);
        nodeRequestDto.setLevel(level);
        nodeRequestDto.setTreeType(treeType);
        return ResponseEntity.ok().body(treeTypeCheckService.getParentsByLevel(nodeRequestDto));
    }

    @GetMapping("/get/node_by_user_id")
    public ResponseEntity<?> getNodeByUserId(@RequestParam Long userId) {
        var nodeRequestDto = new NodeRequestDto();
        nodeRequestDto.setUserId(userId);
        return ResponseEntity.ok().body(treeTypeCheckService.getNodeByUserId(nodeRequestDto));
    }

    @GetMapping("/get/business_tree")
    public ResponseEntity<?> getBusinessTree(@RequestParam Long businessId,
                                             @RequestParam TreeType treeType) {
        var nodeRequestDto = new NodeRequestDto();
        nodeRequestDto.setBusinessId(businessId);
        nodeRequestDto.setTreeType(treeType);
        return ResponseEntity.ok().body(treeTypeCheckService.getBusinessTree(nodeRequestDto));
    }

    @GetMapping("/get/user_tree")
    public ResponseEntity<?> getUserTree(@RequestParam Long userId,
                                         @RequestParam TreeType treeType) {
        var nodeRequestDto = new NodeRequestDto();
        nodeRequestDto.setUserId(userId);
        nodeRequestDto.setTreeType(treeType);
        return ResponseEntity.ok().body(treeTypeCheckService.getUserTree(nodeRequestDto));
    }

    @PostMapping("/disable")
    public Boolean disable(@RequestParam Long userId) {
        var nodeRequestDto = new NodeRequestDto();
        nodeRequestDto.setUserId(userId);
        return treeTypeCheckService.disable(nodeRequestDto);
    }
    @GetMapping("/get/by_node_id")
    public ResponseEntity<?> getNodeByNodeId(@RequestParam("nodeId") Long nodeId){
        var dto = new NodeRequestDto();
        dto.setId(nodeId);
        return ResponseEntity.ok().body(treeTypeCheckService.getNodeByNodeId(dto));
    }
    @GetMapping("/get/by_user_id_and_node_id")
    public ResponseEntity<?> getNodeByUserIdAndBusinessId(@RequestParam("userId") Long userId,
                                                          @RequestParam("businessId") Long businessId){
        var dto = new NodeRequestDto();
        dto.setUserId(userId);
        dto.setBusinessId(businessId);
        return ResponseEntity.ok().body(treeTypeCheckService.getNodeByUserIdAndBusinessId(dto));
    }

    @GetMapping("/get/business_admin")
    public ResponseEntity<?> getBusinessAdmin(@RequestParam("userId") Long businessId){
        var dto = new NodeRequestDto();
        dto.setBusinessId(businessId);
        return ResponseEntity.ok().body(treeTypeCheckService.getBusinessAdmin(dto));
    }
}
