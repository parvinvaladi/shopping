package com.shopping.shop.file_attachment;

import com.shopping.shop.product.Product;

import java.util.List;

public interface IFileAttachmentService {

    void save(FileAttachment fileAttachment);
    List<FileAttachment> getAll();
    FileAttachment update(Long id ,FileAttachment fileAttachment);
    void delete(Long id);

}
