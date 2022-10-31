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
@RequestMapping("/api/v1/user/node")
@RequiredArgsConstructor
public class UserNodeController {

    private final TreeTypeCheckServiceImpl treeTypeCheckService;

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
}
