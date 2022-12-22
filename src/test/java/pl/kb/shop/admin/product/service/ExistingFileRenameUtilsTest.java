package pl.kb.shop.admin.product.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import pl.kb.shop.admin.product.service.ExistingFileRenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ExistingFileRenameUtilsTest {

    @Test
    void shouldRenameExistingFile(@TempDir Path tempdir) throws IOException {
        Files.createFile(tempdir.resolve("test.png"));
        String newName = ExistingFileRenameUtils.renameIfExists(tempdir, "test.png");
        assertEquals("test-1.png", newName);
    }

    @Test
    void shouldNotRenameExistingFile(@TempDir Path tempdir) throws IOException {
        String newName = ExistingFileRenameUtils.renameIfExists(tempdir, "test.png");
        assertEquals("test.png", newName);
    }

    @Test
    void shouldRenameManyExistingFiles(@TempDir Path tempdir) throws IOException {
        Files.createFile(tempdir.resolve("test.png"));
        Files.createFile(tempdir.resolve("test-1.png"));
        Files.createFile(tempdir.resolve("test-2.png"));
        Files.createFile(tempdir.resolve("test-3.png"));
        String newName = ExistingFileRenameUtils.renameIfExists(tempdir, "test.png");
        assertEquals("test-4.png", newName);
    }


}