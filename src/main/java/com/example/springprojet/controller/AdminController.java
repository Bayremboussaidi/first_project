package com.example.springprojet.controller;

import com.example.springprojet.dto.ProductDTO;
import com.example.springprojet.model.Category;
import com.example.springprojet.model.Categoryjob;
import com.example.springprojet.model.Job;
import com.example.springprojet.model.Product;
import com.example.springprojet.service.CategoryService;
import com.example.springprojet.service.CategoryjobService;
import com.example.springprojet.service.JobService;
import com.example.springprojet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadir = System.getProperty("user.dir") + "/src/main/resources/static/product";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryjobService categoryjobService;
    @Autowired
    JobService jobService;

    @GetMapping("/admin/categories")
    public String getCat(Model model) {
        model.addAttribute("category", categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categorical")
    public String getcatjob(Model model){
        model.addAttribute("categoryjob",categoryjobService.getallcategoryjob());
        return "catjob";
    }

    @GetMapping("/admin/categories/add")
    public String getadd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }


    @PostMapping("/admin/categories/add")
    public String postadd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories job/add")
    public String getaddcatjob(Model model) {
        model.addAttribute("categoryjob", new Categoryjob());
        return "cathodic";
    }
    @PostMapping("/admin/categories job/add")
    public String postaddcategoryjob(@ModelAttribute("categoryjob") Categoryjob categoryjob) {
        categoryjobService.Addcategoryjob(categoryjob);
        return "redirect:/admin/categories job/add";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deletecat(@PathVariable int id) {
        categoryService.removecategory(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categoriesjob/delete/{id}")
    public String deletecatjob(@PathVariable int id) {
        categoryjobService.Removecategoryjob(id);
        return "redirect:/admin/categorical";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updatecat(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getcategorybyid(id);
        if (category.isPresent()) {
            model.addAttribute("categoryjob", category.get());
            return "cathodic";
        } else
            return "404";
    }
    @GetMapping("/admin/categoriesjob/update/{id}")
    public String updatecatjob(@PathVariable int id, Model model) {
        Optional<Categoryjob> categoryjob=categoryjobService.getcategoryjobbyid(id);
        if (categoryjob.isPresent()) {
            model.addAttribute("categoryjob", categoryjob.get());
            return "cathodic";
        } else
            return "404";
    }

    //Product Section
    @GetMapping("/admin/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "product";
    }
    @GetMapping("/admin/job")
    public String job(Model model) {
        model.addAttribute("job", jobService.getalljob());
        return "job";
    }
    @GetMapping("/admin/job2")
    public String jobclient(Model model) {
        model.addAttribute("job", jobService.getalljob());
        return "jobclient";
    }

    @GetMapping("/admin/products/add")
    public String productAdd(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productAdd";
    }
    @GetMapping("/admin/job/add")
    public String jobAdd(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("categories1", categoryjobService.getallcategoryjob());
        return "job-add";
    }



    @PostMapping("/admin/products/add")
    public String productaddpost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getcategorybyid(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        String imageuuid;
        if (!file.isEmpty()) {
            imageuuid = file.getOriginalFilename();
            Path filenameandpath = Paths.get(uploadir, imageuuid);
            Files.write(filenameandpath, file.getBytes());
        } else {
            imageuuid = imgName;
        }
        product.setImageName(imageuuid);
        productService.addproduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.removeproduct(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/job/delete/{id}")
    public String deletejob(@PathVariable Integer id) {
        jobService.removejob(id);
        return "redirect:/admin/job";
    }


    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        Product product = productService.getproductbyid(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        productDTO.setCategoryId(product.getCategory().getId());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "productAdd";
    }
    @GetMapping("/admin/job/update/{id}")
    public String updatejob(@PathVariable Integer id, Model model) {
        Job job=jobService.jetjobbyid(id).get();
        job.setId(job.getId());
        job.setName(job.getName());
        job.setDescription(job.getDescription());
        job.setCategoryjob(job.getCategoryjob());
        jobService.addjob(job);
        model.addAttribute("categories1", categoryjobService.getallcategoryjob());
        model.addAttribute("job", job);
        return "job-add";
    }
    @PostMapping("/admin/job/add")
    public String jobaddpost(@ModelAttribute("job") Job job){
        job.setId(job.getId());
        job.setName(job.getName());
        job.setDescription(job.getDescription());
        job.setCategoryjob(job.getCategoryjob());
        jobService.addjob(job);

        return "redirect:/admin/job/add";
    }
}