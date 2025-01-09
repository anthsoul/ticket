package org.example.application.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RenameFileService {
    public  String renameAvatarFile(long userId, String fileExtension) {
        return "avatar_" + userId + "_" + System.currentTimeMillis() + fileExtension ;
    }
}
