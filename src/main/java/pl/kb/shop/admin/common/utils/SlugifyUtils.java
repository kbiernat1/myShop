package pl.kb.shop.admin.common.utils;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

public class SlugifyUtils {
    public static String slugifyFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        Slugify slugify = new Slugify();
        String changedName = slugify
                .withCustomReplacement("_", "-")
                .slugify(name);
        return changedName + "." + FilenameUtils.getExtension(fileName);
    }

    public static String slugifySlug(String slug) {
        Slugify slugify = new Slugify();
        return slugify.withCustomReplacement("_", "-")
                .slugify(slug);
    }
}
