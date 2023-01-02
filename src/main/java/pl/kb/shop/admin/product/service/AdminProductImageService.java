package pl.kb.shop.admin.product.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.kb.shop.admin.common.utils.SlugifyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdminProductImageService {

    @Value("${app.uploadDir}")
    private String uploadDir;

    public String uploadImage(String fileName, InputStream inputStream) {
        String newFileName = SlugifyUtils.slugifyFileName(fileName);
        newFileName = ExistingFileRenameUtils.renameIfExists(Path.of(uploadDir), newFileName);
        Path filePath = Paths.get(uploadDir).resolve(newFileName);
        try(OutputStream outputStream = Files.newOutputStream(filePath)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Nie mogę nadpisać pliku", e);
        }
        return newFileName;
    }

    public Resource serveFiles(String fileName) {
        FileSystemResourceLoader fsrl = new FileSystemResourceLoader();
        return fsrl.getResource(uploadDir + fileName);
    }
}
