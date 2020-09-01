package br.com.campingfire.service;

import br.com.campingfire.model.Image;
import br.com.campingfire.repository.ImageRepository;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageService {

    private final ImageRepository imageRepository;

    private final CampingService campingService;

    private static Storage storage = StorageOptions.getDefaultInstance().getService();

    public Image saveRequest(MultipartFile file, Long campingId) throws IOException {

        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder("camping-fire", file.getOriginalFilename()).build(),
                file.getBytes(),
                Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ));

        Image image = new Image(file.getOriginalFilename(), blobInfo.getMediaLink());
        image.setCamping(campingService.findById(campingId));
        return imageRepository.save(image);

    }

    public Image findById(Long id) {

        return imageRepository.findById(id).get();

    }

    public List<Image> findAllByCampingId(Long id) {

        return imageRepository.findAllByCampingId(id);

    }

    public boolean existsByCampingId(Long id) {

        return imageRepository.existsByCampingId(id);

    }

}
