package com.example.xmlexercise;

import com.example.xmlexercise.model.dto.categoriesByProductsCount.CategoryRootInfoDto;
import com.example.xmlexercise.model.dto.productsInRange.ProductViewRootDto;
import com.example.xmlexercise.model.dto.seed.CategorySeedRootDto;
import com.example.xmlexercise.model.dto.seed.ProductSeedRootDto;
import com.example.xmlexercise.model.dto.seed.UserSeedRootDto;
import com.example.xmlexercise.model.dto.successfullySoldProducts.ProductSoldRootDto;
import com.example.xmlexercise.model.dto.usersAndProducts.UserInfoRootDto;
import com.example.xmlexercise.service.CategoryService;
import com.example.xmlexercise.service.ProductService;
import com.example.xmlexercise.service.UserService;
import com.example.xmlexercise.util.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String RESOURCES_FILE_PATH = "src/main/resources/files/";
    private static final String OUTPUT_FILE_PATH = "out/";
    private static final String CATEGORIES_FILE_NAME = "categories.xml";
    private static final String USERS_FILE_NAME = "users.xml";
    private static final String PRODUCTS_FILE_NAME = "products.xml";
    private static final String PRODUCT_IN_RANGE_FILE_NAME = "products-in-range.xml";
    private static final String USERS_SOLD_PRODUCTS = "users-sold-products.xml";
    private static final String CATEGORIES_PRODUCT = "categories-by-products.xml";
    private static final String USERS_SOLD_PRODUCTS_FULL_INFO = "users-and-products.xml";


    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Select exercise: ");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
            case 1 -> productsInRange();
            case 2 -> soldProducts();
            case 3 -> categoriesInfo();
            case 4 -> userInfo();
        }

    }

    private void userInfo() throws JAXBException {
        UserInfoRootDto userInfoRootDto =
                userService.getProductUserInfo();
        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + USERS_SOLD_PRODUCTS_FULL_INFO,
                userInfoRootDto);

    }

    private void categoriesInfo() throws JAXBException {
       CategoryRootInfoDto categoryRootDto = categoryService
                .getCategoryInfo();

        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + CATEGORIES_PRODUCT,
                categoryRootDto);
    }


    private void soldProducts() throws JAXBException {
        ProductSoldRootDto rootDto = userService
                .getUserInfo();

        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH +USERS_SOLD_PRODUCTS,
                rootDto);

    }

    private void productsInRange() throws JAXBException {
        ProductViewRootDto rootDto = productService
                .findProductsInRangeWithoutBuyer();

        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + PRODUCT_IN_RANGE_FILE_NAME,
                rootDto);

    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if(categoryService.getEntityCount() == 0) {
            CategorySeedRootDto categorySeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + CATEGORIES_FILE_NAME,
                            CategorySeedRootDto.class);

            categoryService.seedCategories(categorySeedRootDto.getCategories());
        }

        if(userService.getCount() == 0){
           UserSeedRootDto userSeedRootDto = xmlParser
                    .fromFile(RESOURCES_FILE_PATH + USERS_FILE_NAME,
                            UserSeedRootDto.class);

           userService.seedUsers(userSeedRootDto.getUsers());

        }

        if(productService.getCount() == 0){
            ProductSeedRootDto productSeedRootDto = xmlParser
                    .fromFile(RESOURCES_FILE_PATH + PRODUCTS_FILE_NAME,
                            ProductSeedRootDto.class);
            productService.seedProducts(productSeedRootDto.getProducts());

        }

    }
}
