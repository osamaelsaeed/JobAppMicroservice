package com.company_ms.company_ms.company.servicesImpl;

import com.company_ms.company_ms.company.Company;
import com.company_ms.company_ms.company.CompanyRepository;
import com.company_ms.company_ms.company.CompanyService;
import com.company_ms.company_ms.company.clients.ReviewClient;
import com.company_ms.company_ms.company.dto.ReviewMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ReviewClient reviewClient;



    @Override
    public Company createCompany(Company company) {
        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company companyDB = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id" + id));

        companyDB.setDescription(company.getDescription());
        companyDB.setName(company.getName());

        Company savedCompany = companyRepository.save(companyDB);

        return savedCompany;
    }

    @Override
    public Company deleteCompany(Long id) {
        Company companyDB = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id" + id));
        companyRepository.deleteById(id);
        return companyDB;
    }

    @Override
    public Company getCompanyById(Long id) {
        Company companyDB = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id" + id));
        return companyDB;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {

        Company companyDB = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with id" + reviewMessage.getCompanyId()));

        double rating = reviewClient.getAverageRating(reviewMessage.getCompanyId());

        companyDB.setAverageRating(rating);

        companyRepository.save(companyDB);

    }
}
