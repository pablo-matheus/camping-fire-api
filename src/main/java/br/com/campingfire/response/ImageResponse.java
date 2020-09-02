package br.com.campingfire.response;

import br.com.campingfire.model.Image;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse implements Serializable {

    @ApiModelProperty(value = "Image ID", required = true)
    private Long id;

    @ApiModelProperty(value = "Image Name", required = true)
    private String name;

    @ApiModelProperty(value = "Image URL", required = true)
    private String url;

    public ImageResponse(Image image) {

        this.id = image.getId();
        this.name = image.getName();
        this.url = image.getUrl();

    }

}
