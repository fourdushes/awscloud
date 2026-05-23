import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InstitutionsUserService {

    private final InstitutionsUserRepository institutionsUserRepository;

    @Transactional
    public String join(InstitutionsUser institutionsUser) { // 회원 가입
        validateDuplicateInstitutionsUser(institutionsUser.getId());
        institutionsUserRepository.save(institutionsUser);
        return institutionsUser.getId();
    }

    public InstitutionsUser findById(String id) {
        return institutionsUserRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("아이디를 찾을 수 없습니다. " + id));
    }

    public List<InstitutionsUser> findAll() {
        return institutionsUserRepository.findAll();
    }

    public void validateDuplicateInstitutionsUser(String id) { // 중복 회원 검증
        InstitutionsUser institutionsUser = institutionsUserRepository.findById(id);
        if (institutionsUser != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
