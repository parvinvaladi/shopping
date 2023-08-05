package com.shopping.shop.file_storage;

import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MinioStorageController {

    @Autowired
    MinioAdapter minioAdapter;

    @GetMapping("/buckets")
    public List<Bucket> getAllBuckets(){
        return minioAdapter.getAllBuckets();
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String,String> uploadFile(@RequestPart(value = "file",required = false)MultipartFile file) throws IOException{
        String key = minioAdapter.uploadFile(file);
        Map<String,String> result = new HashMap<>();
        result.put("key",key);
        return result;
    }

    @GetMapping(path = "/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "file") String file) throws IOException {
        byte[] data = minioAdapter.getFile(file);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("content-type","application/octet-stream")
                .header("content-disposition","attachment; filename=\""+ file+"\"")
                .body(resource);
    }
}
