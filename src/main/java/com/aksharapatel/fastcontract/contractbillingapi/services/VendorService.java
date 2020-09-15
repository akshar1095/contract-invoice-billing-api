package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(Long vendorId) throws RecordNotFoundException {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        return vendor.orElseThrow(() -> new RecordNotFoundException("No vendor record exists for the given vendor id"));
    }
}
