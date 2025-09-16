package dockerEx.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import dockerEx.project.entity.Member;
import dockerEx.project.entity.MemberCache;
import dockerEx.project.repository.MemberCacheRepository;

@Service
@RequiredArgsConstructor
public class MemberCacheDirectService {
    private final MemberCacheRepository memberCacheRepository;

    public void saveMemberCache(Member member) {
        MemberCache cache = MemberCache.builder()
                .email(member.getEmail())
                .name(member.getName())
                .role(member.getRole().name())
                .emailVerified(member.isEmailVerified())
                .build();
        memberCacheRepository.save(cache);
    }

    public MemberCache getMemberCache(String email) {
        return memberCacheRepository.findById(email).orElse(null);
    }

    public void deleteMemberCache(String email) {
        memberCacheRepository.deleteById(email);
    }
}
