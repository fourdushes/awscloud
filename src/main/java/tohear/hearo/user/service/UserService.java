import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String join(User user) { // 회원 가입
        valiodateDuplicateUser(user.getId());
        userRepository.save(user);
        return user.getId();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("아이디를 찾을 수 없습니다. " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String findId(String name, String email) {
        return userRepository.findIdByNameAndEmail(name, email);
    }

    public void validateLogin(String id, String password) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("아이디가 옳바르지 않습니다. " + id));
        
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 옳바르지 않습니다.");
        }
    }

    public void validateDuplicateUser(String id) { // 중복 회원 검증
        User user = userRepository.findById(id);
        if (user != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
