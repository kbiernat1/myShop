package pl.kb.shop.admin.service;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

class UploadedFilesNameUtils {
    public static String slugifyFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        Slugify slugify = new Slugify();
        String changedName = slugify
                .withCustomReplacement("_", "-")
                .slugify(name);
        return changedName + "." + FilenameUtils.getExtension(fileName);
    }
}
