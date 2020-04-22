package com.knightuser.rest;

import static com.knightuser.Constants.ROLES_BASE_URL;

import com.knightuser.domain.RoleVO;
import com.knightuser.rest.mapper.RestRoleMapper;
import com.knightuser.rest.request.RoleRequestDto;
import com.knightuser.rest.response.RoleResponseDto;
import com.knightuser.service.RoleService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROLES_BASE_URL)
@Slf4j
public class RoleResource {

    private final RoleService roleService;
    private final RestRoleMapper roleMapper;

    @PostMapping
    public ResponseEntity<RoleResponseDto> addRole(@Valid @RequestBody RoleRequestDto roleRequestDto) {
        log.info("Rest request to add role : {}", roleRequestDto);
        RoleVO roleVO = roleService.save(roleMapper.toRoleVO(roleRequestDto));
        return new ResponseEntity<>(roleMapper.toRoleResponseDto(roleVO), HttpStatus.CREATED);
    }
}
