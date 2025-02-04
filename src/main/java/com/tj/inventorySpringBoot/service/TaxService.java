package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.TaxDTO;
import com.tj.inventorySpringBoot.entity.Tax;
import com.tj.inventorySpringBoot.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaxService {

    @Autowired
    private TaxRepository taxRepository;

    // Create a new tax
    public TaxDTO createTax(TaxDTO taxDTO) {
        Tax tax = convertToEntity(taxDTO);
        Tax savedTax = taxRepository.save(tax);
        return convertToDTO(savedTax);
    }

    // Update an existing tax
    public TaxDTO updateTax(Long id, TaxDTO taxDTO) {
        Optional<Tax> existingTaxOptional = taxRepository.findById(id);
        if (existingTaxOptional.isPresent()) {
            Tax existingTax = existingTaxOptional.get();

            // Update the fields of the existing tax
            existingTax.setTaxType(taxDTO.getTaxType());
            existingTax.setRate(taxDTO.getRate());
            existingTax.setDescription(taxDTO.getDescription());
            existingTax.setTaxCategory(taxDTO.getTaxCategory());
            existingTax.setIsActive(taxDTO.getIsActive());
            existingTax.setEffectiveFrom(taxDTO.getEffectiveFrom());
            existingTax.setEffectiveTill(taxDTO.getEffectiveTill());

            Tax updatedTax = taxRepository.save(existingTax);
            return convertToDTO(updatedTax);
        }
        return null; // Handle appropriately if the tax is not found
    }

    // Get all taxes
    public List<TaxDTO> getAllTaxes() {
        List<Tax> taxes = taxRepository.findAll();
        return taxes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a tax by ID
    public TaxDTO getTaxById(Long id) {
        Optional<Tax> taxOptional = taxRepository.findById(id);
        return taxOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a tax by ID
    public void deleteTax(Long id) {
        taxRepository.deleteById(id);
    }

    // Convert TaxDTO to Tax entity
    private Tax convertToEntity(TaxDTO taxDTO) {
        Tax tax = new Tax();
        tax.setTaxId(taxDTO.getTaxId());
        tax.setTaxType(taxDTO.getTaxType());
        tax.setRate(taxDTO.getRate());
        tax.setDescription(taxDTO.getDescription());
        tax.setTaxCategory(taxDTO.getTaxCategory());
        tax.setIsActive(taxDTO.getIsActive());
        tax.setEffectiveFrom(taxDTO.getEffectiveFrom());
        tax.setEffectiveTill(taxDTO.getEffectiveTill());
        return tax;
    }

    // Convert Tax entity to TaxDTO
    private TaxDTO convertToDTO(Tax tax) {
        TaxDTO taxDTO = new TaxDTO();
        taxDTO.setTaxId(tax.getTaxId());
        taxDTO.setTaxType(tax.getTaxType());
        taxDTO.setRate(tax.getRate());
        taxDTO.setDescription(tax.getDescription());
        taxDTO.setTaxCategory(tax.getTaxCategory());
        taxDTO.setIsActive(tax.getIsActive());
        taxDTO.setEffectiveFrom(tax.getEffectiveFrom());
        taxDTO.setEffectiveTill(tax.getEffectiveTill());
        return taxDTO;
    }
}
