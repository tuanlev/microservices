# 📌 JWT Registered Claims

JWT (JSON Web Token) định nghĩa một số **claims chuẩn (registered claims)** theo [RFC 7519](https://datatracker.ietf.org/doc/html/rfc7519) để đảm bảo tính nhất quán và bảo mật trong việc trao đổi thông tin giữa các hệ thống.

## 🔍 Bảng mô tả các registered claims

| Claim | Tên đầy đủ        | Ý nghĩa                                                                 | Ví dụ |
|-------|--------------------|------------------------------------------------------------------------|-------|
| `iss` | **Issuer**         | Máy chủ phát hành JWT – thường là dịch vụ xác thực (auth-service).     | `"iss": "https://auth.example.com"` |
| `sub` | **Subject**        | Đối tượng chính của JWT – thường là `user id` hoặc `username`.         | `"sub": "user123"` |
| `aud` | **Audience**       | Đối tượng nhận JWT – thường là service tiêu thụ (resource-service).    | `"aud": "https://api.example.com"` |
| `exp` | **Expiration Time**| Thời điểm hết hạn của token (Unix epoch time – giây).                 | `"exp": 1718544000` |
| `nbf` | **Not Before**     | Token không hợp lệ trước thời điểm này (thường dùng để trì hoãn hiệu lực). | `"nbf": 1718540000` |
| `iat` | **Issued At**      | Thời điểm token được phát hành.                                       | `"iat": 1718539000` |
| `jti` | **JWT ID**         | Mã định danh duy nhất cho token – dùng để chống **replay attack**.     | `"jti": "d2d59b7c-879c-4a8e-b8e6-fd1e3bc379d0"` |

---

## 🔐 Ghi chú bảo mật

- `exp` nên **luôn được sử dụng** để tránh token sống mãi.
- `jti` có thể kết hợp với cache/database để kiểm tra tính duy nhất của token (chống lặp lại).
- `nbf` và `iat` dùng để kiểm soát **thời điểm token bắt đầu có hiệu lực**.

---

## 📦 Ví dụ payload JWT

```json
{
  "iss": "https://auth.example.com",
  "sub": "user123",
  "aud": "https://api.example.com",
  "exp": 1718544000,
  "nbf": 1718540000,
  "iat": 1718539000,
  "jti": "d2d59b7c-879c-4a8e-b8e6-fd1e3bc379d0"
}

# 📌 Phân hóa theo lớp
[HTTP request]
   |
   | --> BearerTokenAuthenticationFilter  
            |
            |--> AuthenticationManager (ProviderManager)
                    |
                    |--> JwtAuthenticationProvider
                            |
                            |--> JwtDecoder (NimbusJwtDecoder) //cần config
                            |
                            |--> JwtAuthenticationConverter
                                     |
                                     |--> JwtGrantedAuthoritiesConverter JwtGrantedAuthoritiesConverter 
            |nên thay đổi để phù hợp với phân quyền không chỉ (không chỉ read write)
            |--> SecurityContextPersistenceFilter
            |
       [SecurityContextHolder.setAuthentication()]

### BearerTokenAuthenticationFilter ->  DefaultBearerTokenResolver implements BearerTokenResolver (Cắt token từ header) với:
###   1. private static final String ACCESS_TOKEN_PARAMETER_NAME = "access_token";

###   2. private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",

### Note: từ đây có thể kiểm tra các thể hiện hay các phụ thuộc trong đó

### Note: các props tối thiểu cần có sau khi decode:
### sub → làm principal trong JwtAuthenticationToken. thay đổi tên ở  org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter


### scope / scp → dùng bởi JwtGrantedAuthoritiesConverter để tạo GrantedAuthority (vd: SCOPE_read). thay đổi tên ở org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter

### exp → được JwtDecoder xác thực (token expired sẽ bị từ chối). 