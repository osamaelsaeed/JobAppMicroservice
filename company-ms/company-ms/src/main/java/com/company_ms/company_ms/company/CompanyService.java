package com.company_ms.company_ms.company;

import com.company_ms.company_ms.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);

    List<Company> getAllCompanies();

    Company updateCompany(Long id, Company company);

    Company deleteCompany(Long id);

    Company getCompanyById(Long id);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
