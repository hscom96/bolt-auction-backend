package com.neoga.platform.memberstore.store.controller;

import com.neoga.platform.memberstore.member.domain.Members;
import com.neoga.platform.memberstore.member.service.MemberService;
import com.neoga.platform.memberstore.store.dto.StoreDto;
import com.neoga.platform.memberstore.store.service.StoreService;
import com.neoga.platform.security.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/store", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class StoreController {

    private final StoreService storeService;
    private final AuthService authService;
    private final MemberService memberService;

    @ApiOperation(value = "상점 조회", notes = "해당 상점의 정보 조회")
    @GetMapping("/{member-id}")
    public ResponseEntity getStore(@PathVariable(name = "member-id") Long memberId) {

        StoreDto findStoreDto = storeService.getStore(memberId);

        Resource storeResource = new Resource(findStoreDto);
        storeResource.add(linkTo(StoreController.class).slash(findStoreDto.getMemberId()).withSelfRel());
        storeResource.add(new Link("/swagger-ui.html#/store-controller/getStoreUsingGET").withRel("profile"));

        return ResponseEntity.ok(storeResource);
    }

    @ApiOperation(value = "상점 수정", notes = "상점 설명 이미지 등록")
    @PutMapping
    public ResponseEntity updateStore(String description, String memberName, MultipartFile image) throws IOException {

        Members findMember = memberService.findMemberById(authService.getLoginInfo().getMemberId());

        StoreDto store = storeService.updateStore(findMember, description, memberName, image);

        Resource storeResource = new Resource(store);
        storeResource.add(linkTo(StoreController.class).slash(findMember.getId()).withSelfRel());
        storeResource.add(new Link("/swagger-ui.html#/store-controller/updateStoreUsingPUT").withRel("profile"));

        return ResponseEntity.ok(storeResource);
    }

}
