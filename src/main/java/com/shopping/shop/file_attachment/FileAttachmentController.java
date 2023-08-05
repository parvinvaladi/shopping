package com.shopping.shop.file_attachment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1/file-attachment")
@AllArgsConstructor
public class FileAttachmentController {

    private final IFileAttachmentService service;
    private FileAttachmentMapper mapper;

    @PostMapping("/")
    public ResponseEntity<Void> save(@RequestBody FileAttachmentDTO fileAttachmentDTO){
        FileAttachment fileAttachment = mapper.toFileAttachment(fileAttachmentDTO);
        service.save(fileAttachment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<FileAttachmentDTO>> getAll(){
        List<FileAttachment> fileAttachments = service.getAll();
        List<FileAttachmentDTO> fileAttachmentDTOS = mapper.toFileAttachmentDTOs(fileAttachments);
        return ResponseEntity.ok(fileAttachmentDTOS);
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<FileAttachmentDTO> update(@PathVariable Long id, @RequestBody FileAttachmentDTO fileAttachmentDTO){
        FileAttachment fileAttachment = mapper.toFileAttachment(fileAttachmentDTO);
        FileAttachment updatedFileAttachment = service.update(id,fileAttachment);
        FileAttachmentDTO updatedFileAttachmentDTO = mapper.toFileAttachmentDto(updatedFileAttachment);
        return ResponseEntity.ok(updatedFileAttachmentDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@PathParam("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
