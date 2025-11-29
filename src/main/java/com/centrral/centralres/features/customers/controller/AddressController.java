package com.centrral.centralres.features.customers.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centrral.centralres.core.dto.response.PagedResponse;
import com.centrral.centralres.core.security.dto.ApiResponse;
import com.centrral.centralres.core.security.model.User;
import com.centrral.centralres.features.customers.dto.address.request.AddressAdminRequest;
import com.centrral.centralres.features.customers.dto.address.request.AddressCustomerRequest;
import com.centrral.centralres.features.customers.dto.address.response.AddressResponse;
import com.centrral.centralres.features.customers.service.AddressService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping()
    public ResponseEntity<ApiResponse<PagedResponse<AddressResponse>>> getAllAddresses(Pageable pageable) {
        PagedResponse<AddressResponse> paged = addressService.getAllAddresses(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Direcciones obtenidas", paged));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> getAddress(@PathVariable Long id) {
        AddressResponse response = addressService.getAddress(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dirección obtenida", response));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<PagedResponse<AddressResponse>>> getMyAddresses(
            @AuthenticationPrincipal User user,
            Pageable pageable) {
        PagedResponse<AddressResponse> paged = addressService.getAddressesByCustomerAuth(user, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Mis direcciones obtenidas", paged));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<PagedResponse<AddressResponse>>> getAddressesByCustomer(
            @PathVariable Long customerId, Pageable pageable) {
        PagedResponse<AddressResponse> paged = addressService.getAddressesByCustomerId(customerId, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Direcciones obtenidas", paged));
    }

    @PostMapping("/me")
    public ResponseEntity<ApiResponse<AddressResponse>> createMyAddress(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody AddressCustomerRequest dto) {
        AddressResponse response = addressService.createAddressForCustomer(user, dto);
        return new ResponseEntity<>(new ApiResponse<>(true, "Dirección creada", response), HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<AddressResponse>> createAddressForAdmin(
            @Valid @RequestBody AddressAdminRequest dto) {
        AddressResponse response = addressService.createAddressForAdmin(dto);
        return new ResponseEntity<>(new ApiResponse<>(true, "Dirección creada (ADMIN)", response), HttpStatus.CREATED);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> updateAddressAsAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AddressAdminRequest dto) {
        AddressResponse response = addressService.updateAddress(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dirección actualizada (ADMIN)", response));
    }

    @PutMapping("/me/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> updateMyAddress(
            @PathVariable("id") Long addressId,
            @Valid @RequestBody AddressCustomerRequest dto,
            @AuthenticationPrincipal User user) {
        AddressResponse response = addressService.updateAddressForCustomer(addressId, dto, user);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dirección actualizada", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(
            @PathVariable(value = "id") Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dirección eliminada", null));
    }

}
