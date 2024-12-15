package com.rishilearnings.azstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/storages")

public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping
    public List<String> list() {
        return storageService.listFiles();
    }

    @GetMapping(params = "fileName")
    public byte[] getFile(@RequestParam String fileName) {

        return storageService.getFile(fileName);
    }

    @PostMapping()
    public List<String> createBlob(@RequestBody String value) {

        String blobName = "order/request_" + ZonedDateTime.now().toEpochSecond();

        return storageService.uploadDataToBlob(blobName, value);
    }


    @PostMapping("/uploads")
    public List<String> upload
            (@RequestParam MultipartFile file)
            throws IOException {

        return storageService.upload(file);

    }


}
