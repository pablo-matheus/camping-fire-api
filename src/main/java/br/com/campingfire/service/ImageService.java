package br.com.campingfire.service;

import br.com.campingfire.model.Image;
import br.com.campingfire.repository.ImageRepository;
import br.com.campingfire.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageService {

    private final ImageRepository imageRepository;

    private final CampingService campingService;

    public Image saveRequest(MultipartFile file, Long campingId) throws IOException {

        //TODO Refactor this method removing constructor (reduces the necessity of further adding new variables)
        Image image = new Image(
                file.getOriginalFilename(),
                file.getContentType(),
                ImageUtils.compressBytes(file.getBytes()),
                file.getSize());

        image.setCamping(campingService.findById(campingId));

        return imageRepository.save(image);

    }

    public Image findById(Long id) {

        Optional<Image> retrievedImage = imageRepository.findById(id);

        //TODO Refactor this method removing constructor (reduces the necessity of further adding new variables)
        Image image = new Image(
                retrievedImage.get().getName(),
                retrievedImage.get().getType(),
                ImageUtils.decompressBytes(retrievedImage.get().getFile()),
                retrievedImage.get().getSize());

        return image;

    }

}
