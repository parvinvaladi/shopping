package com.shopping.shop.file_attachment;

import com.shopping.shop.user.UserMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface FileAttachmentMapper {

    FileAttachmentDTO toFileAttachmentDto(FileAttachment fileAttachment);
    FileAttachment toFileAttachment(FileAttachmentDTO fileAttachmentDTO);
    List<FileAttachment> toFileAttachments(List<FileAttachmentDTO> fileAttachmentDTOS);
    List<FileAttachmentDTO> toFileAttachmentDTOs(List<FileAttachment> fileAttachments);
}
