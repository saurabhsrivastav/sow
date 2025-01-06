package com.ems.sow.services.implementation;

import com.ems.sow.model.ImagesStock;
import com.ems.sow.repositories.DeviceParameterImageRepository;
import com.ems.sow.services.DeviceParameterImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceParameterImageServiceImplementation implements DeviceParameterImageService {


    @Autowired
    private DeviceParameterImageRepository deviceParameterImageRepository;

    @Override
    public List<ImagesStock> getListImages(String osd, String modbus) {
        return deviceParameterImageRepository.findByOsdAndModbusId(osd, modbus);
    }

//    @Override
//    public List<ImagesStock> getImage(String url) {
//        System.out.println("https://emsstockimages.s3.ap-south-1.amazonaws.com/81837_241209145646_0001.rgb565");
//        ImagesStock image = new ImagesStock();
//        System.out.println("https://emsstockimages.s3.ap-south-1.amazonaws.com/" + url);
//        image.setFileUrl("https://emsstockimages.s3.ap-south-1.amazonaws.com/" + url);
//
//        // Return the image in a list
//        return List.of(image);
//    }

//    @Override
//    public byte[] getImage(String s3Key, int width, int height) throws IOException {
//        // Download the file from S3
//        ResponseBytes<?> s3Object = s3Client.getObjectAsBytes(
//                GetObjectRequest.builder()
//                        .bucket(bucketName)
//                        .key(s3Key)
//                        .build()
//        );
//
//        byte[] rgb565Data = s3Object.asByteArray();
//
//        // Convert RGB565 to BufferedImage
//        BufferedImage bufferedImage = decodeRgb565(rgb565Data, width, height);
//
//        // Convert BufferedImage to JPEG
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "jpeg", jpegOutputStream);
//
//        return jpegOutputStream.toByteArray();
//    }
//
//    private BufferedImage decodeRgb565(byte[] rgb565Data, int width, int height) {
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        int index = 0;
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                int rgb565 = ((rgb565Data[index] & 0xFF) << 8) | (rgb565Data[index + 1] & 0xFF);
//                index += 2;
//
//                int r = ((rgb565 >> 11) & 0x1F) * 255 / 31;
//                int g = ((rgb565 >> 5) & 0x3F) * 255 / 63;
//                int b = (rgb565 & 0x1F) * 255 / 31;
//
//                int rgb = (r << 16) | (g << 8) | b;
//                image.setRGB(x, y, rgb);
//            }
//        }
//
//        return image;
//    }
}
