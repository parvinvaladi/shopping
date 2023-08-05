package com.shopping.shop.file_attachment;

import com.shopping.shop.category.Category;
import com.shopping.shop.product.Product;
import com.shopping.shop.user.IUserService;
import com.shopping.shop.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileAttachmentService implements IFileAttachmentService{

    private final FileAttachmentRepository repository;
    private IUserService userService;

    @Override
    public void save(FileAttachment fileAttachment) {
        Long fileAttachmentId = fileAttachment.getUser().getId();
        User user = userService.getById(fileAttachmentId);
        fileAttachment.setUser(user);
        repository.save(fileAttachment);
    }

    @Override
    public List<FileAttachment> getAll() {
        List<FileAttachment> list = (List<FileAttachment>) repository.findAll();
        return list;
    }

    @Override
    public FileAttachment update(Long id, FileAttachment fileAttachment) {
        FileAttachment fileAttachment1;
        fileAttachment1 = repository.findById(id).orElseThrow();
        fileAttachment1.setUser(fileAttachment.getUser());
        save(fileAttachment1);
        return fileAttachment1;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
