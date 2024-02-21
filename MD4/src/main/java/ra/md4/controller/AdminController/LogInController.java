package ra.md4.controller.AdminController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.md4.dto.user.UserDto;
import ra.md4.model.user.User;
import ra.md4.service.UserService.IUserService;

@Controller
@RequiredArgsConstructor
public class LogInController {
    private final IUserService userService;
    @GetMapping(value = "/save")
    public String register(@RequestParam(required = false)Integer id, Model model){
        User user = new User();
        if(id!=null) {
            user = userService.findUserById(id);
        }
        model.addAttribute("user" , user);
        return "save";
    }
    @PostMapping("/save")
    public String accountSave(@ModelAttribute("user") @Valid UserDto userDto , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "save";
        }
        userService.save(userDto);
        return "redirect:/home";
    }
    @GetMapping("/login")
    public String login(){
        return "redirect:/admin/user";
    }
}
