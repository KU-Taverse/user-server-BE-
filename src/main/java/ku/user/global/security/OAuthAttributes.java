package ku.user.global.security;

import ku.user.domain.user.infrastructure.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }
    // OAuth2User에서 반환하는 사용자 정보는 Map
    // 따라서 값 하나하나를 변환해야 한다.
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {

        if(registrationId.equals("kakao")) return ofKakao(userNameAttributeName, attributes);
        if(registrationId.equals("google")) return ofGoogle(userNameAttributeName, attributes);
        throw new UnsupportedRegistrationIdException("Unsupported registrationId: " + registrationId);
    }

    private static OAuthAttributes ofGoogle(String usernameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String usernameAttributeName,
                                            Map<String, Object> attributes) {

        Map<String, Object> account = (Map)attributes.get("kakao_account");
        Map<String, String> profile = (Map)account.get("profile");

        return OAuthAttributes.builder()
                .name(profile.get("nickname"))
                .email((String) account.get("email"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .nickname(name)
                .email(email)
                .build();
    }

}
