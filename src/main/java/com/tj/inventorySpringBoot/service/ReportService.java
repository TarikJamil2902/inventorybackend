package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ReportDTO;
import com.tj.inventorySpringBoot.entity.Report;
import com.tj.inventorySpringBoot.entity.Employee;  // Assuming Employee entity exists
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.repository.ReportRepository;
import com.tj.inventorySpringBoot.repository.UserRepository;
import com.tj.inventorySpringBoot.repository.EmployeeRepository; // Assuming Employee repository exists
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;  // Injecting Employee repository

    // Create a new report
    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = convertToEntity(reportDTO);
        Report savedReport = reportRepository.save(report);
        return convertToDTO(savedReport);
    }

    // Update an existing report
    public ReportDTO updateReport(Long id, ReportDTO reportDTO) {
        Optional<Report> existingReportOptional = reportRepository.findById(id);
        if (existingReportOptional.isPresent()) {
            Report existingReport = existingReportOptional.get();

            // Update fields
            existingReport.setReportType(reportDTO.getReportType());
            existingReport.setStartDate(reportDTO.getStartDate());
            existingReport.setEndDate(reportDTO.getEndDate());
            existingReport.setGeneratedAt(reportDTO.getGeneratedAt());
            existingReport.setData(reportDTO.getData());

            // Update the generatedBy employee
            Employee generatedByEmployee = employeeRepository.findById(reportDTO.getGeneratedByEmployeeId()).orElse(null);
            existingReport.setGeneratedBy(generatedByEmployee);

            // Update the createdBy user
            User createdByUser = userRepository.findByUserName(reportDTO.getCreatedByUserName()).orElse(null);
            existingReport.setCreatedBy(createdByUser);

            Report updatedReport = reportRepository.save(existingReport);
            return convertToDTO(updatedReport);
        }
        return null; // Return null or throw an exception if not found
    }

    // Get all reports
    public List<ReportDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a report by its ID
    public ReportDTO getReportById(Long id) {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (reportOptional.isPresent()) {
            return convertToDTO(reportOptional.get());
        }
        return null; // Return null or throw an exception if not found
    }

    // Delete a report by its ID
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    // Convert ReportDTO to Report entity
    private Report convertToEntity(ReportDTO reportDTO) {
        Report report = new Report();
        report.setReportId(reportDTO.getReportId());
        report.setReportType(reportDTO.getReportType());
        report.setStartDate(reportDTO.getStartDate());
        report.setEndDate(reportDTO.getEndDate());
        report.setGeneratedAt(reportDTO.getGeneratedAt());
        report.setData(reportDTO.getData());

        // Set the generatedBy employee from the Employee repository based on employee ID
        Employee generatedByEmployee = employeeRepository.findById(reportDTO.getGeneratedByEmployeeId()).orElse(null);
        report.setGeneratedBy(generatedByEmployee);

        // Set the createdBy user from the User repository based on userName
        User createdByUser = userRepository.findByUserName(reportDTO.getCreatedByUserName()).orElse(null);
        report.setCreatedBy(createdByUser);

        return report;
    }

    // Convert Report entity to ReportDTO
    private ReportDTO convertToDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setReportId(report.getReportId());
        reportDTO.setReportType(report.getReportType());
        reportDTO.setStartDate(report.getStartDate());
        reportDTO.setEndDate(report.getEndDate());
        reportDTO.setGeneratedAt(report.getGeneratedAt());
        reportDTO.setData(report.getData());

        // Set generatedByEmployeeId from the generatedBy employee
        if (report.getGeneratedBy() != null) {
            reportDTO.setGeneratedByEmployeeId(report.getGeneratedBy().getEmployeeId());
        }

        // Set createdByUserName from the createdBy user
        if (report.getCreatedBy() != null) {
            reportDTO.setCreatedByUserName(report.getCreatedBy().getUserName());
        }

        return reportDTO;
    }
}
