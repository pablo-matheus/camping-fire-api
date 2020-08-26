package br.com.campingfire.response;

import br.com.campingfire.model.Image;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse implements Serializable {

    @ApiModelProperty(value = "Image ID", required = true)
    private Long id;

    @ApiModelProperty(value = "Image Name", required = true)
    private String name;

    @ApiModelProperty(value = "Image Type", required = true)
    private String type;

    @ApiModelProperty(value = "Image File", required = true)
    private byte[] file;

    @ApiModelProperty(value = "Image Size", required = true)
    private Long size;

    public ImageResponse(Image image) {

        this.id = image.getId();
        this.name = image.getName();
        this.type = image.getType();
        this.file = image.getFile();
        this.size = image.getSize();

    }

}
