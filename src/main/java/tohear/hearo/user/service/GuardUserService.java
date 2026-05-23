import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuardUserService {

    private final GuardUserRepository guardUserRepository;

    @Transactional
    public String join(GuardUser guardUser) { // 회원 가입
        validateDuplicateGuardUser(guardUser.getId());
        guardUserRepository.save(guardUser);
        return guardUser.getId();
    }

    public GuardUser findById(String id) {
        return guardUserRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("아이디를 찾을 수 없습니다. " + id));
    }

    public List<GuardUser> findAll() {
        return guardUserRepository.findAll();
    }

    public void validateDuplicateGuardUser(String id) { // 중복 회원 검증
        GuardUser guardUser = guardUserRepository.findById(id);
        if (guardUser != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
