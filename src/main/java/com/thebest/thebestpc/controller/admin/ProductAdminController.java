package com.thebest.thebestpc.controller.admin;

import com.thebest.thebestpc.dto.CreateProductDto;
import com.thebest.thebestpc.mapper.ProductMapper;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.model.ProductConfig;
import com.thebest.thebestpc.service.categories.CategoriesService;
import com.thebest.thebestpc.service.file.FileService;
import com.thebest.thebestpc.service.product.ProductService;
import com.thebest.thebestpc.service.productConfig.ProductConfigService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductAdminController {
    CategoriesService categoriesService;
    private final ProductMapper productMapper;
    private final FileService fileService;
    ProductService productService;
    ProductConfigService productConfigService;

    @GetMapping
    public String products(Model model) {
        model.addAttribute("categories", categoriesService.findAllCategories());
        model.addAttribute("products", productService.findAllProducts());
        return "view/admin/AdminForm";
    }

    @PostMapping
    public String addProduct(@RequestParam("files") MultipartFile file, @ModelAttribute CreateProductDto dto) {
        try {

            Product product = productMapper.mapToEntity(dto);
            fileService.saveFile(file);
            product.setImage(file.getOriginalFilename());
            productService.addProduct(product);
            product.getProductConfigs().forEach(productConfig -> productConfig.setProduct(product));
            productConfigService.saveAll(product.getProductConfigs().stream().toList());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        return "redirect:/admin/products";
    }

    @GetMapping("/config/{id}")
    public ResponseEntity<List<ProductConfig>> showConfigByIdProduct(@PathVariable Long id) {
        List<ProductConfig> productConfigs = productConfigService.findByProductId(id);
        return ResponseEntity.ok(productConfigs);

    }
}
