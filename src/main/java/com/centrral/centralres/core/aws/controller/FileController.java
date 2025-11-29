package com.centrral.centralres.core.aws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.centrral.centralres.core.aws.dto.response.DeleteResponse;
import com.centrral.centralres.core.aws.dto.response.FileResponse;
import com.centrral.centralres.core.aws.service.FileService;
import com.centrral.centralres.core.security.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

        private final FileService fileService;

        @PostMapping("/upload")
        public ResponseEntity<ApiResponse<FileResponse>> uploadFile(
                        @RequestParam("file") MultipartFile file,
                        @RequestParam(value = "folder", defaultValue = "general") String folder) {

                var metadata = fileService.uploadFile(file, folder);

                var details = metadata.getDetails();

                var response = FileResponse.builder()
                                .id(metadata.getId())
                                .fileName(metadata.getOriginalName())
                                .fileUrl(metadata.getUrl())
                                .folder(metadata.getFolder())
                                .uploadedById(metadata.getUploadedBy().getId())
                                .uploadedByUsername(metadata.getUploadedBy().getUsername())
                                .uploadDate(metadata.getUploadDate())
                                .size(details != null ? details.getSize() : null)
                                .contentType(details != null ? details.getContentType() : null)
                                .build();

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Archivo subido exitosamente", response));
        }

        @DeleteMapping("/delete/{fileId}")
        public ResponseEntity<ApiResponse<DeleteResponse>> deleteFile(@PathVariable Long fileId) {
                var result = fileService.deleteFile(fileId);

                var response = new DeleteResponse(
                                result.getFileId(),
                                result.getDeletedById(),
                                result.getDeletedByUsername());

                return ResponseEntity.ok(
                                new ApiResponse<>(true, "Archivo eliminado exitosamente", response));
        }
}
