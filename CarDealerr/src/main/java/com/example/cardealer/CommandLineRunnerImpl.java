package com.example.cardealer;

import com.example.cardealer.model.dto.carsFromMakeToyota.CarsInfoRootDto;
import com.example.cardealer.model.dto.carsWithTheirListOfParts.CarsRootGetInfoDto;
import com.example.cardealer.model.dto.localSuppliers.SuppliersInfoRootDto;
import com.example.cardealer.model.dto.orderedCustomers.CustomersInfoDto;
import com.example.cardealer.model.dto.orderedCustomers.CustomersInfoRootDto;
import com.example.cardealer.model.dto.seed.CarSeedRootDto;
import com.example.cardealer.model.dto.seed.CustomerSeedRootDto;
import com.example.cardealer.model.dto.seed.PartSeedRootDto;
import com.example.cardealer.model.dto.seed.SupplierSeedRootDto;
import com.example.cardealer.model.entity.Sale;
import com.example.cardealer.service.*;
import com.example.cardealer.util.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final String RESOURCES_FILE_PATH = "src/main/resources/files/";
    private static final String OUTPUT_FILE_PATH = "out/";
    private static final String SUPPLIERS_FILE_NAME = "suppliers.xml";
    private static final String PARTS_FILE_NAME = "parts.xml";
    private static final String CARS_FILE_NAME = "cars.xml";
    private static final String CUSTOMERS_FILE_NAME = "customers.xml";
    private static final String ORDERED_CUSTOMERS = "ordered-customers.xml";
    private static final String TOYOTA_CARS = "toyota-cars.xml";
    private static final String LOCAL_SUPPLIERS = "local-suppliers.xml";
    private static final String CARS_AND_PARTS = "cars-and-parts.xml";

    private final XmlParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(XmlParser xmlParser, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedAll();
        //getOrderedCustomers();
        //getCarsModelToyota();
        //getLocalSuppliers();
        getCarsWithTheirProducts();
    }

    private void getCarsWithTheirProducts() throws JAXBException {
        CarsRootGetInfoDto carsRootGetInfoDto =
                carService.getAllCarsFullInfo();
        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + CARS_AND_PARTS,
                carsRootGetInfoDto);
    }

    private void getLocalSuppliers() throws JAXBException {
        SuppliersInfoRootDto suppliersInfoRootDto =
                supplierService.getSupplierInfo();
        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + LOCAL_SUPPLIERS,
                suppliersInfoRootDto);

    }

    private void getCarsModelToyota() throws JAXBException {
        CarsInfoRootDto carsInfoRootDto =
                carService.getCarInfoToyota();
        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + TOYOTA_CARS,
                carsInfoRootDto);
    }

    private void getOrderedCustomers() throws JAXBException {
        CustomersInfoRootDto customersInfoRootDto =
                customerService.getCustomers();
        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + ORDERED_CUSTOMERS,
                customersInfoRootDto);
    }

    private void seedAll() throws JAXBException, FileNotFoundException {
        seedSupplier();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }

    private void seedSales() {
        if(saleService.getCount() == 0){
            saleService.seedSales();
        }
    }

    private void seedCustomers() throws JAXBException, FileNotFoundException {
        if(customerService.getCount() == 0){
            CustomerSeedRootDto customerSeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + CUSTOMERS_FILE_NAME,
                            CustomerSeedRootDto.class);

            customerService.seedCustomers(customerSeedRootDto.getCustomers());
        }
    }

    private void seedCars() throws JAXBException, FileNotFoundException {
        if(carService.getCount() == 0){
            CarSeedRootDto carSeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + CARS_FILE_NAME,
                            CarSeedRootDto.class);

            carService.seedCars(carSeedRootDto.getCars());

        }
    }

    private void seedParts() throws JAXBException, FileNotFoundException {
        if(partService.getCount() == 0){
            PartSeedRootDto partSeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + PARTS_FILE_NAME,
                            PartSeedRootDto.class);

            partService.seedParts(partSeedRootDto.getParts());
        }
    }

    private void seedSupplier() throws JAXBException, FileNotFoundException {
        if(supplierService.getCount() == 0){
            SupplierSeedRootDto supplierSeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + SUPPLIERS_FILE_NAME,
                            SupplierSeedRootDto.class);

            supplierService.seedSuppliers(supplierSeedRootDto.getSuppliers());
        }
    }
}
