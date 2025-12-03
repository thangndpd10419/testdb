package com.example.foodbe.controllers;


import com.example.foodbe.payload.ApiResponse;
import com.example.foodbe.payload.PageResponse;
import com.example.foodbe.request.user.UserCreateDTO;
import com.example.foodbe.response.user.UserResponseDTO;
import com.example.foodbe.services.UserService;
import com.example.foodbe.utils.ConstantUtils;
import com.example.foodbe.utils.SortUtils2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<ApiResponse<PageResponse<UserResponseDTO>>> getUserByEmail(
            @Min(value = ConstantUtils.Page.MIN_CURRENT_PAGE, message =  ConstantUtils.Page.PAGE_MIN_MSG)
            @RequestParam(defaultValue = "" + ConstantUtils.Page.DEFAULT_CURRENT_PAGE) Integer page,

            @Min(value = ConstantUtils.Page.MIN_PAGE_SIZE, message = ConstantUtils.Page.SIZE_MIN_MSG)
            @Max(value = ConstantUtils.Page.MAX_PAGE_SIZE, message = ConstantUtils.Page.SIZE_MAX_MSG)
            @RequestParam(defaultValue = "" + ConstantUtils.Page.DEFAULT_PAGE_SIZE) Integer size,

            @RequestParam(defaultValue = "id,asc") String[] sort,
            @RequestParam(defaultValue = "", required = false) String email
    ){
        Pageable pageable = PageRequest.of(page, size, SortUtils2.getSort(sort));
        return ResponseEntity.ok(ApiResponse.success(userService.findByEmail(email, pageable)));
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO createdUser = userService.create(userCreateDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(userService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id){
       userService.deleteById(id);
       return ResponseEntity.ok(ApiResponse.success(ConstantUtils.DELETE_SUCCESSFULLY +id));
    }

}
