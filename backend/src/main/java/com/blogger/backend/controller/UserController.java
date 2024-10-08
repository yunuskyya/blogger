package com.blogger.backend.controller;

import com.blogger.backend.dto.request.RegisterUserRequest;
import com.blogger.backend.dto.request.UserUnLockedRequset;
import com.blogger.backend.dto.response.GetAllUserResponse;
import com.blogger.backend.dto.response.GetUserByIdResponse;
import com.blogger.backend.model.enums.Role;
import com.blogger.backend.repository.UserRepository;
import com.blogger.backend.service.AuthService;
import com.blogger.backend.service.UserService;
import com.blogger.backend.shared.GenericMessage;
import com.blogger.backend.shared.Messages;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.blogger.backend.constant.BloggerConstant.API_V1;

@RestController
@RequestMapping(API_V1 +"/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthService authService;


    @PostMapping()
    GenericMessage registerUser(@Valid @RequestBody RegisterUserRequest request) {
        userService.registerUser(request);
        return new GenericMessage(Messages.getMessageForLocale("blogger.register.user.success.message.successfully",
                LocaleContextHolder.getLocale()));
    }
    @PatchMapping("/{token}/active")
    GenericMessage activationUser(@PathVariable String token) {
        userService.activationUser(token);
        return new GenericMessage(Messages.getMessageForLocale("blogger.register.user.activation.success.message.successfully",
                LocaleContextHolder.getLocale()));
    }

    @GetMapping()
    public  ResponseEntity<Page<GetAllUserResponse>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetUserByIdResponse> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PutMapping("/{requset}/unLocked")
    GenericMessage unLockedUser(@PathVariable UserUnLockedRequset requset){
        userService.unLockedUser(requset);
        return new GenericMessage(Messages.getMessageForLocale("blogger.register.user.unLocked.success.message.successfully",
                LocaleContextHolder.getLocale()));
    }
    @PatchMapping("/{id}/role")
    public ResponseEntity<?> assignRole(@PathVariable int id, @RequestParam Role newRole) {
        int currentUserId = authService.getCurrentUserId();
        userService.assignRole(id, newRole, currentUserId);
        return ResponseEntity.ok(new GenericMessage("Rol başarıyla güncellendi"));
    }
}
