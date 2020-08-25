package br.com.campingfire.response;

import br.com.campingfire.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse implements Serializable {

    private Long id;
    private String name;
    private String type;
    private byte[] pictureBytes;

    public ImageResponse(Image image) {

        this.id = image.getId();
        this.name = image.getName();
        this.type = image.getType();
        this.pictureBytes = image.getPictureBytes();

    }

}
