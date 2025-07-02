package batch.project.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import batch.project.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        // 1. CORS Preflight(OPTIONS) 요청은 무조건 통과
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(req, res);
            return;
        }

        // 2. 비밀번호 재설정 API는 토큰 검사 없이 통과
        if (req.getRequestURI().startsWith("/api/pass/reset-password")) {
            chain.doFilter(req, res);
            return;
        }

        // 3. Authorization 헤더에서 JWT 추출 및 검증
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.validateToken(token);

            if (claims != null) {
                String loginId = claims.getSubject();
                String role = claims.get("role", String.class);

                // role이 null이거나 비어있으면 인증 처리하지 않음(방어 코드)
                if (loginId != null && role != null && !role.isBlank()) {
                    List<GrantedAuthority> auths =
                            List.of(new SimpleGrantedAuthority("ROLE_" + role));

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(loginId, null, auths);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        // 4. 인증 실패(토큰 없음/유효하지 않음)이어도 체인 계속 진행 (Spring Security가 비인증 상태로 처리)
        chain.doFilter(req, res);
    }
}
