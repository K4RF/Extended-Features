package dockerEx.project.dto.request;

import dockerEx.project.entity.enums.SocialType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialRegisterRequest {
    private String email;
    private String password;
    private String name;
    private String socialId;
    private SocialType socialType;
}