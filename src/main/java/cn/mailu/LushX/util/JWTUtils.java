package cn.mailu.LushX.util;

import cn.mailu.LushX.security.JWTUserDetails;
import cn.mailu.LushX.security.JWTUserFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @Author: NULL
 * @Description:jwt-token工具类
 * @Date: Create in 2017/11/6 9:33
 */
public class JWTUtils {
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";
/*    private static final String CLAIM_KEY_ACCOUNT_ENABLED = "enabled";
    private static final String CLAIM_KEY_ACCOUNT_NON_LOCKED = "non_locked";
    private static final String CLAIM_KEY_ACCOUNT_NON_EXPIRED = "non_expired";*/

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access_token.expiration}")
    private Long access_token_expiration;

//    @Value("${jwt.refresh_token.expiration}")
//    private Long refresh_token_expiration;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public JWTUserDetails getUserFromToken(String token){
        JWTUserDetails user;
        try {
            final Claims claims=getClaimsFromToken(token);
           String userId = getUserIdFromToken(token);
            String username=claims.getSubject();
            List roles = (List) claims.get(CLAIM_KEY_AUTHORITIES);
            Collection<? extends GrantedAuthority> authorities = parseArrayToAuthorities(roles);
           /* boolean account_enabled = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_ENABLED);
            boolean account_non_locked = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_LOCKED);
            boolean account_non_expired = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_EXPIRED);*/
            user = new JWTUserDetails(userId, username, "password",  authorities);
        }catch (Exception e){
            user=null;
        }
        return user;
    }

    public boolean validateToken(String token, UserDetails userDetails){
        JWTUserDetails user= (JWTUserDetails) userDetails;
        final String userId=getUserIdFromToken(token);
        final String username=getUsernameFromToken(token);
        return (userId.equals(user.getUserId())
                && username.equals(user.getUsername())
                && !isTokenExpired(token)
                /* && !isCreatedBeforeLastPasswordReset(created, userDetails.getLastPasswordResetDate()) */
        );
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }


    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims clamis=getClaimsFromToken(token);
            username=clamis.getSubject();
        }catch (Exception e){
            username=null;
        }
        return username;
    }

    private Collection<? extends GrantedAuthority> parseArrayToAuthorities(List roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Object role : roles) {
            authority = new SimpleGrantedAuthority(role.toString());
            authorities.add(authority);
        }
        return authorities;
    }

    private String getUserIdFromToken(String token) {
        String userId;
        try {
            final Claims clamis=getClaimsFromToken(token);
            userId= (String) clamis.get(CLAIM_KEY_USER_ID);
        }catch (Exception e){
            userId=null;
        }
        return userId;
    }

    private Claims getClaimsFromToken(String token){
        Claims claims;
        try {
            claims= Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims=null;
        }
        return claims;
    }

    public String generateAccessToken(UserDetails userDetails) throws JsonProcessingException {
        JWTUserDetails user = (JWTUserDetails) userDetails;
        Map<String, Object> claims = generateClaims(user);
        ObjectMapper mapper = new ObjectMapper();
        claims.put(CLAIM_KEY_AUTHORITIES, mapper.writeValueAsString(authoritiesToArray(user.getAuthorities())));
        return generateAccessToken(user.getUsername(), claims);
    }

    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims,access_token_expiration);
    }

    private String generateToken(String subject, Map<String, Object> claims, Long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    private Date generateExpirationDate(Long expiration) {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }


    private Map<String,Object> generateClaims(JWTUserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, user.getUserId());
        return claims;
    }

    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

}
