package ra.md4.controller.AdminController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.md4.dto.product.CategoryDto;
import ra.md4.dto.product.ProductDto;
import ra.md4.model.product.Category;
import ra.md4.model.product.Product;
import ra.md4.model.user.User;
import ra.md4.repository.AccountRepositoty.UserRepository;
import ra.md4.repository.ProductRepositoty.CategoryRepositoty;
import ra.md4.repository.ProductRepositoty.ProductRepository;
import ra.md4.service.ProductService.ICategoryService;
import ra.md4.service.ProductService.IProductService;
import ra.md4.service.UserService.IRoleService;
import ra.md4.service.UserService.IUserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IProductService productService;
    private final ProductRepository productRepository;
    private final ICategoryService categoryService;
    private final CategoryRepositoty categoryRepositoty;
    private final IUserService userService;
    private final IRoleService roleService;
    private final UserRepository userRepository;

    @GetMapping
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/user")
    public String user(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user";
    }

    @GetMapping("/user/status")
    public String updateStatus(@RequestParam Boolean status, @RequestParam Integer id) {
        User user = userService.findUserById(id);
        if (status) {
            user.setAccountStatus(false);
        } else {
            user.setAccountStatus(true);
        }
        userRepository.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/find")
    public String findbyname(@RequestParam String name, Model model) {
        User user = userService.findUserByName(name);
        model.addAttribute("userList", user);
        return "user";
    }

    @GetMapping("/user/role")
    public String setRole(@RequestParam String role, @RequestParam Integer id) {
        User user = userService.findUserById(id);
        if (role.equals("USER")) {
            user.setRole(roleService.findRoleByName("MOD"));
        } else if (role.equals("MOD")) {
            user.setRole(roleService.findRoleByName("USER"));
        }
        userRepository.save(user);
        return "redirect:/admin/user";
    }


    @GetMapping("/category")
    public String category(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "category";
    }

    @GetMapping("/category/save")
    public String saveCategoryView(@RequestParam(required = false) Integer id, Model model) {
        Category category = new Category();
        if (id != null) {
            category = categoryService.findById(id);
        }
        model.addAttribute("category", category);
        return "saveCategory";
    }

    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute("category") @Valid CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "saveCategory";
        }
        categoryService.save(categoryDto);
        return "redirect:/admin/category";
    }

    @GetMapping("/category/status")
    public String statusCategory(@RequestParam Integer id, @RequestParam Boolean status) {
        Category category = categoryService.findById(id);
        category.setStatus(!status);
        categoryRepositoty.save(category);
        return "redirect:/admin/category";
    }

    @RequestMapping("/category/delete")
    public String delete(@RequestParam Integer id) {
        categoryService.deleteById(id);
        return "redirect:/admin/category";
    }


    //product
    @GetMapping("/product")
    public String product(@RequestParam(name = "name", required = false) String name, Model model) {
        List<Product> productList = new ArrayList<>();
        if (name != null) {
            productList = productService.findProductsByProductNameContaining(name);
        } else {
            productList = productService.findAll();
        }
        model.addAttribute("productList", productList);

        return "product";
    }

    @GetMapping(value = "/product/save")
    public String saveProductView(@RequestParam(required = false) Integer id, Model model) {
        Product product = new Product();
        if (id != null) {
            product = productService.findById(id);
        }
        model.addAttribute("product", product);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "saveProduct";
    }

    @PostMapping(value = "/product/save")
    public String saveProduct(@ModelAttribute("product") @Valid ProductDto productDto, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()){
//            List<Category> categoryList = categoryService.findAll();
//            model.addAttribute("categoryList",categoryList);
//            return "saveProduct";
//        }
        productService.save(productDto);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/status")
    public String statusProduct(@RequestParam Integer id, @RequestParam Boolean status) {
        Product product = productService.findById(id);
        product.setStatus(!status);
        productRepository.save(product);
        return "redirect:/admin/product";
    }
}
