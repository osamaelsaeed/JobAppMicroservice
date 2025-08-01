package com.company_ms.company_ms.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        Company savedCompany = companyService.createCompany(company);

        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Company>> retrieveAllCompanies(){
        List<Company> savedCompanies = companyService.getAllCompanies();

        return new ResponseEntity<>(savedCompanies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company){
        Company updatedCompany = companyService.updateCompany(id, company);

        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable Long id){
        Company deletedCompany = companyService.deleteCompany(id);

        return new ResponseEntity<>(deletedCompany, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> retrieveCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }



}
