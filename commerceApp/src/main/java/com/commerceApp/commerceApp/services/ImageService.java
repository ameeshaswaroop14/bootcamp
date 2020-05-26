package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.bootloader.Bootstrap;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.product.ProductVariation;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ObjectDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageService {
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    String firstPath = System.getProperty("user.dir");
    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }


    public ResponseEntity downloadImage(String fileName, HttpServletRequest request) throws IOException {
        String fileBasePath = firstPath + "/src/main/resources/users/";
        return getImage(fileBasePath,fileName,request);

    }

    public ResponseEntity downloadImageOfProductVariation(String fileName, HttpServletRequest request) throws IOException {
        String fileBasePath = firstPath + "/src/main/resources/productVariation/";
        return getImage(fileBasePath,fileName,request);
    }

    public ResponseEntity<Object> uploadSingleImage(MultipartFile file, Customer customer) throws IOException {
        File convertfile = new File(firstPath + "/src/main/resources/users/images" + file.getOriginalFilename());
        convertfile.createNewFile();
        String fileBasePath = firstPath + "/src/main/resources/users/";
        Path path = Paths.get(fileBasePath + convertfile.getName());
        FileOutputStream fout = new FileOutputStream(convertfile);
        fout.write(file.getBytes());
        fout.close();
        Optional<String> ext = getExtensionByStringHandling(convertfile.getName());
        changeFileName(customer,ext,path);
        return new ResponseEntity<>("file added", HttpStatus.OK);
    }


    public ResponseEntity<Object> uploadSingleImageForProductVariation(MultipartFile file, Long varId) throws IOException {
        File convertfile = new File(firstPath + "/src/main/resources/productVariation/images" + file.getOriginalFilename());
        convertfile.createNewFile();
        String fileBasePath = firstPath + "/src/main/resources/productVariation/";
        Path path = Paths.get(fileBasePath + convertfile.getName());
        FileOutputStream fout = new FileOutputStream(convertfile);
        logger.info(convertfile.getAbsolutePath());

        fout.write(file.getBytes());
        fout.close();
        Optional<String> ext = getExtensionByStringHandling(convertfile.getName());
        int count = 0;
        File dir = new File(fileBasePath);
        if (ext.isPresent()) {
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File file1 : files) {
                    String value = varId.toString();
                    if (file1.getName().startsWith(value)) {
                        count++;
                        logger.info(String.valueOf(count));

                    }
                }
                String value1 = varId.toString();
                value1 = value1 + "_" + count;
                Files.move(path, path.resolveSibling(value1 + "." + ext.get()));
            }
        } else {
            throw new RuntimeException();
        }
        return new ResponseEntity<>("file added", HttpStatus.OK);
    }



    public void changeFileName(Customer customer,Optional<String> ext,Path path) throws IOException {
        String fileBasePath = firstPath + "/src/main/resources/users/";
        File dir = new File(fileBasePath);
        if (ext.isPresent())
        {
            logger.info(ext.get());

            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File file1 : files) {
                    String value = customer.getId().toString()+"_";
                    if (file1.getName().startsWith(value)) {
                        Files.delete(Paths.get(file1.getPath()));
                    }
                }

                String value = customer.getId().toString()+"_";
                Files.move(path, path.resolveSibling(value+"." + ext.get()));
            }

        } else {
            throw new RuntimeException();
        }

    }

    public ResponseEntity getImage(String fileBasePath, String fileName, HttpServletRequest request
    ) throws IOException {
        File dir = new File(fileBasePath);
        Resource resource = null;
        String contentType = null;
        if (dir.isDirectory()) {
            File arr[] = dir.listFiles();
            for (File file : arr) {
                if (file.getName().startsWith(fileName)) {
                    Path path = Paths.get(fileBasePath + file.getName());
                    try {
                        resource = new UrlResource(path.toUri());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                }
            }
        }
        logger.info(contentType);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}
